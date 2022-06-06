package com.example.retrofitmain.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmain.R
import com.example.retrofitmain.adapter.RecyclerViewAdapter
import com.example.retrofitmain.databinding.ActivityMainBinding
import com.example.retrofitmain.model.CrypoModel
import com.example.retrofitmain.service.CrypoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL ="https://raw.githubusercontent.com/"
    private var cryptoModels : ArrayList<CrypoModel>?=null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadData()

    }

    private fun loadData() {
     val retrofit = Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()

    val service = retrofit.create(CrypoAPI::class.java)
    val call=service.getData()

    call.enqueue(object: Callback<List<CrypoModel>>{
        override fun onResponse(
            call: Call<List<CrypoModel>>,
            response: Response<List<CrypoModel>>
        ) {
            if(response.isSuccessful){
                response.body()?.let {
                    cryptoModels=ArrayList(it)
                    cryptoModels?.let {
                        recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                        binding.recyclerView.adapter = recyclerViewAdapter
                    }

                }
            }

        }

        override fun onFailure(call: Call<List<CrypoModel>>, t: Throwable) {
            t.printStackTrace()

        }

    })



    }
    override fun onItemClick(cryptoModel: CrypoModel) {
        Toast.makeText(applicationContext, "Clicked : ${cryptoModel.currency}", Toast.LENGTH_SHORT).show()

    }}