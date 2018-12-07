package net.chinaedu.aedu.image.loader;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

/**
 * Created by MartinKent on 2017/4/26.
 */

public class AeduPicassoImageLoader extends AbstractAeduImageLoader {

    AeduPicassoImageLoader() {

    }


    @Override
    void into(Context context, ImageView target, AeduRequestCreator creator) {
        Picasso picasso = Picasso.with(context);
        RequestCreator requestCreator = null;
        if (null != creator.getUri()) {
            requestCreator = picasso.load(creator.getUri());
        } else if (0 < creator.getDrawableRes()) {
            requestCreator = picasso.load(creator.getDrawableRes());
        }
        if (creator.isGif()) {
            System.err.println("AeduRequestCreator.asGif is not supported.");
        }
        if (null != requestCreator) {
            if (null != creator.getPlaceHolder()) {
                requestCreator.placeholder(creator.getPlaceHolder());
            }
            if (null != creator.getErrorDrawable()) {
                requestCreator.error(creator.getErrorDrawable());
            }
            if (creator.getResizeWidth() > 0 && creator.getResizeHeight() > 0) {
                requestCreator.resize(creator.getResizeWidth(), creator.getResizeHeight());
            }
            requestCreator.into(target);
        }
    }

    @Override
    public void pauseRequest(Context context) {
//        Picasso.with(context).cancelRequest(tar);
    }

    @Override
    public void resumeRequest(Context context) {

    }

    @Override
    public void destoryRequest(Context context) {
        Picasso.with(context).shutdown();
    }
}
