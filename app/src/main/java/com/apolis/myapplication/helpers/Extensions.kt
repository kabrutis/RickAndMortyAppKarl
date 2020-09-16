package com.apolis.myapplication.helpers

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_layout_character.view.*

@BindingAdapter("loadImage")
fun ImageView.loadImage(urlString:String){
    Glide.with(this).load(urlString).into(image_view_character)
}