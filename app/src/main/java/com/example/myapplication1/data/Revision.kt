package com.example.myapplication1.data

import java.sql.Date

data class Revision(
    var id: Int,
    var id_matiere: Int,
    var name:String,
    var date: Date,
    var hour : Int,
    var nbr_hour: Int,
    var status : Int
 )
{
    public fun finiOuPas(): Boolean {
        if ( status == 1) {
            return true
        }else{
            return false
        }
    }
}
