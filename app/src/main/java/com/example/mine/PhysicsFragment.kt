package com.example.mine

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PhysicsFragment : Fragment(), onClicked {
    var chapterName : String = "chapter_name"
    private lateinit var viewmodel : PhyFragmentVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vi =  inflater.inflate(R.layout.fragment_physics, container, false)
        val adapter = ChapterDisplayer(PhyChapters.getChapters(),this)
        val phyView = vi.findViewById<RecyclerView>(R.id.phy_review)
        phyView.layoutManager = LinearLayoutManager(context)
        phyView.adapter = adapter
        viewmodel = ViewModelProvider(this).get(PhyFragmentVM::class.java)
        return vi
    }

    private fun chapterSetter () {
        val intent = Intent(context, QuestionSetter::class.java)
        intent.putExtra(PhyChapters.chapName,viewmodel.chapterName)
        startActivity(intent)
    }

    override fun onPress(chapters: Chapters) {
            viewmodel.determine(chapters)
            chapterSetter()
    }


}