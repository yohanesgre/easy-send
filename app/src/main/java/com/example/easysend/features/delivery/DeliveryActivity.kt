package com.example.easysend.features.delivery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.easysend.R
import com.example.easysend.databinding.ActivityDeliveryBinding
import com.example.easysend.features.delivery.viewmodel.DeliverySharedViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DeliveryActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedViewModel =
            ViewModelProviders.of(this).get(DeliverySharedViewModel::class.java)
        sharedViewModel.selectedOrderId = intent.getIntExtra("OrderId", 0)
        val binding = DataBindingUtil.setContentView<ActivityDeliveryBinding>(this, R.layout.activity_delivery)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}