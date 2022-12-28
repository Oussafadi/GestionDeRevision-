package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication1.db.appdatabase

class MatiereActivity : AppCompatActivity() {

    lateinit var db : appdatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matiere)

        db = appdatabase(this)

        val tv=findViewById<TextView>(R.id.tv_output)
        val bt_add=findViewById<Button>(R.id.bt_addmatiere)

        val reslist= db.getAllMatiere()
        for(matiere in reslist){
            tv.text= tv.text.toString()+ matiere.name+"\n"

        }

        bt_add.setOnClickListener {
            val toaddmatiere = Intent(this,AddMatiere::class.java)
            startActivity(toaddmatiere)
        }

    }
}