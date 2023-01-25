package com.example.myapplication1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var MonthYearText : TextView
    private lateinit var Date : LocalDate



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            //        sohaib test
                    val bt_matiere= findViewById<Button>(R.id.bt_matiere)
                     bt_matiere.setOnClickListener {
                       val intoaddmatiere=Intent(this,MatiereActivity::class.java)
                        startActivity(intoaddmatiere)
                   }
            //        test

         Date = LocalDate.now()
         findViews()
         setAllViews()
    }

    private fun findViews() {
         recyclerView =findViewById(R.id.rv_revision)
         MonthYearText = findViewById(R.id.tv_MonthYear)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthYearView() {
        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern(" MMMM yyyy")
        MonthYearText.setText(Date.format(formatter))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun joursSemaine(): ArrayList<LocalDate> {
        val days : ArrayList<LocalDate> = ArrayList()
        var dimanche : LocalDate? = dernierJourSemaine()
        val finSemaine : LocalDate = dimanche!!.plusWeeks(1)
        dimanche= dimanche.plusDays(1)
        val fin2 : LocalDate = finSemaine.plusDays(1)
        while ( dimanche!!.isBefore(fin2)) {
            days.add(dimanche!!)
            dimanche = dimanche.plusDays(1)
        }
        return days
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dernierJourSemaine(): LocalDate? {
            val laSemaineDerniere : LocalDate = Date.minusWeeks(1)

        while ( Date.isAfter(laSemaineDerniere)) {
            if ( Date.dayOfWeek == DayOfWeek.SUNDAY) {
                return Date
            }
            Date = Date.minusDays(1)
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setAllViews() {
        setMonthYearView()
        val days : ArrayList<LocalDate> = joursSemaine()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)
        val Jours = listOf(
            "Lundi",
            "Mardi",
            "Mercredi",
            "Jeudi",
            "Vendredi",
            "Samedi",
            "Dimanche"
        )
         val myadapter = RevisionAdapter(Jours,days)
          recyclerView.setAdapter(myadapter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun SemaineProchaine(view : View) {
        Date = Date.plusWeeks(1)
        setAllViews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun SemainePrecedante(view : View) {
     Date = Date.minusWeeks(1)
        setAllViews()
    }












}