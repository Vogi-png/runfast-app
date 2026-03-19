package com.example.runfast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runfast.databinding.FragmentProfileBinding

class Profile : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        // IR PARA PERSONALIZAR
        binding.buttonPersonalize.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, ProfilePersonalize())
                .addToBackStack(null)
                .commit()
        }

        // IR PARA ALTERAR SENHA
        binding.buttonSenha.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, ProfilePassword())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}