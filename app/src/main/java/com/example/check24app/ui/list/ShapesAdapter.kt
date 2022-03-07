package com.example.check24app.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.check24app.databinding.ItemShapeBinding
import com.example.check24app.domain.model.ShapesModel

class ShapesAdapter(val onPhotoSelected: (photo: ShapesModel, position: Int) -> Unit) : RecyclerView.Adapter<ShapesAdapter.PhotoViewHolder>() {

    private val photoItems: ArrayList<ShapesModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var binding = ItemShapeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoItems[position], position)
    }

    override fun getItemCount() = photoItems.size

    fun updateItems(shapes: List<ShapesModel>) {
        photoItems.clear()
        photoItems.addAll(shapes)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(val itemBinding: ItemShapeBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(photoModel: ShapesModel, position: Int) {
            itemBinding.apply {
                shapesItem = photoModel

                cardPhoto.setOnClickListener {
                    onPhotoSelected(photoModel, position)
                }
            }
        }
    }
}