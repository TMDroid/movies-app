package de.dannyb.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.dannyb.moviesapp.common.viewBinding
import de.dannyb.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding { ActivityMainBinding.inflate(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
