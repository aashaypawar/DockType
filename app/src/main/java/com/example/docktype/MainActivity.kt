package com.example.docktype

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dockStatus: Intent? = IntentFilter(Intent.ACTION_DOCK_EVENT).let { ifilter ->
            applicationContext.registerReceiver(null, ifilter)
        }
        val dockState: Int = dockStatus?.getIntExtra(Intent.EXTRA_DOCK_STATE, -1) ?: -1
        val isDocked: Boolean = dockState != Intent.EXTRA_DOCK_STATE_UNDOCKED

        val isCar: Boolean = dockState == Intent.EXTRA_DOCK_STATE_CAR
        val isDesk: Boolean = dockState == Intent.EXTRA_DOCK_STATE_DESK
                || dockState == Intent.EXTRA_DOCK_STATE_LE_DESK
                || dockState == Intent.EXTRA_DOCK_STATE_HE_DESK

        when{
            isCar-> Toast.makeText(applicationContext,"Dock CAR", Toast.LENGTH_SHORT).show()
            isDesk-> Toast.makeText(applicationContext,"Dock DESK", Toast.LENGTH_SHORT).show()
            !isDocked-> Toast.makeText(applicationContext,"Not Docked", Toast.LENGTH_SHORT).show()
        }
    }
}