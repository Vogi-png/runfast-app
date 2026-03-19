package com.example.runfast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runfast.databinding.FragmentProfilePersonalizeBinding

class ProfilePersonalize : Fragment(R.layout.fragment_profile_personalize) {

    private var _binding: FragmentProfilePersonalizeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfilePersonalizeBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as? MainActivity)?.ajustarInterface(Profile())
        _binding = null
    }
}