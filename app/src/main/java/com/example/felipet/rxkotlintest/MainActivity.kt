package com.example.felipet.rxkotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.felipet.rxkotlintest.operators.MapActivity
import com.example.felipet.rxkotlintest.operators.ZipActivity
import org.jetbrains.anko.intentFor


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun map(view: View) {
        startActivity(intentFor<MapActivity>())
    }

    fun zip(view: View) {
        startActivity(intentFor<ZipActivity>())
    }



}
