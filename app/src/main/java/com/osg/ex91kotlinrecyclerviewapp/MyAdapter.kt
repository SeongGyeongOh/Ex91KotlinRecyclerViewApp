package com.osg.ex91kotlinrecyclerviewapp

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*

class MyAdapter constructor(val context: Context, val items:ArrayList<ItemVO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {//자바에서는 제네릭을 생략할 수 있지만, 코틀린에서는 꼭 명시해야함

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.recycler_item, parent, false)

        val vh = VH(itemView)
        return vh
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as VH //형변환은 as다
        val item = items[position]

//        vh.itemView.tvTitle.setText(item.title)
//        vh.itemView.tvMsg.setText(item.msg)
//        vh.itemView.iv.setImageResource(item.img)

        //코틀린에서는 set메소드를 싫어한다! 대신, set해서 설정할 값을 property(속성)로 설정하는 것을 권장한다
        //set을 없애고 대입연산자로...
        vh.itemView.tvTitle.text=item.title
        vh.itemView.tvMsg.text=item.msg
        //글라이드와 같은 역할을 하는 피카소를 써보자
        Picasso.get().load(item.img).into(vh.itemView.iv)

        //코틀린은 이 위치에서 itemView의 클릭 이벤트 처리를 한다
//        vh.itemView.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(p0: View?) {
//                //아이템 상세 정보 페이지 화면으로 이동
//            }
//
//        })

    }

    //inner class ViewHolder : itemView 안의 뷰들을 관리하는 클래스
    inner class VH constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        //이 부분 안만들어도 됨..?
//        val tvTitle = itemView.tvTitle
//        val tvMsg = itemView.tvMsg
//        val iv = itemView.iv
        init{
            //원래 자바에서는 이 생성자에서 getLayoutPosition 메소드로 클릭한 아이템을 구분했다.
            //그런데 kotlin에서는 getLayoutPosition() 대신에 이 아답터에 멤버변수로 이미 layoutPosition이라는 놈이 있다
            //그런데 layoutPosition의 속성값이 언제나 항상 -1이다
            //그래서 이곳에서 클릭 위치를 찾을 방법이 없다...


            //그래서, 아이템의 위치정보가 존재하는 onBindViewHolder에서 클릭 이벤트를 처리한다-> 이제는 된다

            itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    Toast.makeText(context, ""+layoutPosition, Toast.LENGTH_SHORT ).show()

                    val intent = Intent(context, ItemActivity::class.java)
                    intent.putExtra("title", items.get(layoutPosition).title)
                    intent.putExtra("msg", items.get(layoutPosition).msg)
                    intent.putExtra("img", items.get(layoutPosition).img)

                    context.startActivity(intent)

                }
            })
        }
    }
}