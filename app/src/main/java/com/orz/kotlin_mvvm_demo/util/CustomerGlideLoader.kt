package com.orz.kotlin_mvvm_demo.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.orz.kotlin_mvvm_demo.App
import com.youth.banner.loader.ImageLoader
import java.io.File

/**
 * Created by wd on 2018/3/1.
 * 重写banner图片加载器
 */
class CustomerGlideLoader : ImageLoader() {

    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        var uri: Uri? = null
        if (path is String) {
            uri = Uri.parse(path)
        } else if (path is Int) {
            uri = getResUri(path)
        } else if (path is Uri) {
            uri = path
        } else if (path is File) {
            uri = Uri.fromFile(path)
        }
        if (uri == null) {
            return
        }

        val requestOptions = RequestOptions()
                .centerCrop()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        val transitionOptions = DrawableTransitionOptions.withCrossFade(1000)
        Glide.with(context).load(uri)
                .transition(transitionOptions)
                .apply(requestOptions)
                .into(imageView)
    }

    companion object {
        private var instant:CustomerGlideLoader?=null
        fun getInstance():CustomerGlideLoader{
            if (instant == null){
                synchronized(CustomerGlideLoader::class.java){
                    if (instant == null){
                        instant = CustomerGlideLoader()
                    }
                }
            }
            return instant as CustomerGlideLoader
        }
        @JvmOverloads
        fun getResUri(resId: Int, packageName: String = App.instance().packageName): Uri {
            return if (resId <= 0) {
                Uri.EMPTY
            } else Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + packageName + "/" + App.instance().resources.getResourceTypeName(resId)
                    + "/" + App.instance().resources.getResourceEntryName(resId))
        }
    }
}
