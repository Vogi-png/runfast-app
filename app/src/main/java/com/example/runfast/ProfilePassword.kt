package com.example.runfast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runfast.databinding.FragmentProfilePasswordBinding

class ProfilePassword : Fragment(R.layout.fragment_profile_password) {

    private var _binding: FragmentProfilePasswordBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfilePasswordBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as? MainActivity)?.ajustarInterface(Profile())
        _binding = null
    }
}