package com.marco.ninegridimageview.widget.ninegridimageview;

import android.content.Context;
import android.widget.ImageView;

/**
 * author : Marco
 * date : 2019/10/7 0007 09:28
 * desc: 九宫格图片控件适配器
 */
public abstract class NineGridImageViewAdapter <T> {
    /**
     * 重写该方法，使用任意第三方图片加载工具加载图片
     */
    protected abstract void onDisplayImage(Context context, ImageView imageView, T t);

    /**
     * @param context
     * @param imageView
     * @param t
     * @param colorId    颜色值id
     * @param textSize   文字大小
     */
    protected abstract void onDisplayImage(Context context, ImageView imageView, T t
            , Integer colorId, int textSize, float xRatio, float yRatio);

    /**
     * 重写该方法自定义生成ImageView方式，用于九宫格头像中的一个个图片控件，可以设置ScaleType等属性
     */
    protected ImageView generateImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

}
