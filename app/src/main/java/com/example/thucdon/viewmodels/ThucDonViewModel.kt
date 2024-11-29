package com.example.thucdon.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.thucdon.models.ThucDon
import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

class ThucDonViewModel (private var context : Context): ViewModel() {
    private var thucDonList : List<ThucDon> = emptyList()

    fun getThucDon() : List<ThucDon> {
        if (thucDonList.isEmpty()) {
            var json = readJsonFromFile(context, "ontap.json")
            val type = object: TypeToken<List<ThucDon>>() {}.type
            thucDonList = Gson().fromJson(json, type)
        }
        return thucDonList

    }



    fun readJsonFromFile(context: Context, filename: String): String {
        val inputStream = context.assets.open(filename)
        val bufferedReader = BufferedReader(inputStream.reader())
        return bufferedReader.use { it.readText() }
    }
}