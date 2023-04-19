package com.isea.hypotheticalsports.views

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.isea.hypotheticalsports.R
import com.isea.hypotheticalsports.adapter.MatchAdapter
import com.isea.hypotheticalsports.base.BaseDataBindingFragment
import com.isea.hypotheticalsports.databinding.FragmentMatchesBinding
import com.isea.hypotheticalsports.viewmodel.MatchesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class UpcomingMatchesFragment : BaseDataBindingFragment<FragmentMatchesBinding, MatchesViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_matches
    override val viewModel: MatchesViewModel by activityViewModel ()


    private lateinit var adapter: MatchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MatchAdapter()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.upcomingMatches.collect {
                    adapter.matches = it
                }
            }
        }

        binding.rvPreviousMatches.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPreviousMatches.adapter = adapter
    }
}