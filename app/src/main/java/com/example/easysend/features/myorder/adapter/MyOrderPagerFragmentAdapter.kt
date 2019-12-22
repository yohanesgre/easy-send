package com.example.easysend.features.myorder.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.easysend.features.myorder.view.MyOrderChildFragment

const val ACTIVE_FRAGMENT_INDEX = 0
const val ARCHIVED_FRAGMENT_INDEX = 1

class MyOrderPagerFragmentAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        ACTIVE_FRAGMENT_INDEX to {
            MyOrderChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        MyOrderChildFragment.POSITION_KEY,
                        ARCHIVED_FRAGMENT_INDEX
                    )
                }
            }
        },
        ARCHIVED_FRAGMENT_INDEX to {
            MyOrderChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        MyOrderChildFragment.POSITION_KEY,
                        ACTIVE_FRAGMENT_INDEX
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