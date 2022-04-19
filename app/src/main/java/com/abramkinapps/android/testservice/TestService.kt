package com.abramkinapps.android.testservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.TimeUnit

class TestService: Service() {
    var i = 0


    override fun onCreate() {
        super.onCreate()

        Log.d("TAG", "onCreate()")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        val thread = Thread(object: Runnable{

            override fun run() {

                for (j in 1..10) {
                    Log.d("TAG", "OK ${i++}")

                    TimeUnit.SECONDS.sleep(2)
                }

                stopSelf()
            }

        })

        thread.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("TAG", "onDestroy()")
    }

}