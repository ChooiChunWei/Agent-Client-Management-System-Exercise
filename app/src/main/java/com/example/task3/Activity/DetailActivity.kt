package com.example.task3.Activity

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.task3.Adapter.DetailPagerAdapter
import com.example.task3.R


class DetailActivity : AppCompatActivity(){
    //initialize the components for tablayout and viewpager
    private lateinit var mViewPager: ViewPager
    private lateinit var caseDBtn: TextView
    private lateinit var ScanDBtn: TextView
    private lateinit var infoBtn: TextView
    private lateinit var mPagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //get data from previous activity using bundle
        val bundle = intent.extras

        toolbar()

        //Set the name
        val nameTxt = findViewById<TextView>(R.id.nameTxt)
        val imageView = findViewById<ImageView>(R.id.imageView)

        if(bundle != null){
            var name = bundle.getString("name")
            var Policy = bundle.getString("Policy")
            val Category = bundle.getString("Category")
            val profilePic = bundle.getInt("profilePic")

            nameTxt.text = name
            imageView.setImageResource(profilePic)

            //Set the title in the toolbar
            val title = findViewById<TextView>(R.id.template_title)
            title.text = Policy

        //tablayout and viewpager
        mViewPager = findViewById(R.id.detail_view_pager)
        caseDBtn = findViewById(R.id.caseDBtn)
        ScanDBtn = findViewById(R.id.ScanDBtn)
        infoBtn = findViewById(R.id.infoBtn)

        mPagerAdapter = DetailPagerAdapter(supportFragmentManager,bundle)
        mViewPager.adapter = mPagerAdapter
        mViewPager.offscreenPageLimit = 4

        //add page change listener
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changingTabs(position)
            }
        })

        //Default tab
            if(Category == "NB"){
                mViewPager.currentItem = 0
            }else if(Category == "PS"){
                mViewPager.currentItem = 3
            }

        caseDBtn.setTextColor(Color.WHITE)
        caseDBtn.setBackgroundResource(R.drawable.button_rounded_border_selected)

        caseDBtn.setOnClickListener {
            if(Category == "NB"){
                mViewPager.currentItem = 0
            }else if(Category == "PS"){
                mViewPager.currentItem = 3
            }
        }

        ScanDBtn.setOnClickListener {
            mViewPager.currentItem = 1

        }

        infoBtn.setOnClickListener {
            mViewPager.currentItem = 2

        }

        }
    }

    private fun toolbar(){
        //set toolbar to actionbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //Add a customize back button in the toolbar
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            //back
            onBackPressed()
        }
    }

    private fun changingTabs(position: Int) {
        if (position == 0 || position == 3) {
            caseDBtn.setTextColor(Color.WHITE)
            caseDBtn.setBackgroundResource(R.drawable.button_rounded_border_selected)

            ScanDBtn.setTextColor(Color.BLACK)
            ScanDBtn.setBackgroundResource(R.drawable.button_normal)

            infoBtn.setTextColor(Color.BLACK)
            infoBtn.setBackgroundResource(R.drawable.button_normal)

        }

        if (position == 1) {
            caseDBtn.setTextColor(Color.BLACK)
            caseDBtn.setBackgroundResource(R.drawable.button_normal)

            ScanDBtn.setTextColor(Color.WHITE)
            ScanDBtn.setBackgroundResource(R.drawable.button_rounded_border_selected)

            infoBtn.setTextColor(Color.BLACK)
            infoBtn.setBackgroundResource(R.drawable.button_normal)

        }

        if (position == 2) {
            caseDBtn.setTextColor(Color.BLACK)
            caseDBtn.setBackgroundResource(R.drawable.button_normal)

            ScanDBtn.setTextColor(Color.BLACK)
            ScanDBtn.setBackgroundResource(R.drawable.button_normal)

            infoBtn.setTextColor(Color.WHITE)
            infoBtn.setBackgroundResource(R.drawable.button_rounded_border_selected)
        }
    }

}