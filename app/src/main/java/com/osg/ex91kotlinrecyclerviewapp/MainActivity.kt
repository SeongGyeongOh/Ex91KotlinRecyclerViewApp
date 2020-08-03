package com.osg.ex91kotlinrecyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //대량의 데이터를 멤버변수(property)로
    var items = ArrayList<ItemVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //원래는 서버에서 데이터를 읽어와야 하지만, 시간상 날코딩을 하자
        items.add(ItemVO("Moana", "Profile Image", R.drawable.moana01))
        items.add(ItemVO("Stranger", "Big Image", R.drawable.moana02))
        items.add(ItemVO("Good", "why not?", R.drawable.moana03))
        items.add(ItemVO("Hello", "nice to meet you", R.drawable.moana04))
        items.add(ItemVO("Kotlin RecyclerView", "it is a lovely day", R.drawable.moana05))
        items.add(ItemVO("Moana", "Profile Image", R.drawable.moana01))
        items.add(ItemVO("Stranger", "Big Image", R.drawable.moana02))
        items.add(ItemVO("Good", "why not?", R.drawable.moana03))
        items.add(ItemVO("Hello", "nice to meet you", R.drawable.moana04))
        items.add(ItemVO("Kotlin RecyclerView", "it is a lovely day", R.drawable.moana05))


        //코틀린의 리사이클러는 setAdapter를 사용하지 않고 recyclerview의 프로퍼티로 adapter변수를 가지고 있다
        //그래서 아답터를 굳이 멤버변수로 만들 필요가 없다
        recyclerview.adapter = MyAdapter(this, items)

        //리사이클러뷰의 레이아웃매니저
        recyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        ) //마지막 파라미터로 데이터 순서 뒤집을 수 있음!!!!! 꼭 기억해라!!!!!
    }

    override fun onResume() {
        super.onResume()

        //리사이클러뷰 갱신하기
//        recyclerview.adapter.notifyDataSetChanged() //아답터를 참조하지 않았을 수도 있는 상황이라 에러가 남
        //그래서, recyclerview안에 있는 adapter가 null인지 체크한 후에 명령을 내려야 함
        //멤버변수(property)가 null인지 체크하는 방법
        //!! 키워드의 특징: try-catch가 필요없음. 혹시 null이면 메소드를 실행하지 않음(nullpointerror가 나타나지 않음)
        recyclerview.adapter!!.notifyDataSetChanged() //이중 부정으로 '널이 아니면'을 물어봄
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu ->Toast.makeText(this, "aa", Toast.LENGTH_SHORT ).show()
        }

        return super.onOptionsItemSelected(item)
    }
}