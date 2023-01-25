package com.example.myapplication1

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RevisionAdapter( var Jours: List<String> , var days : ArrayList<LocalDate>) : RecyclerView.Adapter<RevisionAdapter.ViewHolder>(){

    // provide a reference to the views for each data item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_activiteday: TextView = itemView.findViewById(R.id.tv2_day)
        val tv_activitedate: TextView = itemView.findViewById(R.id.tv2_date)
        val tv_activitename: TextView = itemView.findViewById(R.id.tv2_name)
         val tv_activitebutton: ImageButton = itemView.findViewById(R.id.completedButton)

    }

    // create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view =  LayoutInflater.from(parent.context).inflate(R.layout.jourlayout2,parent,false)
        // return a new ViewHolder instance
        return ViewHolder(view)
    }

    //replace the contents of a view (invoked by the layout manager)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get the activite at the given position
        val jour = Jours[position]
        val datejours= days[position]
        holder.tv_activiteday.text = jour
        holder.tv_activitedate.text = datejours.monthValue.toString() + "-" + datejours.dayOfMonth.toString()
         // Si la tache existe on change la visibility du bouton a VISIBILE
       /* if ( activiteRevision.taches?.get(0)?.name != null ) {
            holder.tv_activitebutton.visibility=View.VISIBLE
        }
        */

    }
    //  return la taille de la liste d'activite de revision
    override fun getItemCount(): Int {
         return Jours.size
    }
}