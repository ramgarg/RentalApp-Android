package com.eazyrento.supporting

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.eazyrento.Constant
import com.eazyrento.EazyRantoApplication
import com.eazyrento.ValidationMessage
import com.google.android.gms.common.internal.service.Common
import java.io.ByteArrayOutputStream
import java.io.InputStream

class UploadImageFromDevice {
    //pic image
    lateinit var picImageBase64:OnPiclImageToBase64

    open fun pickImage(activity:Activity,onPiclImageToBase64:OnPiclImageToBase64): Unit {
        this.picImageBase64 = onPiclImageToBase64

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        activity.startActivityForResult(intent, Constant.PICK_PHOTO_FOR_AVATAR)

    }

     fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       // super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return
            }
            val imageStream: InputStream? =
                data.data?.let { EazyRantoApplication.context?.getContentResolver()?.openInputStream(it) }

            val selectedImage = BitmapFactory.decodeStream(imageStream)

            picImageBase64.onBitmap(selectedImage)

            // set Image in view and set base64 to send server

            picImageBase64.onBase64(encodeImage(selectedImage))

        }
    }

    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}

interface OnPiclImageToBase64{
    fun onBase64(string: String?)
    fun onBitmap(bm:Bitmap?)
}