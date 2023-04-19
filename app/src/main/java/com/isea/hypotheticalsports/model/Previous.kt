package com.isea.hypotheticalsports.model


import com.google.gson.annotations.SerializedName

data class Previous(
    @SerializedName("away")
    val away: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("highlights")
    val highlights: String,
    @SerializedName("home")
    val home: String,
    @SerializedName("winner")
    val winner: String
)