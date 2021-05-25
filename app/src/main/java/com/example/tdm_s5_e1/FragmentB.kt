package com.example.tdm_s5_e1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.td4_exo4.Enseignant
import kotlinx.android.synthetic.main.fragment_b.*


class FragmentB : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_b, container, false)
    }
    fun displayDetails(title:String,hours:String,ens:Enseignant){
        moduleTitle.text=title
        moduleHour.text=hours
        moduleEns.text=ens.fname
    }
}