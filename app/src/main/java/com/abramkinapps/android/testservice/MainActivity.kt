package com.abramkinapps.android.testservice

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var s:Button
    lateinit var fs:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        s = findViewById(R.id.start_service)
        fs = findViewById(R.id.start_foreground_service)


        fs.setOnClickListener{

               val intent_foreground = Intent(this, TestForegroundService::class.java)
                                    .putExtra("TEST_NAME", "TEST_STRING")

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intent_foreground)
                }

        }

        s.setOnClickListener{
            startService(Intent(this, TestService::class.java))
        }


    }

}


