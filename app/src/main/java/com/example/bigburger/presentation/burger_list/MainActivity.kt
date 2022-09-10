package com.example.bigburger.presentation.burger_list

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.bigburger.R
import com.example.bigburger.common.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val burgerListViewModel: BurgerListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set retry btn action
        btn_retry.setOnClickListener {
            observeResourceBurger()
        }

        //create observers
        observeResourceBurger()
    }

    private fun observeResourceBurger(){
        burgerListViewModel.getBurgers().observe(this, Observer { resource ->
            when (resource){
                is Resource.Loading ->{
                    //display progress bar
                    lin_error.visibility = View.GONE
                    progress_circular.visibility = View.VISIBLE
                    burger_list.visibility = View.GONE
                }
                is Resource.Error -> {
                    //display error message
                    error_message.text = resource.message
                    lin_error.visibility = View.VISIBLE
                    progress_circular.visibility = View.GONE
                    burger_list.visibility = View.GONE
                }
                is Resource.Success -> {
                    //initialize list adapter with data
                    var adapter = BurgersRecyclerViewAdapter(this, resource.data ?: emptyList())
                    burger_list.adapter = adapter
                    lin_error.visibility = View.GONE
                    progress_circular.visibility = View.GONE
                    burger_list.visibility = View.VISIBLE
                }
            }
        })
    }
}