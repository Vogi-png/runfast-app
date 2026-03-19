package com.example.runfast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runfast.databinding.FragmentGoalRegisterBinding

class GoalRegister : Fragment(R.layout.fragment_goal_register) {

    private var _binding: FragmentGoalRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGoalRegisterBinding.bind(view)


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