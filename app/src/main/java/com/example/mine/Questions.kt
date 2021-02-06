package com.example.mine

data class Questions (
        val id : Int,
        val question : String,
        val choice1 : String,
        val choice2 : String,
        val choice3 : String,
        val choice4 : String,
        val correctChoice : Int
)