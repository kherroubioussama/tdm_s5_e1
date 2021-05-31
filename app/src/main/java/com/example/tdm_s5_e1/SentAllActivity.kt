package com.example.tdm_s5_e1

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat

import kotlinx.android.synthetic.main.activity_sent_all.*
import kotlinx.android.synthetic.main.activity_sent_all.bt_send
import kotlinx.android.synthetic.main.activity_sent_all.et_message
import kotlinx.android.synthetic.main.fragment_b.*


class SentAllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sent_all)
        var intent=intent
        val numbers=intent.getStringArrayListExtra("numbers")
        bt_send.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                sendTheSms(numbers as ArrayList<String>)
            }else{
                ActivityCompat.requestPermissions(this,Array(1){android.Manifest.permission.SEND_SMS},111)
            }
        }


    }
    private fun sendTheSms(listNumbers:ArrayList<String>) {

        val sMessage:String=et_message.text.toString().trim()
        if(!sMessage.equals("")){
            val smsManager: SmsManager = SmsManager.getDefault()
            for (i in 0..listNumbers.size-1){
                val sPhone:String=listNumbers.get(i)
                smsManager.sendTextMessage(sPhone,null,sMessage,null,null)
                Toast.makeText(this,"sms sent successfuly!", Toast.LENGTH_LONG).show()
            }

        }
        else{
            Toast.makeText(this,"enter all!", Toast.LENGTH_LONG).show()
        }
    }
}