package com.example.taskrfrv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ResAdapter(private val context: Context, private val resList:List<Restaurent>, private val BaseURL: String) : RecyclerView.Adapter<ResAdapter.ResViewHolder>(){

    inner class ResViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.res_title)
        var ratings = itemView.findViewById<TextView>(R.id.res_rating)
        var timing = itemView.findViewById<TextView>(R.id.ress_timings)
        var resImage = itemView.findViewById<ImageView>(R.id.res_img)
        var cuisine = itemView.findViewById<TextView>(R.id.res_cusines)
        var delTime = itemView.findViewById<TextView>(R.id.res_delv)
        var minOrder = itemView.findViewById<TextView>(R.id.res_min_bill)
        var availability = itemView.findViewById<TextView>(R.id.res_avbl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResViewHolder {
        return ResViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_card_restraunts , parent , false))
    }

    override fun onBindViewHolder(holder: ResViewHolder, position: Int) {
        var restraunt = resList[position]
        holder.title.text = restraunt.title
        holder.ratings.text = "Ratings: "+restraunt.rating
        holder.timing.text = "Timings:" + restraunt.startTime + " to " + restraunt.endTime
        holder.delTime.text = restraunt.distance
        holder.minOrder.text = "Min Rs."+restraunt.minBilling
        holder.availability.text = restraunt.statusAvlbl

        Picasso.get()
            .load(BaseURL+restraunt.image)
            .into(holder.resImage)

        var arr:List<String> = restraunt.category
        var cus= ""
        for(i:Int in arr.indices){
            if(i == arr.size-1){
                cus+= arr[i]
            }else{
                cus+="${arr[i]}, "
            }
        }
        holder.cuisine.text = cus
    }

    override fun getItemCount(): Int {
        return resList.size
    }
}