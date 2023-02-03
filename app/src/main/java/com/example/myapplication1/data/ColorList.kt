package com.example.myapplication1.data

class ColorList
{
    private val blackHex = "000000"
    private val whiteHex = "FFFFFF"

    val defaultColor: ColorObject = basicColors()[0]

    fun colorPosition(colorObject: ColorObject): Int
    {
        for (i in basicColors().indices)
        {
            if(colorObject == basicColors()[i])
                return i
        }
        return 0
    }

    fun basicColors(): List<ColorObject>
    {
        return listOf(
            ColorObject("Très Important", "F44336"),
            ColorObject("Important", "FFEB3B"),
            ColorObject("Peu Important", "8BC34A"),
            ColorObject("Neutre", "BDBDBD"),
        )
    }
}