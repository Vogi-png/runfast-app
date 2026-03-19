package com.example.runfast

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.runfast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
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

    /**
     * Função Pública para ajustar a interface (Bars/FAB) de qualquer lugar.
     */
    fun ajustarInterface(fragment: Fragment) {
        when (fragment) {
            is GoalRegister -> {
                binding.bottomNavigationView.visibility = View.GONE
                binding.floatingActionButton.hide()
                updateStatusBarColor("#AC250E") // Vermelho RunFast
            }
            is Profile -> {
                binding.bottomNavigationView.visibility = View.VISIBLE
                binding.floatingActionButton.hide()
                updateStatusBarColor("#00000000") // Transparente para a onda
            }
            is Home -> {
                binding.bottomNavigationView.visibility = View.VISIBLE
                binding.floatingActionButton.show()
                updateStatusBarColor(null) // Cor padrão do sistema
            }
            else -> {
                binding.bottomNavigationView.visibility = View.VISIBLE
                binding.floatingActionButton.hide()
                updateStatusBarColor(null)
            }
        }
    }

    /**
     * Troca o fragmento principal e aplica ajustes de layout.
     */
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()

        ajustarInterface(fragment)

        // Aplica os Insets para evitar que o conteúdo fique sob as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Se for Profile ou GoalRegister, tiramos o padding do topo (0)
            val paddingTop = if (fragment is Profile || fragment is GoalRegister) 0 else systemBars.top
            binding.frameLayout.setPadding(0, paddingTop, 0, 0)

            // Padding inferior na barra de navegação
            binding.bottomNavigationView.setPadding(0, 0, 0, systemBars.bottom)

            insets
        }
    }

    /**
     * Altera a cor da barra de status do Android.
     */
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