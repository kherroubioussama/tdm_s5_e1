package com.example.tdm_s5_e1

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_b.*

@Suppress("UNREACHABLE_CODE")
class AllFragment : Fragment() {
    private lateinit var listNumbers:ArrayList<String>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_all, container, false)
       val bundle = arguments
        val message = bundle!!.getStringArrayList("contacts")
        listNumbers= message as ArrayList<String>
        for (i in 0..listNumbers.size-1){
            println(listNumbers.get(i))
            println("work+++")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*for (i in 0..listNumbers.size-1){
            println(listNumbers.get(i))
        }*/
        bt_send.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(context as Activity,android.Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                for (i in 0..listNumbers.size-1){
                    println(listNumbers.size)
                    println("work+++")
                }
            }else{
                ActivityCompat.requestPermissions(context as Activity,Array(1){android.Manifest.permission.SEND_SMS},111)
            }
        }
    }

    private fun sendTheSms() {

        val sMessage:String=et_message.text.toString().trim()
        if(!sMessage.equals("")){
            val smsManager: SmsManager = SmsManager.getDefault()
            for (i in 0..listNumbers.size-1){
                val sPhone:String=listNumbers.get(i)
                smsManager.sendTextMessage(sPhone,null,sMessage,null,null)
                Toast.makeText(context,"sms sent successfuly!", Toast.LENGTH_LONG).show()
            }

        }
        else{
            Toast.makeText(context,"enter all!", Toast.LENGTH_LONG).show()
        }
    }
}