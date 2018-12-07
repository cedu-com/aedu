package net.chinaedu.aedu.image.loader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.GifTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.Target;


/**
 * @author huangshihai@chinaedu.com
 */

public class AeduGlideImageLoader extends AbstractAeduImageLoader {

    @Override
    void into(Context context, ImageView target, AeduRequestCreator creator) {
        GenericRequestBuilder builder = null;
        if (creator.isGif()) {
            if (null != creator.getUri()) {
                builder = Glide.with(context).load(creator.getUri()).asGif();
            } else {
                builder = Glide.with(context).load(creator.getDrawableRes()).asGif();
            }
        } else {
            if (null != creator.getUri()) {
                builder = Glide.with(context).load(creator.getUri());
            } else {
                builder = Glide.with(context).load(creator.getDrawableRes());
            }
        }
        if (null != creator.getPlaceHolder()) {
            builder.placeholder(creator.getPlaceHolder());
        }
        if (null != creator.getErrorDrawable()) {
            builder.error(creator.getErrorDrawable());
        }
        if (creator.getResizeWidth() > 0 && creator.getResizeHeight() > 0) {
            builder.override(creator.getResizeWidth(), creator.getResizeHeight());
        }
        if (null != builder) {
            builder.into(target);
        }
    }

    @Override
    public void pauseRequest(Context context) {
        Glide.with(context).pauseRequests();
    }

    @Override
    public void resumeRequest(Context context) {
        Glide.with(context).resumeRequests();
    }

    @Override
    public void destoryRequest(Context context) {
        Glide.with(context).onDestroy();
    }
}
