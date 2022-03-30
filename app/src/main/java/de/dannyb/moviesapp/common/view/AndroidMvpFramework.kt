package de.dannyb.moviesapp.common.view

import android.view.View

interface ViewMvp {
    val rootView: View

    interface Listener
}

interface ObservableViewMvp<T> : Observable<T>, ViewMvp

interface Observable<T> {

    fun registerListener(listener: T)

    fun unregisterListener(listener: T)
}

abstract class BaseObservableViewMvp<T> : BaseObservable<T>(), ViewMvp

abstract class BaseObservable<T> : Observable<T> {

    protected val listeners = mutableSetOf<T>()

    override fun registerListener(listener: T) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: T) {
        listeners.remove(listener)
    }
}
