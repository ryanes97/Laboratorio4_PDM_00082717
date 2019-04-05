package com.kmina.labo_4.activities.network

import android.net.Uri
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {

    val MOVIES_API_BASEURL = "http://www.omdbapi.com/"
    val TOKEN_API = "128825C4"

    fun buildSearchUrl(movieName: String) : URL {

        val builtUri = Uri.parse(MOVIES_API_BASEURL)
            .buildUpon()
            .appendQueryParameter("apikey", TOKEN_API)
            .appendQueryParameter("t", movieName)
            .build()

        return try {
            URL(builtUri.toString())
        } catch (e : MalformedURLException){
            URL("")
        }

    }

    fun getResponseFromHttpUrl(url : URL):String{
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.inputStream

            val scanner = Scanner(`in`)

            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if (hasInput){
                scanner.next()
            }else{
                ""
            }
        }finally {
            urlConnection.disconnect()
        }
    }
}