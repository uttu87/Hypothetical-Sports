package com.isea.hypotheticalsports.model


import com.google.gson.annotations.SerializedName

data class Upcoming(
    @SerializedName("away")
    val away: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("home")
    val home: String
)