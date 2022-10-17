package shalini.mvvm.vsco.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;

import hilt_aggregated_deps._shalini_mvvm_vsco_di_VSCOServiceModule;
import shalini.mvvm.vsco.R;

/**
 * This is used to setup a custom android tag for the XML. So that the hit image can be displayed
 * using a simple tag in the XML. Glide library is used for retrieving images since it has a very good built in caching
 * system in place. Should revisit this to see how to use dependency injection to initialize this.
 */
public class Binders {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide
                .with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.ic_default_image)
                .error(R.drawable.ic_error_image)
                .into(imageView);
    }


    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, Integer width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) width;
        view.setLayoutParams(layoutParams);
    }
}
