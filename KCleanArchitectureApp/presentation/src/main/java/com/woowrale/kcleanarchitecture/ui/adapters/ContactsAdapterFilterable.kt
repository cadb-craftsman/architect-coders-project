package com.woowrale.kcleanarchitecture.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.R
import java.util.*

class ContactsAdapterFilterable(
    private val context: Context,
    private val contactList: List<Contact>,
    private val listener: ContactsAdapterListener
) : RecyclerView.Adapter<ContactsAdapterFilterable.ViewHolder>(), Filterable {

    private lateinit var contactListFiltered: List<Contact>

    init {
        contactListFiltered = contactList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_row_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactListFiltered!![position]
        holder.name.setText(contact.name)
        holder.phone.setText(contact.phone)

        Glide.with(context)
            .load(contact.profileImage)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return contactListFiltered!!.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    contactListFiltered = contactList
                } else {
                    val filteredList = ArrayList<Contact>()
                    for (row in contactList) {
                        if (row.name!!.toLowerCase().contains(charString.toLowerCase()) || row.phone!!.contains(
                                charSequence
                            )
                        ) {
                            filteredList.add(row)
                        }
                    }

                    contactListFiltered = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = contactListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                contactListFiltered = filterResults.values as ArrayList<Contact>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var name: TextView
        lateinit  var phone: TextView
        lateinit var thumbnail: ImageView

        init {
            name = view.findViewById(R.id.name)
            phone = view.findViewById(R.id.phone)
            thumbnail = view.findViewById(R.id.thumbnail)

            view.setOnClickListener {
                listener.onContactSelected(contactListFiltered!![adapterPosition])
            }
        }
    }

    interface ContactsAdapterListener {
        fun onContactSelected(contact: Contact)
    }
}