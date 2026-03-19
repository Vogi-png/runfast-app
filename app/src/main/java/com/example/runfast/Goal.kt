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

        // IR PARA OBJETIVO
        binding.buttonObjective.setOnClickListener {
            parentFragment?.view?.findViewById<View>(R.id.navigationTabLayout2)?.visibility = View.GONE
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_abas_objective, GoalRegister())
                .addToBackStack(null)
                .commit()
        }

        // IR PARA IMC
        binding.buttomIMC.setOnClickListener {
            val telaImc = GoalImc()


            (activity as? MainActivity)?.ajustarInterface(telaImc)

            parentFragment?.view?.findViewById<View>(R.id.navigationTabLayout2)?.visibility = View.GONE
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_abas_objective, telaImc)
                .addToBackStack(null)
                .commit()
        }
    }
}