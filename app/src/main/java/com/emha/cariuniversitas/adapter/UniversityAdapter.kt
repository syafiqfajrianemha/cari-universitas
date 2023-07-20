package com.emha.cariuniversitas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emha.cariuniversitas.data.model.UniversityResponse
import com.emha.cariuniversitas.databinding.UniversityItemBinding

class UniversityAdapter : RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder>() {

    private val list = ArrayList<UniversityResponse>()

    fun setList(university: ArrayList<UniversityResponse>) {
        list.clear()
        list.addAll(university)
        notifyDataSetChanged()
    }

    class UniversityViewHolder(private val binding: UniversityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(university: UniversityResponse) {
            with(binding) {
                val checkAbbreviation =
                    if (university.abbreviation == "") "-" else university.abbreviation

                val text = "${university.university} ($checkAbbreviation)"
                tvUniversityName.text = text

                val checkAddress = if (university.address == "") "-" else university.address
                tvUniversityAddress.text = checkAddress
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val view = UniversityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UniversityViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        holder.bind(list[position])
    }
}