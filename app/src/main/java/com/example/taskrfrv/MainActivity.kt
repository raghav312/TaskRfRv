package com.example.taskrfrv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var myWebServices: WebServices
    private lateinit var adapter: ResAdapter
    private lateinit var rv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myWebServices = WebServices.retrofit.create(WebServices::class.java)
        rv = findViewById<RecyclerView>(R.id.rv_restraunts)
        //        getData()
        val intent = Intent(applicationContext , SecondActivity::class.java)
        startActivity(intent)
    }

    private fun getData() {
        var call: Call<ResponseBody> = myWebServices.getRestaurents()
        call.enqueue(object: Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>){
                if(response.isSuccessful){
                    setUpAdapter(response.body()!!)
                }else{
                    Toast.makeText(this@MainActivity, "${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Connect to a NETWORK.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpAdapter(res:ResponseBody?) {
        if(res == null){
            Toast.makeText(this, "Somethings wrong.", Toast.LENGTH_SHORT).show()
        }else{
            var reslist: MutableList<Restaurent> = res.restaurents.toMutableList()
            reslist = reslist.sortedByDescending { it.rating.toFloat() } as MutableList<Restaurent>
            for (a:Restaurent in reslist){
                println(a.rating)
            }
            adapter = ResAdapter(this , reslist , res.baseurl)
            rv.adapter  = adapter
        }
    }
}