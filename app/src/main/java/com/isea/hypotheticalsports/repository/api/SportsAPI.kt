package com.isea.hypotheticalsports.repository.api

import com.isea.hypotheticalsports.model.Matches
import com.isea.hypotheticalsports.model.Teams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SportsAPI {

    @GET("/teams")
    suspend fun fetchTeams(): Response<Teams>

    @GET("/teams/matches")
    suspend fun fetchMatches(): Response<Matches>

    @GET("/teams/{id}/matches")
    suspend fun fetchMatchesPerTeam(@Path("{id}") id: String): Response<Matches>
}