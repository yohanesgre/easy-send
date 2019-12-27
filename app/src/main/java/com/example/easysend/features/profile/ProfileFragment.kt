package com.example.easysend.features.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.easysend.databinding.FragmentProfileBinding
import com.example.easysend.di.Injectable

class ProfileFragment : Fragment(), Injectable{

    private lateinit var binding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false).apply{
            toolbar.title = "Profile"
            tvLblEditProfile.setOnClickListener {
                startActivity(Intent(requireContext(), ProfileEditActivity::class.java))
            }
        }
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

}