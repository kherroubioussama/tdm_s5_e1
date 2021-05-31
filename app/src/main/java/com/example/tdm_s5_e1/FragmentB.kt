package com.example.tdm_s5_e1

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_b.*


class FragmentB : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_send.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(context as Activity,android.Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                sendTheSms()
            }else{
                ActivityCompat.requestPermissions(context as Activity,Array(1){android.Manifest.permission.SEND_SMS},111)
            }
        }
    }

    private fun sendTheSms() {
        val sPhone:String=et_phone.text.toString().trim()
        val sMessage:String=et_message.text.toString().trim()
        if(!sPhone.equals("")&&!sMessage.equals("")){
            val smsManager:SmsManager= SmsManager.getDefault()
            smsManager.sendTextMessage(sPhone,null,sMessage,null,null)
            Toast.makeText(context,"sms sent successfuly!",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context,"enter all!",Toast.LENGTH_LONG).show()
        }
    }

    fun displayDetails(title:String){
        et_phone.text = Editable.Factory.getInstance().newEditable(title)

    }
}