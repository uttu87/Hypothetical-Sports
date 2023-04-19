package com.isea.hypotheticalsports.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.isea.hypotheticalsports.utils.Constants.TEAM_TABLE

@Entity(tableName = TEAM_TABLE)
data class Team(
    @SerializedName("id")
    @ColumnInfo("id")
    @PrimaryKey
    val id: String,
    @SerializedName("logo")
    @ColumnInfo("logo")
    val logo: String,
    @SerializedName("name")
    @ColumnInfo("name")
    val name: String
)