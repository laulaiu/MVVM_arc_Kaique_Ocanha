package com.leonardo.mvvm_arc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonardo.mvvm_arc.data.ViewModel.ViewModelFactory
import com.leonardo.mvvm_arc.data.ViewModel.ViewModelLive
import com.leonardo.mvvm_arc.data.api.Ret
import com.leonardo.mvvm_arc.data.model.LiveModelo
import com.leonardo.mvvm_arc.data.repositories.DataRepository
import com.leonardo.mvvm_arc.databinding.ActivityMainBinding
import com.leonardo.mvvm_arc.other.adapter.AdapterRc

//https://s3.amazonaws.com/api.ocanha.com/lista-lives.json

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : ViewModelLive
    private val retrofitService = Ret.getIntance()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var repository : DataRepository
    private var adapter : AdapterRc = AdapterRc{
        Toast.makeText(this, "Text: "+it.author, Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        repository = DataRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(DataRepository(retrofitService))
        ).get( ViewModelLive::class.java )

        val recyclerView =  binding.listaliverecy
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveData.observe(this, Observer {lista->
            adapter.setList(lista)
        })

        viewModel.liveData_Error.observe(this, Observer{message ->
            Toast.makeText(this, "Error: "+ message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getALlLives()
    }


}