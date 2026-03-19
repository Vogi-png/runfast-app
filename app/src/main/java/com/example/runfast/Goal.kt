package com.example.runfast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runfast.databinding.FragmentGoalBinding

class Goal : Fragment(R.layout.fragment_goal) {

    private var _binding: FragmentGoalBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGoalBinding.bind(view)

        binding.buttonObjective.setOnClickListener {
            val proximaTela = GoalRegister()

            (activity as? MainActivity)?.ajustarInterface(proximaTela)

            parentFragment?.view?.findViewById<View>(R.id.navigationTabLayout2)?.visibility = View.GONE

            parentFragmentManager.beginTransaction()
                .replace(R.id.container_abas_objective, proximaTela)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}