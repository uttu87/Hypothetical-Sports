package com.isea.hypotheticalsports.repository

import com.isea.hypotheticalsports.model.Matches
import com.isea.hypotheticalsports.model.Team
import com.isea.hypotheticalsports.repository.api.SportsAPI
import com.isea.hypotheticalsports.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SportsRepository(private val sportsAPI: SportsAPI) : SportsRepositoryInterface {

    override suspend fun fetchTeams(): Resource<List<Team>> = withContext(Dispatchers.IO) {
        try {
            val response = sportsAPI.fetchTeams()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it.teams)
                } ?: Resource.error(response.message(), null)
            } else {
                Resource.error(response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("No Internet Connection!", null)
        }
    }

    override suspend fun fetchMatches(): Resource<Matches> = withContext(Dispatchers.IO) {
        try {
            val response = sportsAPI.fetchMatches()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(response.message(), null)
            } else {
                Resource.error(response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("No Internet Connection!", null)
        }
    }

    override suspend fun fetchMatchesPerTeam(teamId: String):Resource<Matches> =
        withContext(Dispatchers.IO) {
            try {
                val response = sportsAPI.fetchMatchesPerTeam(teamId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        return@let Resource.success(it)
                    } ?: Resource.error(response.message(), null)
                } else {
                    Resource.error(response.message(), null)
                }
            } catch (e: Exception) {
                Resource.error("No Internet Connection!", null)
            }
        }
}