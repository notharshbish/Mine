package com.example.mine

import com.example.mine.Chapters
import com.example.mine.R

object PhyChapters {
    var chapName : String? = null
    var qNo : String? = null
    var tQue : String? = null
    var mCorrectAnswers : String? = null

    fun getChapters() : ArrayList<Chapters> {

        var mList = ArrayList<Chapters>()

        val c1 = Chapters("Interference And Diffraction",
        5,
        R.drawable.ic_physics_icon)
        mList.add(c1)

        val c2 = Chapters("Newton's Law of motion",
                5,
                R.drawable.ic_physics_icon)
        mList.add(c2)

        val c3 = Chapters("Fluid mechanics",
                5,
                R.drawable.ic_physics_icon)
        mList.add(c3)

        val c4 = Chapters("Atoms and nuclei",
                5,
                R.drawable.ic_physics_icon)
        mList.add(c4)

        val c5 = Chapters("Vectors and scalars",
                5,
                R.drawable.ic_physics_icon)
        mList.add(c5)

        val c6 = Chapters("Stress and strain",
                5,
                R.drawable.ic_physics_icon)
        mList.add(c6)

        return mList
    }
}