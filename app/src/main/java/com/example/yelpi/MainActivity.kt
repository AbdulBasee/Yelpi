package com.example.yelpi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val API_KEY = "6tI5CJr7XtOgmA7OzDNAFmLgsKz3P2yjoFpgrpwqgRbsW-G15o1VCDCnHoKzCuetZ4yuUKpwFw6iW8m5clnejNVB2UV0vSYl4RzbexXAFJhJkq5OlT-Rfp2MGxr6X3Yx"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter = RestaurantsAdapter(this, restaurants)
        val rvRestaurant = findViewById<View>(R.id.rvRestaurants) as RecyclerView
        rvRestaurant.adapter = adapter
        rvRestaurant.layoutManager = LinearLayoutManager(this)



        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val yelpService = retrofit.create(YelpService::class.java)

        yelpService.searchRestaurants("Bearer $API_KEY","Avocado Toast", "New York").enqueue(object : Callback<YelpSearchResult> {



            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if(body == null){
                    Log.w(TAG, "Did not receive valid response from Yelp API...")
                    return
                }

                restaurants.addAll(body.restaurant)
                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }
        })
    }

}