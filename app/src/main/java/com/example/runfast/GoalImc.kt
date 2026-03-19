package com.example.runfast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runfast.databinding.FragmentGoalImcBinding

class GoalImc : Fragment(R.layout.fragment_goal_imc) {

    private var _binding: FragmentGoalImcBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGoalImcBinding.bind(view)

        binding.buttonCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        parentFragment?.let { pai ->
            (activity as? MainActivity)?.ajustarInterface(pai)
            pai.view?.findViewById<View>(R.id.navigationTabLayout2)?.visibility = View.VISIBLE
        }
        _binding = null
    }
}