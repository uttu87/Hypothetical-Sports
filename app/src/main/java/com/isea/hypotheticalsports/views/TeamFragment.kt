package com.isea.hypotheticalsports.views

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.isea.hypotheticalsports.R
import com.isea.hypotheticalsports.adapter.TeamAdapter
import com.isea.hypotheticalsports.base.BaseDataBindingFragment
import com.isea.hypotheticalsports.databinding.FragmentTeamBinding
import com.isea.hypotheticalsports.viewmodel.TeamViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamFragment : BaseDataBindingFragment<FragmentTeamBinding, TeamViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_team
    override val viewModel: TeamViewModel by viewModel()
    private lateinit var adapter: TeamAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TeamAdapter()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.teamList.collect {
                    adapter.teams = it
                }
            }
        }

        binding.rvTeamList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvTeamList.adapter = adapter
    }
}