package ua.com.skywell.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Alex Poltavets on 02.11.2016.
 */

public class BindingUtil {

    @BindingAdapter("bind:loadImage")
    public static void loadImage(ImageView view,String url){
        Picasso.with(view.getContext())
                    .load(url)
                    .into(view);
    }
}
