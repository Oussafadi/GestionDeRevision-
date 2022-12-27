package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView : RecyclerView =findViewById(R.id.rv_revision)
        // linear layout manager
        val layoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)
       // recyclerView.layoutManager = layoutManager


        var listeTaches : ArrayList<tacheRevision>?= ArrayList<tacheRevision>()
        listeTaches?.add(tacheRevision("Projet Kotlin",false))
        var listeTaches2 : ArrayList<tacheRevision> ?= ArrayList<tacheRevision>()
        listeTaches2?.add(tacheRevision("Projet xml1",false))
        var listeTaches3 : ArrayList<tacheRevision> ?= ArrayList<tacheRevision>()
        listeTaches3?.add(tacheRevision("Projet kotlin2",false))
        var listeTaches4 : ArrayList<tacheRevision> ?= ArrayList<tacheRevision>()
        listeTaches4?.add(tacheRevision("Projet xml2",false))

        var revisionList = listOf(
            activiteRevision("Lundi " , listeTaches ,"01-01" ) ,
            activiteRevision("Mardi", listeTaches2,"02-01") ,
            activiteRevision("Mercredi", listeTaches3,"03-01"),
            activiteRevision("Jeudi", listeTaches4,"04-01"),
            activiteRevision("Vendredi", null,"05-01"),
            activiteRevision("Samedi", null,"06-01"),
            activiteRevision("Dimanche", null ,"07-01")

        )
        val myadapter = RevisionAdapter(revisionList)
         recyclerView.setAdapter(myadapter)
    }










}