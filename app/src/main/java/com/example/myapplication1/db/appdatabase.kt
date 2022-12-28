package com.example.myapplication1.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication1.data.Matiere
import com.example.myapplication1.data.Revision
import java.sql.Date

class appdatabase(
    mcontext : Context,
    name: String="apprevision_db",
    version: Int=1
): SQLiteOpenHelper(
    mcontext,
    name,
    null,
    version
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableMatieres= """
            CREATE TABLE matieres(
                id integer PRIMARY KEY,
                name varchar(50) unique
            )
        """.trimIndent()
        val createTableRevisions="""
            CREATE TABLE revisions(
                id integer PRIMARY KEY,
                id_matiere integer references matieres(id),
                date date,
                hour integer,
                nbr_hour integer,
                status integer,
                constraint UniqueDate unique(date,hour)
            )
        """.trimIndent()
        db?.execSQL(createTableMatieres)
        db?.execSQL(createTableRevisions)
    }

    override fun onUpgrade(db: SQLiteDatabase?, v1: Int, v2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS matieres")
        db?.execSQL("DROP TABLE IF EXISTS revisions")
        onCreate(db)
    }

    fun addMatiere(matiere:Matiere): Boolean {
        val db = this.writableDatabase
        val values= ContentValues()
        values.put("name",matiere.name)
        val res=db.insert("matieres",null,values).toInt()

        db.close()
        return res != -1
    }

    fun getAllMatiere():ArrayList<Matiere>{
        val db = this.readableDatabase
        val listres = ArrayList<Matiere>()
        val res = db.rawQuery("select * from matieres",null)
        while(res.moveToNext()){
            listres.add(Matiere(res.getInt(0),res.getString(1)))
        }
        db.close()
        return listres
    }

    fun deleteMatiere(id:Int): Boolean{
        val db = this.writableDatabase
        val res1 =db.delete("revisions","where id_matiere=$id",null)
        val res2 =db.delete("matieres","where id=$id",null)
        return res2==1

    }

    fun updateMatiere(matiere:Matiere): Boolean{
        val db = this.readableDatabase
        val values= ContentValues()
        values.put("name",matiere.name)
        val res = db.update("matieres",values,"where id=${matiere.id}",null)
        return res != -1
    }

    fun findRevisionByIdMatiere(id:Int):ArrayList<Revision>{
        val db = this.readableDatabase
        val listres = ArrayList<Revision>()
        val selectionargs= arrayOf(id.toString())
        val res = db.query("revisions",null,"id_matiere=?",selectionargs,null,null,"date , hour")
        while(res.moveToNext()){
            listres.add(Revision(res.getInt(0),res.getInt(1),Date.valueOf(res.getString(2)),res.getInt(3),res.getInt(4),res.getInt(5)))
        }

        db.close()
        return listres
    }

    fun findRevisionByDate(date: Date):ArrayList<Revision>{
        val db = this.readableDatabase
        val listres = ArrayList<Revision>()
        val selectionargs= arrayOf(date.toString())
        val res = db.query("revisions",null,"date=?",selectionargs,null,null,"hour")
        while(res.moveToNext()){
            listres.add(Revision(res.getInt(0),res.getInt(1),Date.valueOf(res.getString(2)),res.getInt(3),res.getInt(4),res.getInt(5)))
        }

        db.close()
        return listres
    }

    fun deleteRevision(id:Int): Boolean{
        val db = this.writableDatabase

        val res =db.delete("revisions","where id=$id",null)
        return res==1

    }

    fun addRevision(revision:Revision): Boolean {
        val db = this.writableDatabase
        val values= ContentValues()
        values.put("id_matiere",revision.id_matiere)
        values.put("date",revision.date.toString())
        values.put("hour",revision.hour)
        values.put("nbr_hour",revision.nbr_hour)
        values.put("status",revision.status)
        val res=db.insert("revisions",null,values).toInt()

        db.close()
        return res != -1
    }


    fun updateRevision(revision:Revision): Boolean{
        val db = this.readableDatabase
        val values= ContentValues()
        values.put("id_matiere",revision.id_matiere)
        values.put("date",revision.date.toString())
        values.put("hour",revision.hour)
        values.put("nbr_hour",revision.nbr_hour)
        values.put("status",revision.status)
        val res = db.update("revisions",values,"where id=${revision.id}",null)
        return res != -1
    }




}