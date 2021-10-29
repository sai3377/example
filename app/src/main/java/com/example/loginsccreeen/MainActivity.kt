package com.example.loginsccreeen

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.loginsccreeen.NetworkInterceptor.NoConnectivityException
import com.example.loginsccreeen.Singleton.Constants
import com.example.loginsccreeen.retrofit.ApiService
import com.example.loginsccreeen.retrofit.DataClass.AppverersionResponse
import com.example.loginsccreeen.retrofit.DataClass.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    lateinit var uname: EditText
    lateinit var paswd: EditText
    lateinit var login: Button
    lateinit var fetchdataButton:Button
    lateinit var androidTextview:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uname = findViewById(R.id.uname)
        paswd = findViewById(R.id.paswd)
        login = findViewById(R.id.login)
        fetchdataButton=findViewById(R.id.fetchdataButton)
        androidTextview=findViewById(R.id.androidTextview)






        login.setOnClickListener { view ->

            if(uname.text.toString().isEmpty())
            {
                Toast.makeText(applicationContext, "usename is empty", Toast.LENGTH_LONG).show()
            }
            else if(paswd.text.toString().isEmpty())
            {
                Toast.makeText(applicationContext, "password is empty", Toast.LENGTH_LONG).show()
            }
            else
            {
//                var res=uname.text.toString()+paswd.text.toString()
//
//                Toast.makeText(applicationContext, res, Toast.LENGTH_LONG).show()
                getProfileDataFromServer();
            }
        }

        fetchdataButton.setOnClickListener { view ->
            val requestInterface = ApiService.create(Constants.logInUrl, this)
            val Appversioncall = requestInterface.appVersion()
            Appversioncall.enqueue(object : Callback<AppverersionResponse> {
                override fun onResponse(
                    call: Call<AppverersionResponse>,
                    response: Response<AppverersionResponse>
                ) {
                    if (response.isSuccessful()) {

                        val AppversionResponse = response.body()
                        if (AppversionResponse!!.status) {
                            androidTextview.setText(" Android version "+AppversionResponse!!.appdetails.androidversion)
                        } else {
                            Toast.makeText(applicationContext, "Sorry. Can't able to fetch data..!", Toast.LENGTH_LONG).show()
                        }

                    }else{

                        Toast.makeText(applicationContext, "Error "+response.code(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<AppverersionResponse>, t: Throwable) {
                    Log.e("TAG", "onFailure: " + t.toString())
                    if (t is NoConnectivityException) {
                        Toast.makeText(applicationContext, "NO Internet Connection", Toast.LENGTH_LONG).show()
                    }
                }

            })
        }




    }

    // Fetching employee details
    fun getProfileDataFromServer() {
//        val mProgressDialog = ProgressDialog(applicationContext)
//        mProgressDialog.isIndeterminate = true
//        mProgressDialog.setMessage("loading...")
//        mProgressDialog.show()


        val requestInterface = ApiService.create(Constants.logInUrl, this)

        val loginCall = requestInterface.Login(uname.text.toString()!!, paswd.text.toString()!!)
        loginCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: retrofit2.Response<LoginResponse>) {
//                mProgressDialog.dismiss()
                if (response.isSuccessful()) {
                    val loginResponse = response.body()
                    // Navigating to Dashboard
                    if (loginResponse!!.status) {
                        Toast.makeText(applicationContext, "Successfully logged IN ", Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(applicationContext, "Sorry. Can't able to fetch data..!", Toast.LENGTH_LONG).show()
                    }
                } else {
                    // error case
//                        Constants.serverErrorMsg(response.code(), mContext)
                    Toast.makeText(applicationContext, "Error "+response.code(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: " + t.toString())
                if (t is NoConnectivityException) {
                    Toast.makeText(applicationContext, "NO Internet Connection", Toast.LENGTH_LONG).show()
                }
//                mProgressDialog.dismiss()
            }

        })
    }
}