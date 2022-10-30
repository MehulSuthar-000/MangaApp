package com.example.storyapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chapter.*


class Chapter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)


//
        val storyTitle = intent.getStringExtra("storyTitle")
        val storyContent = intent.getStringExtra("storyContent")
        val storyImage = intent.getStringExtra("storyImage")
        val firstchap = intent.getStringExtra("FirstChapter")

        // setting title of the tool bar
        supportActionBar?.title = storyTitle

//        setDisplayShowHomeEnabled() specifies whether or not the Home button is shown.
//        ActionBar. setDisplayHomeAsUpEnabled() specifies whether or not the Home button has the arrow used for Up Navigation next to it.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//      Using picasso to load images in the recycler view
        Picasso.get().load(storyImage).into(storyFeatureImage)
        storyDetails.text = storyContent


        val firstChapBtn = findViewById<Button>(R.id.chap)
        firstChapBtn.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(firstchap)
            startActivity(i)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
                onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}