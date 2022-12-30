package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.db.appdatabase

class MatiereActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matiere)




        val listMatieres=findViewById<RecyclerView>(R.id.listMatieres)

        val adapter=MatiereAdapter(this)
        listMatieres.layoutManager=LinearLayoutManager(this@MatiereActivity)
        listMatieres.adapter=adapter




//        val bt_add=findViewById<Button>(R.id.button1)
//
//        bt_add.setOnClickListener {
//
//            startActivity(Intent(this,AddMatiere::class.java))
//        }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.matiere_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this,AddMatiere::class.java))
        return super.onOptionsItemSelected(item)
    }
}