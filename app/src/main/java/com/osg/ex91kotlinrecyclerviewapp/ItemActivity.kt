package com.osg.ex91kotlinrecyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        //인텐트 객체 소환할 필요가 없다?! 이미 Intent가 액티비티의 property로 있어서...
        val title = intent.getStringExtra("title")
        val msg = intent.getStringExtra("msg")
        val img = intent.getIntExtra("img", R.drawable.bazzi)

        //title은 제목줄로..
        supportActionBar!!.title=title
//        tv.text=msg
        tv.text= intent.extras!!["msg"].toString()
        Picasso.get().load(img).into(iv)

    }
}