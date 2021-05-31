package com.example.tdm_s5_e1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.td4_exo4.Module

class MainActivity : AppCompatActivity(),MyCommunicator {
    private var mIsDualPane = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // If FragmentB is present in activity_main.xml, then we are in Tablet
        // Else the app is running in handset
        val fragmentBView = findViewById<View>(R.id.fragmentB)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            R.id.item1->{
                val intent = Intent(this,SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else->
                return super.onOptionsItemSelected(item)

        }
    }
    override fun displayDetails(data: ContactDTO) {
        if(mIsDualPane){// If we are in Tablet
            val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentB) as FragmentB?
            fragmentB?.displayDetails(data.name)

        }else{
            val intent = Intent(this,SmsActivity::class.java)
            intent.putExtra("contact",data)
            startActivity(intent)
        }
    }
}