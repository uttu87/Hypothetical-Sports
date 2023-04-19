package com.isea.hypotheticalsports.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR

abstract class BaseDataBindingFragment<TBinding : ViewDataBinding, TViewModel : BaseViewModel> :
    BaseFragment()
{

    private var bindingVariable: Int = BR.viewModel

    @get:LayoutRes
    protected abstract val layoutId: Int
    protected abstract val viewModel: TViewModel

    protected val binding get() = _binding!!
    private var _binding: TBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding!!.setVariable(bindingVariable, viewModel)
        _binding!!.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
