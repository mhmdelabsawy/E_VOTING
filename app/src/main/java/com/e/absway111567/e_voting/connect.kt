package com.e.absway111567.e_voting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_connect.*
import org.jetbrains.anko.intentFor

class connect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect)

        val bund = intent.extras
        var electionid = bund.getInt("electionid")
        var serip = bund.getString("ip")

        buelec.setOnClickListener { finish() }
        buv.setOnClickListener {

            startActivity(intentFor<addvoters>("electionid" to electionid,"ip" to serip ))
        }
    }
}
