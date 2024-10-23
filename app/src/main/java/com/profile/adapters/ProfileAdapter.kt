package com.profile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emptyprojectt1.R

data class ProfileOption(
    val title: String, val subtext: String
)

class ProfileAdapter(
    private val context: Context, private val profileOptions: List<ProfileOption>
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOptionTitle: TextView = itemView.findViewById(R.id.tv_option_title)
        val tvOptionSubtext: TextView = itemView.findViewById(R.id.tv_option_subtext)
        val ivArrow: ImageView = itemView.findViewById(R.id.iv_arrow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_profile_option, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val option = profileOptions[position]
        holder.tvOptionTitle.text = option.title
        holder.tvOptionSubtext.text = option.subtext
        // Set click listener if needed
        holder.itemView.setOnClickListener {
            // Handle click events
        }
    }

    override fun getItemCount(): Int {
        return profileOptions.size
    }
}
