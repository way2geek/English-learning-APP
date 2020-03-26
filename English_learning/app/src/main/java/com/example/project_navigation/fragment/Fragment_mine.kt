package com.example.project_navigation.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bmob.v3.Bmob
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.project_navigation.LoginActivity
import com.example.project_navigation.R
import com.example.project_navigation.RecordActivity
import com.example.project_navigation.WordCollectionActivity
import com.example.project_navigation.entity.Feedback
import com.example.project_navigation.event.LoginEvent
import com.example.project_navigation.event.RxBus
import com.example.project_navigation.event.registerInBus
import kotlinx.android.synthetic.main.fragment_mine.*

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
class Fragment_mine : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
        logbtn.setOnClickListener(){
            startActivity(Intent(this.context, LoginActivity::class.java))

        }
        imageView10.setOnClickListener(){
            startActivity(Intent(this.context, WordCollectionActivity::class.java))
        }
        imageView12.setOnClickListener(){
            startActivity(Intent(this.context, RecordActivity::class.java))
        }
        btnsend2.setOnClickListener(){
            Bmob.initialize(this.context,"d1b43716cc00c524ae63b8ddad2b522e")
             var feedback=Feedback(ratingBar.rating.toString(),editText2.text.toString())
            feedback.save(object : SaveListener<String>(){
                override fun done(objectId: String?, ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(context, "感谢反馈", Toast.LENGTH_LONG).show()
                    } else {
                        Log.e("CREATE", "反馈失败：" + ex.message)
                    }
                }
            })
            editText2.setText("")
            ratingBar.rating=0f
        }

    }
    private fun initObserve(){
            RxBus.observe<LoginEvent>().subscribe(){t:LoginEvent?->kotlin.run { logbtn.text="${t?.user?.username}"}} .registerInBus(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        RxBus.unRegister(this)
    }
}
