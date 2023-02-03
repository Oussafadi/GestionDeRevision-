package com.example.myapplication1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.data.ColorList
import com.example.myapplication1.data.Matiere
import com.example.myapplication1.db.appdatabase

class MatiereAdapter(
    var mcontext : Context
): RecyclerView.Adapter<MatiereViewHolder>() {
    //    @SuppressLint("ResourceAsColor")
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val matiere = values.get(position)
//        val itemView= LayoutInflater.from(mcontext).inflate(resource,parent,false)
//        itemView.setBackgroundColor(R.color.purple_500)
//        val tvTitre=itemView.findViewById<TextView>(R.id.tv_matiere_name)
//        tvTitre.text=matiere.name
//        return itemView
//
//    }

    var db = appdatabase(mcontext)
    var data_matlist= db.getAllMatiere()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatiereViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_matiere,parent,false)

        return MatiereViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatiereViewHolder, position: Int) {
        val matiere=data_matlist.get(position)
        holder.tvname.text=matiere.name
        holder.tvname.setTextColor(Color.parseColor("#000000"))
        holder.container.setBackgroundColor(Color.parseColor(ColorList().basicColors()[matiere.color].hexHash))

        holder.bt_delete.setOnClickListener{
            val builder =AlertDialog.Builder(mcontext)
            builder.setTitle("Delete Matiere")
            builder.setMessage("Etes-vous sur de vouloire supprimer cette Matiere ?")
            builder.setPositiveButton("Oui"){dialogIterface,id ->
                db.deleteMatiere(matiere.id)
                refrech()
            }
            builder.setNegativeButton("Non"){dialogIterface,id ->
                dialogIterface.dismiss()
            }
            val alert=builder.create()
            alert.show()
        }
        holder.bt_edit.setOnClickListener {

            val dialogMatiere=DialogMatiere(mcontext)
            dialogMatiere.createdialog(matiere.id,matiere.name,matiere.color,this)
        }


    }
    fun refrech(){
        data_matlist= db.getAllMatiere()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data_matlist.size
    }
}

class MatiereViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var tvname=itemView.findViewById<TextView>(R.id.tv_matiere_name)
    var bt_edit=itemView.findViewById<Button>(R.id.bt_edit_matiere)
    var bt_delete=itemView.findViewById<Button>(R.id.bt_delete_matiere)
    var container=itemView.findViewById<ConstraintLayout>(R.id.matiere_container)
}
