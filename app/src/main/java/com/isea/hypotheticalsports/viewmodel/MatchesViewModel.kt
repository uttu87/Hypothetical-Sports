package com.isea.hypotheticalsports.viewmodel

import androidx.lifecycle.viewModelScope
import com.isea.hypotheticalsports.base.BaseViewModel
import com.isea.hypotheticalsports.model.Previous
import com.isea.hypotheticalsports.model.Upcoming
import com.isea.hypotheticalsports.repository.SportsRepository
import com.isea.hypotheticalsports.utils.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MatchesViewModel(private val repository: SportsRepository) : BaseViewModel() {

    val previousMatches = MutableStateFlow<List<Previous>>(emptyList())
    val upcomingMatches = MutableStateFlow<List<Upcoming>>(emptyList())

    init {
        viewModelScope.launch {
            val matches = repository.fetchMatches()
            if (matches.status == Status.SUCCESS) {
                previousMatches.value = matches.data?.matches?.previous!!
                upcomingMatches.value = matches.data.matches.upcoming
            }
        }
    }

}