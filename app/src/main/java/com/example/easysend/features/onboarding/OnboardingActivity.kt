package com.example.easysend.features.onboarding

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.easysend.R
import com.example.easysend.databinding.ActivityOnboardingBinding
import com.example.easysend.di.UserCache
import com.example.easysend.features.onboarding.adapter.OnboardingPagerFragmentAdapter
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class OnboardingActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var userCache: UserCache

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private var currentPage = 0
    private lateinit var binding:ActivityOnboardingBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        val viewPager = binding.viewPager
        viewPager.adapter = OnboardingPagerFragmentAdapter(supportFragmentManager, this.lifecycle)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.dotsIndicator.setViewPager2(viewPager)
        viewPager.registerOnPageChangeCallback(object: OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                currentPage = position
                if(position>0){
                    binding.btnBack.visibility = View.VISIBLE
                }else if (position == 0){
                    binding.btnBack.visibility = View.GONE
                }
            }
        })
        binding.btnNext.setOnClickListener {
            if (currentPage == binding.viewPager.adapter!!.itemCount-1){
                userCache.putIsFirstTime(false)
                startActivity(Intent(this, PilihAkunActivity::class.java))
                //SharedPref.getInstance(applicationContext).setIsFirstLaunchToFalse()
                finish()
            }else{
                binding.viewPager.setCurrentItem(currentPage + 1, true)
            }
        }
        binding.btnBack.setOnClickListener {
            if (currentPage > 0){
                binding.viewPager.setCurrentItem(currentPage - 1, true)
            }
        }
        binding.btnLewati.setOnClickListener {
            userCache.putIsFirstTime(false)
            startActivity(Intent(this, PilihAkunActivity::class.java))
            finish()
        }
    }
}