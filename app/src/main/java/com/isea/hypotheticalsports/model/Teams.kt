package com.isea.hypotheticalsports.model


import com.google.gson.annotations.SerializedName

data class Teams(
    @SerializedName("teams")
    val teams: List<Team>
)