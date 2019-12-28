package com.example.easysend.features.onboarding.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.easysend.features.onboarding.OnboardingFragment

const val BONUS_FRAGMENT_INDEX = 0
const val KOMISI_FRAGMENT_INDEX = 1
const val CASHBON_FRAGMENT_INDEX = 2

class OnboardingPagerFragmentAdapter(
    fragmentManager:FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        BONUS_FRAGMENT_INDEX to {
            OnboardingFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        OnboardingFragment.POSITION_KEY,
                        BONUS_FRAGMENT_INDEX
                    )
                }
            }
        },
        KOMISI_FRAGMENT_INDEX to {
            OnboardingFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        OnboardingFragment.POSITION_KEY,
                        KOMISI_FRAGMENT_INDEX
                    )
                }
            }
        },
        CASHBON_FRAGMENT_INDEX to {
            OnboardingFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        OnboardingFragment.POSITION_KEY,
                        CASHBON_FRAGMENT_INDEX
                    )
                }
            }
        }
    )

    override fun getItemCount(): Int = fragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}