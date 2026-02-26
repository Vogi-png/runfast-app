package com.example.runfast

import android.os.Bundle
import androidx.activity.enableEdgeToEdge // Import necessário
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.runfast.databinding.ActivityMainBinding
// Removi o import android.app.Fragment antigo

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. CHAME ISSO PRIMEIRO
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        // 2. INFLE O BINDING ANTES DE USÁ-LO
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. AGORA SIM O LISTENER (O binding.root já existe)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // 1. Resolve o fundo: Aplicamos o padding inferior para a NavBar aparecer
            // 2. Resolve o topo: Deixamos 0 para a imagem do Perfil poder subir
            v.setPadding(0, 0, 0, systemBars.bottom)

            insets
        }

        // Resto do código...
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.objective -> replaceFragment(Objective())
                R.id.profile -> replaceFragment(Profile())
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

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)

        // Ajustes de cor e ícones
        when (fragment) {
            is Profile -> {
                binding.floatingActionButton.hide()
                updateStatusBarColor("#00000000")

            }
            is Home -> {
                binding.floatingActionButton.show()
                updateStatusBarColor(null)

            }
            else -> {
                binding.floatingActionButton.hide()
                updateStatusBarColor(null)
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())


            val paddingTop = if (fragment is Profile) 0 else systemBars.top
            binding.frameLayout.setPadding(0, paddingTop, 0, 0)

            binding.bottomNavigationView.setPadding(0, 0, 0, systemBars.bottom)

            insets
        }
    }

    private fun updateStatusBarColor(colorHex: String?) {
        if (colorHex == null) {
            val typedValue = android.util.TypedValue()
            theme.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
            window.statusBarColor = typedValue.data
        } else {
            window.statusBarColor = android.graphics.Color.parseColor(colorHex)
        }
    }
}