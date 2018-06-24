package com.e.absway111567.e_voting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import khttp.post
import kotlinx.android.synthetic.main.activity_balpro.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class balpro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balpro)

        val bund = intent.extras
        var electionid = bund.getInt("electionid")
        var serip = bund.getString("ip")

        val apikey = "36fda24fe5588fa4285ac6c6c2fdfbdb6b6bc9834699774c9bf777f706d05a88"
        val url2 ="http://$serip/xampp/capi/balloats/insert.php"
        val url3 ="http://$serip/xampp/capi/balloats/select.php"
        val url4 ="http://$serip/xampp/capi/programmes/insert.php"

        button.setOnClickListener{
            var balloat = editText14.text
            var program = editText15.text

            doAsync {

                var r2 =""

                var d2 = mapOf("check" to apikey, "electionid" to electionid, "answer" to balloat )
                try { r2 = post(url2, data = d2, timeout = 700.0).text }
                catch (e: Exception) {  }
                //--------------------------------------------------------------------------
                var r3 = ""
                var balloatid = 0                                         // balloat id
                var d1 = mapOf("check" to apikey)
                try {
                    r3 = post(url3, data = d1, timeout = 700.0).text
                    var json = JSONObject(r3)
                    var a1 = json.getJSONArray("result")
                    var b1 = a1.getJSONObject(0)
                    balloatid =b1.getInt("id")
                }  catch (e: Exception) {  }

                //--------------------------------------------------------------------
                var d3 = mapOf("check" to apikey,"bid" to balloatid, "program" to program)
                var r4 =""
                try {
                    r4 = post(url4, data = d3, timeout = 700.0).text

                } catch (e: Exception) {  }

                uiThread { if (r2.equals("yes")&&r4.equals("yes")&&balloatid!=0)  {
                    startActivity(intentFor<connect>("electionid" to electionid,"ip" to serip ))
                }
                    else { toast("network error")}




                }

            }



        }
    }
}
