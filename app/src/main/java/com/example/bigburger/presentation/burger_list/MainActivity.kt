package com.example.bigburger.presentation.burger_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
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

    private lateinit var mBurgerList: RecyclerView
    private lateinit var mErrorMessage: TextView
    private lateinit var mProgress: ProgressBar
    private lateinit var mLinError: LinearLayout
    private lateinit var mBtnRetry: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val burgerListViewModel: BurgerListViewModel by viewModels()

        //bind ui
        mBurgerList = findViewById(R.id.burger_list)
        mErrorMessage = findViewById(R.id.error_message)
        mProgress = findViewById(R.id.progress_circular)
        mLinError = findViewById(R.id.lin_error)
        mBtnRetry = findViewById(R.id.btn_retry)

        // set retry btn action
        mBtnRetry.setOnClickListener {
            burgerListViewModel.getBurgers(this)
        }

        //init list
        burgerListViewModel.getBurgers(this)
    }

    override fun onGetBurgersLoaded(burgers: List<Burger>) {
        //initialize list adapter with data
        var adapter = BurgersRecyclerViewAdapter(this, burgers)
        mBurgerList.adapter = adapter
        mLinError.visibility = View.GONE
        mProgress.visibility = View.GONE
        mBurgerList.visibility = View.VISIBLE
    }

    override fun onError(errorMessage: String) {
        //display error message
        mErrorMessage.text = errorMessage
        mLinError.visibility = View.VISIBLE
        mProgress.visibility = View.GONE
        mBurgerList.visibility = View.GONE
    }

    override fun onLoading() {
        //display progress bar
        mLinError.visibility = View.GONE
        mProgress.visibility = View.VISIBLE
        mBurgerList.visibility = View.GONE
    }
}