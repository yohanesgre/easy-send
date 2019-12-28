package com.example.easysend.features.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.easysend.R
import com.example.easysend.databinding.FragmentOnboardingBinding
import com.example.easysend.di.Injectable

class OnboardingFragment : Fragment(), Injectable{
    private var position: Int = 0
    private lateinit var binding:FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        position = arguments?.getInt(POSITION_KEY) ?: 0
        binding = FragmentOnboardingBinding.inflate(inflater, container, false).apply {
            when(position){
                0->{
                    imageOnboarding.visibility = View.INVISIBLE
                    titleOnboarding.text = "Bonus"
                    bodyOnboarding.text = "Tingkatkan ritase dan dapatkan bonus langsung dari Easy Send"
                }
                1->{
                    imageOnboarding.visibility = View.VISIBLE
                    imageOnboarding.setImageResource(R.drawable.ic_onboarding_komisi)
                    titleOnboarding.text = "Komisi"
                    bodyOnboarding.text = "Nikmati kemudahan pencairan komisi yang cepat"
                }
                2->{
                    imageOnboarding.visibility = View.INVISIBLE
                    titleOnboarding.text = "Cashbon"
                    bodyOnboarding.text = "Nikmati fitur cashbon untuk karyawan dan driver"
                }
            }
        }
        return binding.root
    }

    companion object {
        const val POSITION_KEY = "FragmentPositionKey"
    }
}