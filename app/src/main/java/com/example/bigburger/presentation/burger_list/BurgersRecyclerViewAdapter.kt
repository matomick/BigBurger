package com.example.bigburger.presentation.burger_list

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bigburger.R
import com.example.bigburger.domain.model.Burger


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class BurgersRecyclerViewAdapter(
    private val context : Context,
    private val values: List<Burger>
) : RecyclerView.Adapter<BurgersRecyclerViewAdapter.BurgerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurgerViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.burger_item, parent, false)
        return BurgerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BurgerViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.title
        holder.contentView.text = item.price.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class BurgerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: TextView = itemView.findViewById(R.id.item_number)
        val contentView: TextView = itemView.findViewById(R.id.content)
    }

}