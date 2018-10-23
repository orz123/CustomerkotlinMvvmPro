package com.orz.kotlin_mvvm_demo.glide

import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.GlideModule
import com.orz.kotlin_mvvm_demo.config.Constants


/**
 *
 * @author orz
 */
class GlideConfig : GlideModule {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, Constants.MAX_CACHE_DISK_SIZE.toLong()))
        builder.setMemoryCache(LruResourceCache(Runtime.getRuntime().maxMemory() / 4))
        builder.setBitmapPool(LruBitmapPool(Runtime.getRuntime().maxMemory() / 4))
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565)
    }


    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {

    }

    companion object {
        val TAG = "GlideConfig"
    }
}