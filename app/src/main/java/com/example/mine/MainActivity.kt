package com.example.mine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.mine.C.ChemistryFragment
import com.example.mine.M.MathematicsFragment
import com.example.mine.viewPageAdapter.viewPageAd
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setupTabs()
    }

    private fun setupTabs() {
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        val adapter = viewPageAd(supportFragmentManager)

        adapter.addFragment(PhysicsFragment())
        adapter.addFragment(MathematicsFragment())
        adapter.addFragment(ChemistryFragment())
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        Log.i("My tabs","${tabLayout.getTabAt(0)}")

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_physics_icon)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_math_icon)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_chem_icon)
    }
}