package com.example.music_application

import android.os.Bundle
import android.util.Log // Added import for Log
import android.widget.TextView // Added import for TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager // Added
import androidx.recyclerview.widget.RecyclerView // Added
import retrofit2.Call // Added import for Call
import retrofit2.Callback // Added import for Callback
import retrofit2.Response // Added import for Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var myRecyclerView : RecyclerView
    private lateinit var myAdapter : MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myRecyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>){
                val responseBody = response.body()?.data!!
//                val textview = findViewById<TextView>(R.id.textview)
//                textview.text = responseBody.toString()
                myAdapter = MyAdapter(this@MainActivity, responseBody)
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("MainActivity", "onResponse: $responseBody")



            }

            override fun onFailure(call: Call<MyData?>, t: Throwable){
                Log.d("MainActivity", "onFailure: " + t.message)
            }

        })


    }
}
