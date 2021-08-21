package com.example.taskrfrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {
    private lateinit var myWebServices:WebServices
    private lateinit var adapter: GalAdapter
    private lateinit var rvOthers: RecyclerView
    private lateinit var rvOffers: RecyclerView
    private lateinit var rvSpecialities: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        myWebServices = WebServices.retrofit.create(WebServices::class.java)
        rvOthers = findViewById(R.id.recyclerViewOthers)
        rvOffers = findViewById(R.id.recyclerViewOffers)
        rvSpecialities = findViewById(R.id.recyclerViewSpecialities)
        getGallery()
    }
    private fun getGallery(){
        var call:retrofit2.Call<GalleryResponseBody> = myWebServices.getShopGallery()
        call.enqueue(object :Callback<GalleryResponseBody>{
            override fun onResponse(
                call: retrofit2.Call<GalleryResponseBody>,
                response: Response<GalleryResponseBody>
            ) {
                if(response.isSuccessful){
                    setupAdapter(response.body())
                }else{
                    Toast.makeText(this@SecondActivity, "Somethings wrong.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<GalleryResponseBody>, t: Throwable) {
                Toast.makeText(this@SecondActivity, "Connect to a NETWORK.", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupAdapter(body: GalleryResponseBody?) {
        if(body == null){
            Toast.makeText(this, "Nothing.", Toast.LENGTH_SHORT).show()
        }else{
            var gl:List<Gallery> = body.gallery

            adapter = GalAdapter(this , gl , body.baseurl ,  CODE_OTHERS )
            rvOthers.adapter = adapter

            adapter = GalAdapter(this , gl , body.baseurl ,  CODE_OFFER )
            rvOffers.adapter = adapter

            adapter = GalAdapter(this , gl , body.baseurl ,  CODE_SPEC )
            rvSpecialities.adapter = adapter
        }
    }


    companion object{
        const val CODE_OTHERS = 1
        const val CODE_OFFER = 2
        const val CODE_SPEC = 3
    }

}