package com.example.bigburger.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.bigburger.R
import com.example.bigburger.domain.model.Burger
import com.example.bigburger.presentation.burger_list.BurgerListViewModel
import com.example.bigburger.presentation.burger_list.GetBigBurgersCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), GetBigBurgersCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val burgerListViewModel: BurgerListViewModel by viewModels()

        //init list
        burgerListViewModel.getBurgers(this)
    }

    override fun onPostsLoaded(posts: List<Burger>) {
        TODO("Not yet implemented")
    }

    override fun onError(errorMessage: String) {
        TODO("Not yet implemented")
    }

    override fun onLoading() {
        TODO("Not yet implemented")
    }
}