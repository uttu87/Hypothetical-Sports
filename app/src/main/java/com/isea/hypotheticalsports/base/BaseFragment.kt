package com.isea.hypotheticalsports.base

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    //Support handle OnBackPressed for special task at Fragment
    protected open var callback: OnBackPressedCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    override fun onResume() {
        super.onResume()
        callback?.let {
            requireActivity()
                .onBackPressedDispatcher
                .addCallback(this, it)
        }
    }
}
