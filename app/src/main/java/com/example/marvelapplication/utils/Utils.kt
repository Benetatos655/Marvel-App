package com.example.marvelapplication.utils

import android.content.Context
import androidx.annotation.DimenRes
import com.example.marvelapplication.R

object Utils {

    fun imageFormatChooser(height: Float, weight: Float,context: Context):String{
        return when (height){

            convertPxToDp(context,context.resources.getDimension(R.dimen.standard_medium_height)) -> "/standard_medium.jpg"

            convertPxToDp(context,context.resources.getDimension(R.dimen.standard_large_height)) -> "/standard_large.jpg"

            convertPxToDp(context,context.resources.getDimension(R.dimen.standard_xlarge_height)) -> "/standard_xlarge.jpg"

            convertPxToDp(context,context.resources.getDimension(R.dimen.standard_fantastic_height)) -> "/standard_fantastic.jpg"

            else-> "/standard_amazing.jpg"
        }

    }

    fun convertPxToDp(context:Context,somePxValue:Float ): Float {
        val density: Float = context.resources.displayMetrics.density
        return somePxValue / density
    }

    fun Context.px(@DimenRes dimen: Int): Int = resources.getDimension(dimen).toInt()

    fun Context.dp(@DimenRes dimen: Int): Float = px(dimen) / resources.displayMetrics.density
}