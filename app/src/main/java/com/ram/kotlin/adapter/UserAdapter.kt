package com.ram.kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ram.kotlin.R
import com.ram.kotlin.db.Users
import kotlinx.android.synthetic.main.user_item.view.*


class UserAdapter() :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>(), Filterable {

    var items = ArrayList<Users>()
    var filterItems = ArrayList<Users>()

    fun setListData(data: ArrayList<Users>) {
        this.items = data
        this.filterItems = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)

        return MyViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(filterItems[position])
    }

    override fun getItemCount(): Int {
        return filterItems.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name = view.text_view_name
        val email = view.text_view_email
        val number = view.text_view_mobile
        fun bind(data: Users) {
            name.text = data.name
            email.text = data.email
            number.text = data.number


        }
    }

    //For Searching the contact
    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filterItems = items
                } else {
                    val filteredList: ArrayList<Users> = ArrayList()
                    for (row in items) {


                        // here we are looking for name or phone number match
                        if (row.name.toLowerCase()
                                .contains(charString.toLowerCase()) || row.number
                                .contains(charSequence)
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filterItems = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filterItems
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filterItems = filterResults.values as ArrayList<Users>
                notifyDataSetChanged()
            }
        }
    }

}