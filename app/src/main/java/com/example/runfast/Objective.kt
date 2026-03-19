package com.example.runfast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runfast.databinding.FragmentObjectiveBinding
import com.google.android.material.tabs.TabLayout

class Objective : Fragment(R.layout.fragment_objective) {

    private var _binding: FragmentObjectiveBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentObjectiveBinding.bind(view)

        // 1. Define o fragmento inicial (Meta) assim que a tela abre
        if (savedInstanceState == null) {
            replaceChildFragment(Goal())
        }

        // 2. Escuta os cliques nas abas do TabLayout
        binding.navigationTabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> replaceChildFragment(Goal())        // Clicou em "Meta"
                    1 -> replaceChildFragment(Inspiration()) // Clicou em "Inspiração"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    // Função auxiliar para trocar o fragmento dentro do FrameLayout
    private fun replaceChildFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.container_abas_objective, fragment)
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}