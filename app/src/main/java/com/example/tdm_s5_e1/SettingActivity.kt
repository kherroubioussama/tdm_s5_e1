package com.example.tdm_s5_e1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_setting.*
import yuku.ambilwarna.AmbilWarnaDialog
import kotlin.properties.Delegates


abstract class SettingActivity : AppCompatActivity() {
    private var defaultColor by Delegates.notNull<Int>()
    private lateinit var mainContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        mainContainer = findViewById(R.id.mainContainer)
        defaultColor = ContextCompat.getColor(this, R.color.purple_200)
        defaultColor = ContextCompat.getColor(this, R.color.purple_200)
        loadData()
        colorChange.setOnClickListener {

            openColorPicker()

        }
    }

    private fun openColorPicker() {
        val colorPicker : AmbilWarnaDialog =  AmbilWarnaDialog(this,defaultColor,object : AmbilWarnaDialog.OnAmbilWarnaListener{

            override fun onCancel(dialog: AmbilWarnaDialog?){
            }

            override fun onOk(dialog: AmbilWarnaDialog?, color:Int){

                defaultColor = color
                saveData()
                mainContainer.setBackgroundColor(defaultColor)
            }
        })
        colorPicker.show()

    }
    private fun loadData() {

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedInt = sharedPreferences.getInt("INT_KEY",defaultColor)
        defaultColor = savedInt
        mainContainer.setBackgroundColor(savedInt)
    }

    private fun saveData() {

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putInt("INT_KEY",defaultColor)

        }.apply()
        Toast.makeText(this,"Data Saved", Toast.LENGTH_SHORT).show()
    }
}