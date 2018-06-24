package com.e.absway111567.e_voting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle


import khttp.post
import kotlinx.android.synthetic.main.activity_votingp.*
import org.jetbrains.anko.*
import org.json.JSONObject

class votingp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_votingp)


        val bundl = intent.extras
        var usrid = bundl.getInt("uid")
        var serip = bundl.getString("ip")
        val apikey = "36fda24fe5588fa4285ac6c6c2fdfbdb6b6bc9834699774c9bf777f706d05a88"
        val url = "http://$serip/xampp/capi/elections/insert.php"
        val urls ="http://$serip/xampp/capi/elections/select.php"


        bucreate.setOnClickListener {
            var eltitle = txet.text
            var elq     = txq.text!!
            var startdate = txsd.text!!
            var enddate   = txed.text!!





        doAsync {
            var r = ""
            var d = mapOf("check" to apikey, "userid" to usrid, "agreement" to 1,
                    "title" to eltitle, "question" to elq, "start_date" to startdate, "end_date" to enddate)
            try {
                r = post(url, data = d, timeout = 700.0).text

            } catch (e: Exception) {  }
            //---------------------------------------------------------------------
            var d1 = mapOf("check" to apikey )
            var r1 =""                                                        // election id
            var electionid=0
            try {
                r1 = post(urls, data = d1, timeout = 700.0).text
                var json = JSONObject(r1)
                var a = json.getJSONArray("result")
                var b = a.getJSONObject(0)
                electionid =b.getInt("id")
            } catch (e: Exception) {  }
            //-------------------------------------------------------






            uiThread {
                if (r.equals("yes")) {

                    startActivity(intentFor<balpro>("electionid" to electionid,"ip" to serip ))
                }
                else {
                    toast("network error !")
                }


            }
        }
    }
     }
  }