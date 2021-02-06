package com.example.mine

import androidx.lifecycle.ViewModel
import com.example.mine.Chapters

class PhyFragmentVM : ViewModel() {

    var chapterName : String = "chapter_name"

    fun determine(chaps : Chapters) {
        chapterName = chaps.title
    }
}