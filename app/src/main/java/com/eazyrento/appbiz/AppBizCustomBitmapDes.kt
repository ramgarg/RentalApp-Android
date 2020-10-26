package com.eazyrento.appbiz

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.View.MeasureSpec
import android.widget.ImageView
import android.widget.TextView
import com.eazyrento.R
import com.google.maps.android.ui.IconGenerator


interface AppBizCustomBitmapDes {

    companion object{

        //        val drawle =  ContextCompat.getDrawable(this@DisplayMapsActivity , R.drawable.ic_tracker)
        fun getMarkerIconFromDrawable(drawable: Drawable): Bitmap? {


            val canvas = Canvas()
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            canvas.setBitmap(bitmap)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            drawable.draw(canvas)
            return bitmap
            //return BitmapDescriptorFactory.fromBitmap(bitmap)
        }

        /*fun getBitmapFromDrawable(icon :Int): BitmapDescriptor? {

            return BitmapDescriptorFactory.fromResource(icon)
        }*/

        /*fun getBitmapFromView(view: View, activity: Activity, callback: (Bitmap) -> Unit) {
            activity.window?.let { window ->
                val bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
                val locationOfViewInWindow = IntArray(2)
                view.getLocationInWindow(locationOfViewInWindow)
                try {
                    PixelCopy.request(
                        window, Rect(
                            locationOfViewInWindow[0],
                            locationOfViewInWindow[1],
                            locationOfViewInWindow[0] + 400,
                            locationOfViewInWindow[1] + 400
                        ), bitmap, { copyResult ->
                            if (copyResult == PixelCopy.SUCCESS) {
                                callback(bitmap)
                            }
                            // possible to handle other result codes ...
                        }, Handler()
                    )
                } catch (e: IllegalArgumentException) {
                    // PixelCopy may throw IllegalArgumentException, make sure to handle it
                    e.printStackTrace()
                }
            }
        }*/

        fun getBitmapFromView(view: View, defaultColor: Int): Bitmap? {
            val bitmap =
                Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawColor(defaultColor)
            view.draw(canvas)
            return bitmap
        }

         fun getBitmapFromView(view: View, width: Int, height: Int): Bitmap? {

             val bitmap =
                Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
             val canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }

        /*fun getBitmapFromDrawable(icon :Int): BitmapDescriptor? {

            return BitmapDescriptorFactory.fromResource(icon)
        }

        fun getBitmapDescriptorFactoryFromBitmap(bitmap:Bitmap): BitmapDescriptor? {
            return BitmapDescriptorFactory.fromBitmap(bitmap)
        }*/

        fun getMarkerIconWithLabel(label: String?, angle: Float, mContext: Context): Bitmap? {
            val iconGenerator = IconGenerator(mContext)
            val markerView: View = LayoutInflater.from(mContext).inflate(
                R.layout.map_marker_custom_view,
                null
            )
            val imgMarker: ImageView = markerView.findViewById(R.id.marker_map)
            val tvLabel = markerView.findViewById<TextView>(R.id.km_map)
            //imgMarker.setImageResource(R.mipmap.location)
            imgMarker.rotation = 26.6f
           // tvLabel.text = label
            iconGenerator.setContentView(markerView)
            iconGenerator.setBackground(null)
            return iconGenerator.makeIcon(label)
        }

        fun makeBitmap(context: Context, text: String): Bitmap? {
            val resources: Resources = context.resources
            val scale: Float = resources.getDisplayMetrics().density
            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.location)
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
            val canvas = Canvas(bitmap)
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            paint.setColor(Color.RED) // Text color
            paint.setTextSize(20 * scale) // Text size
            paint.setShadowLayer(1f, 0f, 1f, Color.WHITE) // Text shadow
            val bounds = Rect()
            paint.getTextBounds(text, 0, text.length, bounds)
            val x = bitmap.width - bounds.width() - 10 // 10 for padding from right
            val y = bounds.height()
            canvas.drawText(text, x.toFloat(), y.toFloat(), paint)
            return bitmap
        }

        fun loadBitmapFromView(v: View,w: Int,h:Int): Bitmap? {

            if (v.measuredHeight <= 0) {
                //v.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val specWidth = MeasureSpec.makeMeasureSpec(0 /* any */, MeasureSpec.UNSPECIFIED)
                v.measure(specWidth, specWidth)

                val b =
                    Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
                val c = Canvas(b)
                v.layout(0, 0, w, h)
                v.draw(c)
                return b
            }
            return null
        }

    }
}