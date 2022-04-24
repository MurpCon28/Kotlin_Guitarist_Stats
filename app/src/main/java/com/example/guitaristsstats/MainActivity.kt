package com.example.guitaristsstats

import android.graphics.drawable.Drawable
//import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.guitaristsstats.MainFragment
import kotlinx.android.synthetic.main.editor_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        setSupportActionBar(Toolbar)

//        button1.setOnClickListener{
//            displayImageAsset("davegrohl.png")
//        }
//
//        button2.setOnClickListener{
//            displayImageAsset("jackwhite.png")
//        }
//
//        button3.setOnClickListener{
//            displayImageAsset("theedge.png")
//        }

//        button1.setOnClickListener{
//            displayImageResource(R.drawable.davegrohl1)
//        }
//
//        button2.setOnClickListener{
//            displayImageResource(R.drawable.jackwhite1)
//        }
//
//        button3.setOnClickListener{
//            displayImageResource(R.drawable.theedge1)
//        }
    }

    private fun displayImageResource(resId: Int) {
        guitaristImage.setImageResource(resId)
    }

//    private fun displayImageAsset(fileName: String){
//        assets.open(fileName).use {
//            val drawable = Drawable.createFromStream(it, null)
//            guitaristImage.setImageDrawable(drawable)
//        }
//    }
}