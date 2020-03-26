package com.example.project_navigation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_navigation.ArticleActivity
import com.example.project_navigation.DictActivity
import com.example.project_navigation.adapter.NewsAdapter
import com.example.project_navigation.R
import com.example.project_navigation.RecordActivity
import com.example.project_navigation.entity.News
import kotlinx.android.synthetic.main.fragment_menu.*

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
 * Activities that contain this fragment must implement the
 * [Fragment_menu.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Fragment_menu.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Fragment_menu : Fragment() {
    private val data = ArrayList<News>()
    lateinit var adapter: NewsAdapter
    private val icons = arrayOf(
        R.drawable.item1,
        R.drawable.item2,
        R.drawable.item3,
        R.drawable.item4
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_menu, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data.add(News(icons[0], "苹果推出AirPods Pro无线降噪耳机", "沪江"))
        data.add(News(icons[1], "傻脸娜的新歌影射比伯,霉霉大力支持!", "沪江"))
        data.add(News(icons[2], "新的基因编辑技术诞生", "沪江"))
        data.add(News(icons[3], "NASA工程师提出的引擎概念能达到光速的99%", "沪江"))
        adapter= NewsAdapter(data)
        lv.adapter=adapter
        imageView6.setOnClickListener(){
                startActivity(Intent(this.context,DictActivity::class.java))
        }
        imageView5.setOnClickListener(){
                startActivity(Intent(this.context,ArticleActivity::class.java))
        }
        imageView3.setOnClickListener(){
                startActivity(Intent(this.context,RecordActivity::class.java))
    }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */



}
