package com.e.absway111567.e_voting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import khttp.post
import kotlinx.android.synthetic.main.activity_addvoters.*
import org.jetbrains.anko.*

class addvoters : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addvoters)
        val bund = intent.extras
        var electionid = bund.getInt("electionid")
        var serip = bund.getString("ip")
        val apikey = "36fda24fe5588fa4285ac6c6c2fdfbdb6b6bc9834699774c9bf777f706d05a88"
        val url = "http://$serip/xampp/capi/voters/insert.php"


        buaddvoter.setOnClickListener {
            doAsync {
                var name = editText.text
                var email = editText2.text
                var password =  editText3.text
                var r=""
                var d = mapOf("check" to apikey, "name" to name, "email" to email, "pass" to password, "electionid" to electionid)
                try {
                       r= post(url, data = d, timeout = 700.0).text

                } catch (e: Exception) { r="no network" }

                uiThread {
                    if (r.equals("yes")) { startActivity<end>() }
                    else {
                        toast("network error !")
                    }
                }

            }
        }

    }
}
