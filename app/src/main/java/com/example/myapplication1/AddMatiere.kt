package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.myapplication1.data.ColorList
import com.example.myapplication1.data.ColorObject
import com.example.myapplication1.data.Matiere
import com.example.myapplication1.data.Revision
import com.example.myapplication1.db.appdatabase
import java.sql.Date
import java.text.SimpleDateFormat


class AddMatiere : AppCompatActivity() {



    lateinit var db : appdatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_matiere)

        db = appdatabase(this)
        val editName =findViewById<EditText>(R.id.tvname_matiere)
        val tv_error = findViewById<TextView>(R.id.error)
        val bt_save = findViewById<Button>(R.id.save)
        val colorSpiner=findViewById<Spinner>(R.id.colorSpinner)
        colorSpiner.apply {
            adapter = ColorSpinnerAdapter(applicationContext, ColorList().basicColors())
        }

        val id =intent.getIntExtra("id",0)
        val name =intent.getStringExtra("name")
        val color=intent.getIntExtra("color",0)
        if(id!=0){
            editName.text.insert(0,name)
            colorSpiner.setSelection(color)
        }



        bt_save.setOnClickListener{
            tv_error.visibility= View.INVISIBLE
            tv_error.text=""

            val name = editName.text.toString()
            val color=colorSpiner.selectedItemPosition

            if(name.isEmpty()){
                tv_error.text="name required"
                tv_error.visibility= View.VISIBLE

            }else{

                val newmatiere=Matiere(id,name,color)
                var res = false
                if(id==0){
                    res = db.addMatiere(newmatiere)
                }else{
                    res = db.updateMatiere(newmatiere)
                }


                if(res){
                    Toast.makeText(this,"succes",Toast.LENGTH_LONG).show()

                    startActivity(Intent(this,MatiereActivity::class.java))

//                    val rev=Revision(0,1, "revision",Date.valueOf("2020-12-10"),5,2,1)
//                    var res2=db.addRevision(rev)
//                    rev.hour=(1..20).random()
//                    res2=db.addRevision(rev)


                }else{
                    tv_error.text="name exist"
                    tv_error.visibility= View.VISIBLE
                }
            }
        }
    }
}