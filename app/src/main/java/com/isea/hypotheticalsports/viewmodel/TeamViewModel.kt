package com.isea.hypotheticalsports.viewmodel

import androidx.lifecycle.viewModelScope
import com.isea.hypotheticalsports.base.BaseViewModel
import com.isea.hypotheticalsports.model.Team
import com.isea.hypotheticalsports.repository.SportsRepository
import com.isea.hypotheticalsports.utils.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: SportsRepository) : BaseViewModel() {

    val teamList = MutableStateFlow<List<Team>>(emptyList())

    init {
        viewModelScope.launch {
            val result = repository.fetchTeams()
            if(result.status == Status.SUCCESS) {
                teamList.value = result.data!!
            }
        }
    }
}