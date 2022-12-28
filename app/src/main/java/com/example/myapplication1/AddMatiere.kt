package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication1.data.Matiere
import com.example.myapplication1.db.appdatabase

class AddMatiere : AppCompatActivity() {

    lateinit var db : appdatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_matiere)

        db = appdatabase(this)
        val editName =findViewById<EditText>(R.id.tvname_matiere)
        val tv_error = findViewById<TextView>(R.id.error)
        val bt_save = findViewById<Button>(R.id.save)

        bt_save.setOnClickListener{
            tv_error.visibility= View.INVISIBLE
            tv_error.text=""

            val name = editName.text.toString()

            if(name.isEmpty()){
                tv_error.text="name required"
                tv_error.visibility= View.VISIBLE

            }else{
                val newmatiere=Matiere(0,name)
                val res = db.addMatiere(newmatiere)
                if(res){
                    Toast.makeText(this,"saved",Toast.LENGTH_LONG).show()

                }else{
                    tv_error.text="name exist"
                    tv_error.visibility= View.VISIBLE
                }
            }
        }
    }
}