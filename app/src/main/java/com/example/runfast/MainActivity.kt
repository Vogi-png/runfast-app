package com.example.runfast

import android.app.Fragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.runfast.databinding.ActivityMainBinding
import androidx.core.view.WindowInsetsControllerCompat


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.objective -> replaceFragment(Objective())
                R.id.profile -> replaceFragment(Profile())

                else ->{

                }
            }
            true
        }

        binding.floatingActionButton.setOnClickListener {
            replaceFragment(Add())
        }
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

        when (fragment) {
            is Objective -> updateStatusBarColor("#AC260F") // Laranja
            is Profile -> updateStatusBarColor("#AC0E2B")   // Vermelho
            is Home -> updateStatusBarColor(null)           // null para voltar ao padrão (Branco/Tema)
            else -> updateStatusBarColor(null)
        }
    }

    private fun updateStatusBarColor(colorHex: String?) {
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)

        if (colorHex == null) {
            val typedValue = android.util.TypedValue()
            theme.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
            window.statusBarColor = typedValue.data

        } else {
            window.statusBarColor = android.graphics.Color.parseColor(colorHex)

        }
    }
}