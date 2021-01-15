package com.example.yelpi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class RestaurantsAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val restaurant = restaurants[position]
        System.out.println("the list is" + restaurant);
        holder.bind(restaurant)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(restaurant: YelpRestaurant) {
            val nameTextView = itemView.findViewById<TextView>(R.id.tvName)
            nameTextView.text = restaurant.name

            val ratingbar = itemView.findViewById<RatingBar>(R.id.ratingBar)
            ratingbar.rating = restaurant.rating.toFloat()

            val tvnumReview = itemView.findViewById<TextView>(R.id.tvNumReview)
            tvnumReview.text = "${restaurant.numReviews} Reviews"

            val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
            tvAddress.text = restaurant.location.address

            val tvCategory = itemView.findViewById<TextView>(R.id.tvCategory)
            tvCategory.text = restaurant.categories[0].title

            val tvDistance = itemView.findViewById<TextView>(R.id.tvDisatance)
            tvDistance.text = restaurant.displayDistance()

            val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
            tvPrice.text = restaurant.price

            val imageViews = itemView.findViewById<ImageView>(R.id.imageView)


            Glide.with(context).load(restaurant.imageUrl).apply(RequestOptions().transform(
                    CenterCrop(), RoundedCorners(20)
            )).into(imageViews)








        }
    }
}
