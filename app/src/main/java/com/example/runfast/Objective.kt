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

        replaceChildFragment(Goal())

        binding.navigationTabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.linearLayout.visibility = View.VISIBLE

                        replaceChildFragment(Goal())
                    }
                    1 -> {
                        binding.linearLayout.visibility = View.GONE
                        replaceChildFragment(Inspiration())
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun replaceChildFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.container_abas_objective, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}