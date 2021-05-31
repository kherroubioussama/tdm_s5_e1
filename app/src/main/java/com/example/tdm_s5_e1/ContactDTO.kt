package com.example.tdm_s5_e1

import android.net.Uri
import android.provider.ContactsContract
import java.io.Serializable

class ContactDTO (_name:String,_number:String):Serializable{
    var name = _name
    var mail = _number
}