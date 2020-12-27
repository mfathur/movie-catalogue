package com.mfathurz.moviecatalogue.util

import androidx.room.TypeConverter

class RoomTypeConverter {

    @TypeConverter
    fun getListOfIntFromString(genreIds: String): List<Int> {
        val genreIdList = ArrayList<Int>()

        val arr = genreIds.split(",")

        for (item in arr) {
            if (item != "") {
                genreIdList.add(Integer.parseInt(item))
            }
        }

        return genreIdList
    }

    @TypeConverter
    fun getStringFromListOfInteger(list: List<Int>): String {
        var string = ""

        for (i in list) {
            string += ",$i"
        }
        return string
    }

    @TypeConverter
    fun getStringFromListOfString(list: List<String>): String {
        var string = ""

        for (i in list) {
            string += ",$i"
        }
        return string
    }

    @TypeConverter
    fun getListOfStringFromString(str: String): List<String> {
        val list = ArrayList<String>()

        val arrList = str.split(",")

        for (item in arrList) {
            list.add(item)
        }

        return list
    }
}