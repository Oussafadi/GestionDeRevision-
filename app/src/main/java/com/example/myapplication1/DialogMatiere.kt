package com.example.myapplication1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.myapplication1.data.ColorList
import com.example.myapplication1.data.Matiere
import com.example.myapplication1.db.appdatabase

class DialogMatiere(var mcontext:Context) {

        var db=appdatabase(mcontext)
        fun createdialog(id:Int,name:String?,color:Int?,adapt:MatiereAdapter?){
            val dialogbuilder= AlertDialog.Builder(mcontext)
            val popupview= LayoutInflater.from(mcontext).inflate(R.layout.popup_matiere,null)
            val editName =popupview.findViewById<EditText>(R.id.tvname_matiere)
            val tv_error = popupview.findViewById<TextView>(R.id.error)
            val bt_save = popupview.findViewById<Button>(R.id.save)
            val colorSpiner=popupview.findViewById<Spinner>(R.id.colorSpinner)
            colorSpiner.apply {
                adapter = ColorSpinnerAdapter(mcontext, ColorList().basicColors())
            }
            if(id!=0){
                editName.text.insert(0,name)
                if (color != null) {
                    colorSpiner.setSelection(color)
                }
            }

            bt_save.setOnClickListener {
                tv_error.visibility = View.INVISIBLE
                tv_error.text = ""

                val name = editName.text.toString()
                val color = colorSpiner.selectedItemPosition

                if (name.isEmpty()) {
                    tv_error.text = "name required"
                    tv_error.visibility = View.VISIBLE

                } else {
                    val newmatiere = Matiere(id, name, color)
                    var res = false
                    if (id == 0) {
                        res = db.addMatiere(newmatiere)
                    } else {
                        res = db.updateMatiere(newmatiere)
                    }


                    if (res) {
                        Toast.makeText(mcontext, "succes", Toast.LENGTH_LONG).show()
                        adapt?.refrech()

                        //startActivity(Intent(this, MatiereActivity::class.java))

//                    val rev=Revision(0,1, "revision",Date.valueOf("2020-12-10"),5,2,1)
//                    var res2=db.addRevision(rev)
//                    rev.hour=(1..20).random()
//                    res2=db.addRevision(rev)


                    } else {
                        tv_error.text = "name exist"
                        tv_error.visibility = View.VISIBLE
                    }
                }
            }


            dialogbuilder.setView(popupview)
            val dialog=dialogbuilder.create()
            dialog.show()
        }
    }
