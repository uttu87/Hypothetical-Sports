package com.isea.hypotheticalsports.model


import com.google.gson.annotations.SerializedName

data class Matches(
    @SerializedName("matches")
    val matches: Match
)