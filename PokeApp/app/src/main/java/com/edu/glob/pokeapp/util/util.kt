package com.edu.glob.pokeapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edu.glob.pokeapp.R

fun getProgressDrawable(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth= 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(imageUrl: String?, progressDrawable: CircularProgressDrawable){
    val id= imageUrl?.extractPokemonId()
    val imagePath = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(imagePath)
        .into(this)

}

fun ImageView.loadImageFromSprite(imageUrl: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(imageUrl)
        .into(this)
}


fun String.extractPokemonId() = this.substringAfter("pokemon").replace("/", "").toInt()

