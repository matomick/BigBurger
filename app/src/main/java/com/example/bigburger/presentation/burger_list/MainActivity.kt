package com.example.bigburger.presentation.burger_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.bigburger.R
import com.example.bigburger.domain.model.Burger
import com.example.bigburger.presentation.burger_list.BurgerListViewModel
import com.example.bigburger.presentation.burger_list.GetBigBurgersCallback
import com.example.bigburger.presentation.burger_list.BurgersRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), GetBigBurgersCallback {

    private lateinit var mBurgerList : RecyclerView
    private lateinit var mErrorMessage : TextView
    private lateinit var mProgress : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val burgerListViewModel: BurgerListViewModel by viewModels()

        mBurgerList = findViewById(R.id.burger_list)
        mErrorMessage = findViewById(R.id.error_message)
        mProgress = findViewById(R.id.progress_circular)

        //init list
        burgerListViewModel.getBurgers(this)

    }

    override fun onPostsLoaded(burgers: List<Burger>) {
        var adapter = BurgersRecyclerViewAdapter(this, burgers)
        mBurgerList.adapter = adapter
        mErrorMessage.visibility = View.GONE
        mProgress.visibility = View.GONE
    }

    override fun onError(errorMessage: String) {
        mErrorMessage.visibility = View.GONE
        mErrorMessage.text = errorMessage
        mProgress.visibility = View.GONE
    }

    override fun onLoading() {
        mErrorMessage.visibility = View.GONE
        mProgress.visibility = View.VISIBLE
    }
}