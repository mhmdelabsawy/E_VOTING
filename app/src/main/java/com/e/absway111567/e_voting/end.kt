package com.e.absway111567.e_voting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_connect.*
import kotlinx.android.synthetic.main.activity_end.*

class end : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        bumv.setOnClickListener { finish() }
        buno.setOnClickListener { finishAffinity() }
    }
}
