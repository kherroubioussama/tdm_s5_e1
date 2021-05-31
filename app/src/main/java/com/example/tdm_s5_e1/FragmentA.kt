package com.example.tdm_s5_e1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.td4_exo4.ContactAdapter
import kotlinx.android.synthetic.main.fragment_a.*


class FragmentA : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_a, container, false)

        setupRecyclerView(rootView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        smsAll.setOnClickListener {
            var numbers: ArrayList<String> = ArrayList()
            for (i in 0..getContacts().size-1){
                numbers.add(getContacts().get(i).mail)
            }
            val intent = Intent(context,SentAllActivity::class.java)
            intent.putExtra("numbers",numbers )
            startActivity(intent)
        }
    }
    private fun setupRecyclerView(rootView: View?) {
        val recyclerView = rootView!!.findViewById(R.id.lessonList) as RecyclerView

        val adapter = ContactAdapter(context, getContacts())
        recyclerView.adapter = adapter

        val manager = GridLayoutManager(activity,

            resources.getInteger(R.integer.num))
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = manager
    }
    @SuppressLint("UseRequireInsteadOfGet")
    private fun getContacts():List<ContactDTO>{
        val contactList : MutableList<ContactDTO> = ArrayList()
        if(ActivityCompat.checkSelfPermission(context as Activity,android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(context as Activity,Array(1){android.Manifest.permission.READ_CONTACTS},111)
        }else{
            val cursor: Cursor? = context!!.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,
                ContactsContract.Contacts.DISPLAY_NAME)
            cursor!!.moveToFirst()
            while ( cursor.moveToNext()){

                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val mail = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                print(name+"a")
                contactList.add(ContactDTO(name,mail))

            }
            cursor.close()}
        return contactList
    }
}