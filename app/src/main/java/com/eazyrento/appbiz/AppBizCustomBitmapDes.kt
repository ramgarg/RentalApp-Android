package com.eazyrento.appbiz

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.eazyrento.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


interface AppBizCustomBitmapDes {
    companion object{

//        val drawle =  ContextCompat.getDrawable(this@DisplayMapsActivity , R.drawable.ic_tracker)
         fun getMarkerIconFromDrawable(drawable: Drawable): BitmapDescriptor? {


            val canvas = Canvas()
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            canvas.setBitmap(bitmap)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            drawable.draw(canvas)
            return BitmapDescriptorFactory.fromBitmap(bitmap)
        }

        fun getBitmapFromDrawable(icon :Int): BitmapDescriptor? {

            return BitmapDescriptorFactory.fromResource(icon)
        }
    }
}