package com.example.thucdon.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.thucdon.models.ThucDon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

class ThucDonDetailViewModel(private var context : Context) : ViewModel() {
    private var thucDonList : List<ThucDon> = emptyList()


    fun getThucDonDetail(id: Int): ThucDon {
        if (thucDonList.isEmpty()) {
            var json = readJsonFromFile(context, "ontap.json")
            val type = object: TypeToken<List<ThucDon>>() {}.type
            thucDonList = Gson().fromJson(json, type)
        }
        val thucDon = thucDonList.find { it.id == id }
        if (thucDon != null) {
            return thucDon

        }
        return ThucDon(1,"Không tìm thấy",0,"","")
    }


    fun readJsonFromFile(context: Context, filename: String): String {
        val inputStream = context.assets.open(filename)
        val bufferedReader = BufferedReader(inputStream.reader())
        return bufferedReader.use { it.readText() }
    }
}