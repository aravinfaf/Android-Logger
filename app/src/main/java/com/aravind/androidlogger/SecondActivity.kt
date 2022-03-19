package com.aravind.androidlogger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception

class SecondActivity : AppCompatActivity() {

    val logger = LoggerFactory.getLogger(SecondActivity::class.java)
    var path = "/data/data/com.aravind.log4j/files/app.txt"
    lateinit var fileReader: FileReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        logger.debug("Debug")
        logger.warn("Warn")
        logger.info("Info")
        logger.error("Error")


        val stringBuffer = StringBuffer()

        try {

            val file = File(path)

            if (file.exists()) {
                fileReader = FileReader(file)

                Log.v("SS", file.extension)

                val bufferedReader = BufferedReader(fileReader)
                var line: String = bufferedReader.readLine()
                while (line != null) {
                    stringBuffer.append(line).append("Reading : \n")
                    line = bufferedReader.readLine()
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        finally {
            Log.e("TAG",stringBuffer.toString())
        }
    }
}