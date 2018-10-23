package com.orz.kotlin_mvvm_demo.util

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import com.orz.kotlin_mvvm_demo.App

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


/**
 * bitmap处理工具类
 * Created by Orz
 */
object BitmapUtil {

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(

                drawable.intrinsicWidth,

                drawable.intrinsicHeight,

                if (drawable.opacity != PixelFormat.OPAQUE)
                    Bitmap.Config.ARGB_8888
                else
                    Bitmap.Config.RGB_565)

        val canvas = Canvas(bitmap)

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)

        drawable.draw(canvas)

        return bitmap
    }

    fun saveBitmap(bitmap: Bitmap, dir: String, name: String, isShowPhotos: Boolean): Boolean {
        val path = File(dir)
        if (!path.exists()) {
            path.mkdirs()
        }
        val file = File(path.toString() + "/" + name)
        if (file.exists()) {
            file.delete()
        }
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }

        } else {
            return true
        }
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                    fileOutputStream)
            fileOutputStream.flush()

        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            try {
                fileOutputStream!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        // 其次把文件插入到系统图库
        if (isShowPhotos) {
            try {
                MediaStore.Images.Media.insertImage(App.instance().contentResolver,
                        file.absolutePath, name, null)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            // 最后通知图库更新
            App.instance().sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://$file")))
        }

        return true
    }
}
