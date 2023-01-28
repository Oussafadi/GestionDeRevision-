package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.data.ColorList
import com.example.myapplication1.data.Matiere
import com.example.myapplication1.db.appdatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MatiereActivity : AppCompatActivity() {


    lateinit var db : appdatabase
    lateinit var listMatieres : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matiere)
        db=appdatabase(this)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnItemSelectedListener(
            NavigationBarView.OnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home -> {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    }
                    R.id.add -> {
                        val intent = Intent(this, MatiereActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.stat -> {
//mazal mat9adat
                    }

                }
                false
            })


        listMatieres=findViewById<RecyclerView>(R.id.listMatieres)


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
        val dialogMatiere=DialogMatiere(this)
        dialogMatiere.createdialog(0,null,null,listMatieres.adapter as MatiereAdapter)

        return super.onOptionsItemSelected(item)
    }


}