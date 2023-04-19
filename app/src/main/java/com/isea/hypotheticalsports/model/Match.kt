package com.isea.hypotheticalsports.model


import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("previous")
    val previous: List<Previous>,
    @SerializedName("upcoming")
    val upcoming: List<Upcoming>
)