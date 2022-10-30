package com.example.storyapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var storyTitles = arrayOf<String>()
    private var storyContent = arrayOf<String>()
    private var storyImages = arrayOf<String>()
    private var firstChap = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //In its most basic form, the action bar displays the title for the activity on one side and an overflow menu on the other.
        // Even in this simple form, the app bar provides useful information to the users, and helps to give Android apps a consistent look and feel.
        setSupportActionBar(toolbar)

//        This class provides a handy way to tie together the functionality of DrawerLayout
//        and the framework ActionBar to implement the recommended design for navigation drawers.
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open, R.string.close)

        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)

//        Call syncState to synchronize the indicator with the state of the linked DrawerLayout
        toggle.syncState()

        //calling the setNavigationItemSelectedListener( Context )
        navigationView.setNavigationItemSelectedListener(this)

        //Initializing the  string Arrays from values folder
        storyTitles = resources.getStringArray(R.array.story)
        storyContent = resources.getStringArray(R.array.storyDescription)
        storyImages = resources.getStringArray(R.array.storyimage)
        firstChap = resources.getStringArray(R.array.first_chap)

        // calling ItemAdapter class with (value strings.xml as parameter)
        val adapter = ItemAdapter(storyTitles,storyContent,storyImages,firstChap)


        //This is going to create linear layout  in the RecyclerView
        storyList.layoutManager = LinearLayoutManager(this)

        // Inserting itemAdapter in the recycler View adapter method
        storyList.adapter = adapter


    }

    // this is the method to know which row has been clicked in the Recycler View
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        if(item.itemId == R.id.random){
            val randPosition = Random.nextInt(0,storyTitles.size)
            val intent = Intent(this,Chapter::class.java)
            intent.putExtra("storyTitle",storyTitles[randPosition])
            intent.putExtra("storyContent" , storyContent[randPosition])
            intent.putExtra("storyImage" , storyImages[randPosition])
            intent.putExtra("firstChapter",firstChap[randPosition])
            startActivity(intent)
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0,R.anim.slide_out_left)
    }
}