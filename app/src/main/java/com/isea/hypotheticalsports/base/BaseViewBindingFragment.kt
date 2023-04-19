package com.isea.hypotheticalsports.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingFragment<VBinding : ViewBinding> :
    BaseFragment()
{

    protected val binding get() = _binding!!
    private var _binding: VBinding? = null

    // Eg: ProfileBinding.inflate(inflater, container, false)
    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VBinding

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
