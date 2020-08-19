package com.woowrale.openlibrary.utils

class DataUtils{

    companion object{
        fun concatUrl(url: String):String{
            return "https:$url";
        }

        fun getUrl(url: String):String{
            val urls = url.split("/")
            return urls?.get(2)
        }

    }
}