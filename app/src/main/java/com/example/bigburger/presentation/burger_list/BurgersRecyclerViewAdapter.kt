package com.example.bigburger.presentation.burger_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigburger.R
import com.example.bigburger.domain.model.Burger
import java.text.NumberFormat
import java.util.*


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class BurgersRecyclerViewAdapter(
    private val context: Context,
    private val values: List<Burger>
) : RecyclerView.Adapter<BurgersRecyclerViewAdapter.BurgerViewHolder>() {

    val formatter1: NumberFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurgerViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.burger_item, parent, false)
        return BurgerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BurgerViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.description.text = item.description
        holder.price.text = formatter1.format(item.price / 100)
        Glide.with(context).load(item.thumbnail).into(holder.thumbnail)
    }

    override fun getItemCount(): Int = values.size

    inner class BurgerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val price: TextView = itemView.findViewById(R.id.price)
        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
    }

}