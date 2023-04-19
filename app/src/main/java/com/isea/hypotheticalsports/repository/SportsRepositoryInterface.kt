package com.isea.hypotheticalsports.repository

import com.isea.hypotheticalsports.model.Matches
import com.isea.hypotheticalsports.model.Team
import com.isea.hypotheticalsports.utils.Resource

interface SportsRepositoryInterface {

    suspend fun fetchTeams() : Resource<List<Team>>

    suspend fun fetchMatches() : Resource<Matches>

    suspend fun fetchMatchesPerTeam(teamId: String): Resource<Matches>

}