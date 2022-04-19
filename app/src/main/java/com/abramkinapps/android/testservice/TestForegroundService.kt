package com.abramkinapps.android.testservice

import android.app.*
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Build
import android.os.IBinder
import android.util.Log

import java.util.concurrent.TimeUnit

class TestForegroundService: Service(){

   private var i = 0

    override fun onCreate() {
        super.onCreate()

        Log.d("TAG", "onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val test_string = intent?.getStringExtra("TEST_NAME") ?: "null"


        val thread = Thread(object: Runnable{

            override fun run() {

                for (j in 1..10) {
                    Log.d("TAG", "$test_string ${i++}")

                    TimeUnit.SECONDS.sleep(2)
                }

                stopSelf()
            }

        }).start()

       val intentContent = Intent(applicationContext, MainActivity::class.java)

       val pendingIntent = PendingIntent.getActivity(applicationContext, 100, intentContent, PendingIntent.FLAG_UPDATE_CURRENT)

        val CHANNEL_ID = "ID"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(CHANNEL_ID,CHANNEL_ID,NotificationManager.IMPORTANCE_LOW)


        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

         val notif =  Notification.Action.Builder(Icon.createWithResource(applicationContext, R.drawable.ic_launcher_background),"BUTTON",pendingIntent).build()

        val notification = Notification.Builder(this, CHANNEL_ID)
            .setContentText("MY CONTENT TEXT")
            .setContentTitle("CONTENT TITLE")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setColor(Color.BLUE)
            .addAction(notif)
            .addAction(notif)



            startForeground(100,notification.build())

        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("TAG", "onDestroy()")
    }

    override fun onBind(p0: Intent?): IBinder? {
       return null
    }

}