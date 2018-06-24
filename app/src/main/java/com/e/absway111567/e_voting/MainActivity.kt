package com.e.absway111567.e_voting

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import khttp.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.run
import org.jetbrains.anko.*
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        bu.setOnClickListener {
            var inpu = tx.text!!.toString()

            var url = "http://$inpu/xampp/capi/users/login.php"
            var urls = "http://$inpu/xampp/capi/users/selection.php"   // user id
            val apikey ="36fda24fe5588fa4285ac6c6c2fdfbdb6b6bc9834699774c9bf777f706d05a88"
            var mail = txemail.text!!
            var pass = txpass.text!!
            var p = mapOf("check" to apikey, "email" to mail,"password" to pass)

            doAsync {

                var r =""
                var uid =""
                var userid =0

                try {


                    r = post(url, data =p,  timeout = 700.0).text
                    //------------------------------------------
                    var d = mapOf("check" to apikey,"email" to mail)
                    uid = post(urls, data=d, timeout = 700.0).text     //user id

                    var json = JSONObject(uid)
                    var a = json.getJSONArray("result")
                    var b = a.getJSONObject(0)
                    userid =b.getInt("id")


                    //----------------------------------------


                }

                catch(e: Exception) {r="no network"}



                uiThread {
                    if (r.equals("Login Success")){
                        longToast(r)
            try {    startActivity(intentFor<votingp>( "uid" to userid,  "ip" to inpu))




            }  catch (e: Exception) {toast("no network")}


                    }

                    if (r.equals("Login Failed"))  { longToast("wrong email or password!")}

                }

            }


        }

    }

}