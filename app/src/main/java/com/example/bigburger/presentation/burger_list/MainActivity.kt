package com.example.bigburger.presentation.burger_list

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.bigburger.R
import com.example.bigburger.common.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBurgerList: RecyclerView
    private lateinit var mErrorMessage: TextView
    private lateinit var mProgress: ProgressBar
    private lateinit var mLinError: LinearLayout
    private lateinit var mBtnRetry: Button

    private val burgerListViewModel: BurgerListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bind ui
        mBurgerList = findViewById(R.id.burger_list)
        mErrorMessage = findViewById(R.id.error_message)
        mProgress = findViewById(R.id.progress_circular)
        mLinError = findViewById(R.id.lin_error)
        mBtnRetry = findViewById(R.id.btn_retry)

        // set retry btn action
        mBtnRetry.setOnClickListener {
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
                    mLinError.visibility = View.GONE
                    mProgress.visibility = View.VISIBLE
                    mBurgerList.visibility = View.GONE
                }
                is Resource.Error -> {
                    //display error message
                    mErrorMessage.text = resource.message
                    mLinError.visibility = View.VISIBLE
                    mProgress.visibility = View.GONE
                    mBurgerList.visibility = View.GONE
                }
                is Resource.Success -> {
                    //initialize list adapter with data
                    var adapter = BurgersRecyclerViewAdapter(this, resource.data ?: emptyList())
                    mBurgerList.adapter = adapter
                    mLinError.visibility = View.GONE
                    mProgress.visibility = View.GONE
                    mBurgerList.visibility = View.VISIBLE
                }
            }
        })
    }
}