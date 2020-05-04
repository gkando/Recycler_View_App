package com.example.recyclerviewapp

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(private val photos: ArrayList<Photo>) : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return PhotoHolder(inflatedView)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
        val itemPhoto = photos[position]
        holder.bindPhoto(itemPhoto)
    }

    //1
    class PhotoHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        //2
        private var photo: Photo? = null

        //3
        init {
            view.setOnClickListener(this)
        }

        fun bindPhoto(photo: Photo) {
            this.photo = photo
            Picasso.with(view.context).load(photo.url).into(view.itemImage)
            view.itemDate.text = photo.humanDate
            view.itemDescription.text = photo.explanation
        }

        //4
        override fun onClick(v: View) {
            val context = itemView.context
            val showPhotoIntent = Intent(context, PhotoActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, photo)
            context.startActivity(showPhotoIntent)
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }

}