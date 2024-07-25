package com.burakhkahraman.itunesnavigation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.burakhkahraman.itunesnavigation.databinding.ItunesItemRowBinding
import com.burakhkahraman.itunessearch.network.response.Result


class ITunesAdapter(private var dataSet: List<Result>) :
    RecyclerView.Adapter<ITunesAdapter.ViewHolder>() {




//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val artistName: TextView = view.findViewById(R.id.txtArtistName)
//        val collectionName: TextView = view.findViewById(R.id.txtCollectionName)
//        val collectionPrice: TextView = view.findViewById(R.id.txtCollectionPrice)
//        val imageView: ImageView = view.findViewById(R.id.imageView)
//    }
     class ViewHolder(val binding: ItunesItemRowBinding) : RecyclerView.ViewHolder(binding.root){
         val artistName: TextView
        val collectionName: TextView
        val collectionPrice: TextView
        val imageView: ImageView

    init {
        artistName = binding.txtArtistName
        collectionName = binding.txtCollectionName
        collectionPrice = binding.txtCollectionPrice
        imageView = binding.imageView
    }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.itunes_item_row, parent, false)
//        return ViewHolder(view)

//        val binding = ItunesItemRowBinding
//            .inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)

        val binding = ItunesItemRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val result = dataSet[position]

        holder.artistName.text = result.artistName
        holder.collectionName.text = result.collectionName
        holder.collectionPrice.text = result.collectionPrice.toString()
        Glide.with(holder.imageView.context)
            .load(result.artworkUrl100)
            .circleCrop()
            .into(holder.imageView)

            holder.itemView.setOnClickListener {
                val action = ITunesListDirections.actionITunesListToITunesDetail(dataSet[position])
                it.findNavController().navigate(action)
            }

    }

    override fun getItemCount() = dataSet.size

    fun updateData(newDataSet: List<Result>) {
        this.dataSet = newDataSet
        notifyDataSetChanged()
    }
}
