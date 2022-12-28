package com.example.myapplication1.data

import java.sql.Date

data class Revision(
    var id: Int,
    var id_matiere: Int,
    var date: Date,
    var hour : Int,
    var nbr_hour: Int,
    var status : Int
 )
