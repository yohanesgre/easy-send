package com.example.easysend.features.komisi.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.easysend.features.komisi.view.KomisiChildFragment

const val ACTIVE_FRAGMENT_INDEX = 0
const val ARCHIVED_FRAGMENT_INDEX = 1

class KomisiPagerFragmentAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        ACTIVE_FRAGMENT_INDEX to {
            KomisiChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        KomisiChildFragment.POSITION_KEY,
                        ACTIVE_FRAGMENT_INDEX
                    )
                }
            }
        },
        ARCHIVED_FRAGMENT_INDEX to {
            KomisiChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        KomisiChildFragment.POSITION_KEY,
                        ARCHIVED_FRAGMENT_INDEX
                    )
                }
            }
        }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}