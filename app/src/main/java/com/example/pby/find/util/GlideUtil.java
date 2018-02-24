package com.example.pby.find.util;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.example.pby.find.R;
import com.example.pby.find.glide.transform.GlideCircleTransform;

/**
 * Created by pby on 2018/2/21.
 */

public class GlideUtil {
    private static RequestOptions headOptions = null;

    public static RequestOptions buildHeadOptions() {
        if (headOptions == null) {
            headOptions = RequestOptions.circleCropTransform().apply(new RequestOptions().error(R.mipmap.image_default_head));
        }
        return headOptions;
    }
}
