package com.example.easysend.features.delivery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.easysend.R
import com.example.easysend.databinding.ActivityDeliveryBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DeliveryActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val sharedViewModel = ViewModelProviders.of(this).get(DeliveryActivity::class.java)
        //sharedViewModel.userId = intent.getIntExtra("UserID", 0)
        val binding = DataBindingUtil.setContentView<ActivityDeliveryBinding>(this, R.layout.activity_delivery)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}