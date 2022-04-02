package de.dannyb.moviesapp.movies.view

import android.view.View
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import de.dannyb.moviesapp.R
import de.dannyb.moviesapp.common.view.BaseObservableViewMvp
import de.dannyb.moviesapp.data.DiscoverMovieModel
import de.dannyb.moviesapp.databinding.FragmentMoviesBinding

class MoviesViewImpl(
    private val binding: FragmentMoviesBinding
) : MoviesViewMvp, BaseObservableViewMvp<MoviesViewMvp.Listener>() {

    override val rootView: View
        get() = binding.root

    private val moviesAdapter = MoviesListAdapter { movie ->
        listeners.forEach { it.onMovieClicked(movie) }
    }

    init {
        setupRecyclerView()
        setupSearchBar()
    }

    private fun setupRecyclerView() {
        binding.moviesList.apply {
            layoutManager = GridLayoutManager(rootView.context, GRID_COUNT)
            adapter = moviesAdapter
        }
    }

    private fun setupSearchBar() {
        configureSearchBarTitle()
        with(binding.floatingSearchView) {
            setOnQueryChangeListener { _, newQuery -> configureSuggestions(newQuery) }

            setOnFocusChangeListener(object : FloatingSearchView.OnFocusChangeListener {
                override fun onFocus() {
                    configureSuggestions()
                    setSearchBarTitle("")
                    setSearchText("")
                }

                override fun onFocusCleared() {
                    swapSuggestions(listOf())
                    configureSearchBarTitle()
                }
            })

            setOnSearchListener(object : FloatingSearchView.OnSearchListener {
                override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
                    val movie = searchSuggestion as? DiscoverMovieModel ?: return

                    clearSearchFocus()
                    println("Clicked on suggestion ${movie.title}")
                    listeners.forEach { it.onMovieClicked(movie) }
                }

                override fun onSearchAction(currentQuery: String?) {
                    println("Searched for $currentQuery")
                }
            })

            inflateOverflowMenu(R.menu.menu_movies)
            setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.load_movies_latest ->
                        listeners.forEach { it.loadMoviesType(MoviesType.LATEST) }
                    R.id.load_movies_most_popular ->
                        listeners.forEach { it.loadMoviesType(MoviesType.MOST_POPULAR) }
                }
            }
        }
    }

    private fun generateSearchSuggestions(query: String? = null): List<SearchSuggestion> {
        val items = moviesAdapter.snapshot().items
        return items.filter {
            if (query == null) true
            else it.title.contains(query, ignoreCase = true)
        }
    }

    override suspend fun addMovies(pagingData: PagingData<DiscoverMovieModel>) {
        moviesAdapter.submitData(pagingData)
    }

    private fun configureSuggestions(query: String? = null) {
        val suggestions = generateSearchSuggestions(query)
        binding.floatingSearchView.swapSuggestions(suggestions)
    }

    private fun configureSearchBarTitle() {
        with(binding.floatingSearchView) {
            setSearchBarTitle(
                context.getString(R.string.movies__title)
            )
        }
    }

    companion object {
        private const val GRID_COUNT = 3
    }
}
