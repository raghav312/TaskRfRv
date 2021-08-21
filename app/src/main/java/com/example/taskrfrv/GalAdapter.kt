package com.example.taskrfrv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GalAdapter(val context: Context , val galList:List<Gallery> , val BaseURL:String , val rvCode:Int): RecyclerView.Adapter<GalAdapter.GalViewHolder>(){

    inner class GalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.gal_title)
        val img = itemView.findViewById<ImageView>(R.id.imageView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalViewHolder {
        return GalViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_gallery_cards , parent , false))
    }

    override fun onBindViewHolder(holder: GalViewHolder, position: Int) {
        var item = galList[position]
        if(rvCode == 1 && item.type == "Other" || rvCode == 2 && item.type == "Offer" || rvCode == 3 && item.type == "Specialities" ) {
            holder.title.text = item.title
            Picasso.get()
                .load(BaseURL + item.image)
                .into(holder.img)
        }
    }

    override fun getItemCount(): Int {
        return galList.size
    }
}