package shalini.mvvm.vsco.utils;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

import shalini.mvvm.vsco.R;

/**
 * AppGlideModule needs to be extended to set the default values
 * for all glide calls
 */
public class VSCOGlideModule extends AppGlideModule {

    /**
     * Setting the disk cache strategy for glide. Setting it to 10 mb for now
     * the list displays the small size pictures. All this should help
     * with the bandwidth
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        int memoryCacheSizeBytes = 1024 * 1024 * 10; // 10mb
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, memoryCacheSizeBytes));
        builder.setDefaultRequestOptions(requestOptions(context));
    }

    /**
     * All glide calls will have these default options set when making a call
     * There are place holder and error images set here.
     */

    private static RequestOptions requestOptions(Context context){
        return new RequestOptions()
                .signature(new ObjectKey(
                        System.currentTimeMillis() / (24 * 60 * 60 * 1000)))
                .centerCrop()
                .placeholder(R.drawable.ic_default_image)
                .error(R.drawable.ic_error_image)
                .encodeFormat(Bitmap.CompressFormat.PNG)
                .encodeQuality(100)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false);
    }
}
