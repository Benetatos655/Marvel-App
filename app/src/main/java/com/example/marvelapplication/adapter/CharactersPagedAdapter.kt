package com.example.marvelapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelapplication.R
import com.example.marvelapplication.model.MyModelForAdapter
import com.example.marvelapplication.utils.Constants.LIMIT
import com.example.marvelapplication.utils.Utils.dp
import com.example.marvelapplication.utils.Utils.imageFormatChooser
import kotlinx.android.synthetic.main.character_list_item.view.*

class CharactersPagedAdapter(private val context: Context, private val  listener : AdapterInterface ) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var charList : MutableList<MyModelForAdapter> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharactersViewHolder(LayoutInflater.from(context).inflate(R.layout.character_list_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //last item on the view had been rendered
        if(position==(itemCount -1)){
        listener.lastItemOnViewCreated(position)
        }
        (holder as CharactersViewHolder).bind(charList[position])
    }

    override fun getItemCount(): Int {
        return charList.size
    }

    fun update(it: MutableList<MyModelForAdapter>?) {
        it?.let {
           charList.addAll(itemCount,it)
            notifyItemInserted(itemCount - LIMIT)
        }
    }

    inner class CharactersViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        fun bind(item : MyModelForAdapter){


            val url : String =item.thumbnail+imageFormatChooser(context.dp(R.dimen.imageview_dimen), context.dp(R.dimen.imageview_dimen),context)
            view.image.clipToOutline=true
            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.logo)
                .into(view.image)
            view.title.text=item.name
            if (!item.description.isNullOrEmpty()){
                view.info.text=item.description
                view.info.visibility=View.VISIBLE
            }
        }
    }

    interface AdapterInterface{
        fun lastItemOnViewCreated(position: Int)
    }

}