package com.anwesh.uiprojects.linkedconccircmarkstepview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.conccircmarkview.ConcCircMarkStepView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConcCircMarkStepView.create(this)
    }
}
