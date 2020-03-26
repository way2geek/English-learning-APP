package com.example.project_navigation.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_navigation.R
import com.example.project_navigation.WebActivity
import kotlinx.android.synthetic.main.fragment_listening.*
/**
 *    author : 徐浩航
 *    version: 1.0
 */

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Fragment_listening : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listening, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageButton.setOnClickListener(){
            startActivity(Intent(this.context, WebActivity::class.java).putExtra("option","1"))
        }
        imageButton3.setOnClickListener(){

            startActivity(Intent(this.context, WebActivity::class.java).putExtra("option","2"))
        }
        imageButton2.setOnClickListener(){
            startActivity(Intent(this.context, WebActivity::class.java).putExtra("option","3"))
        }
        imageButton4.setOnClickListener(){
            startActivity(Intent(this.context, WebActivity::class.java).putExtra("option","4"))
        }
        button.setOnClickListener(){
            startActivity(Intent(this.context, WebActivity::class.java).putExtra("option","5"))
        }
    }
}
