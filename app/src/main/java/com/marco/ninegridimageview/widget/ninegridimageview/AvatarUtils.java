package com.marco.ninegridimageview.widget.ninegridimageview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.regex.Pattern;

/**
 * @Author Marco
 * @Date 2019/10/7 0007
 * @Des 头像工具类
 */
public class AvatarUtils {

    private static final int[] COLORS = new int[]{
            0xFFFF83A5,
            0xFFFF83A5,
            0xFFDFBB60,
            0xFFDFBB60,
            0xFF46ABDF,
            0xFF46ABDF,
            0xFF2AC3A5,
            0xFF2AC3A5,
            0xFFDF815A,
            0xFFDF815A,
    };

    // 中文匹配
    private static final String REGEX_CN = "^[\\u4e00-\\u9fa5]$";
    // 英文匹配
    private static final String REGEX_EN = "^[a-zA-Z]+$";

    /**
     * 是否使用默认头像
     *
     * @param avatarUrl
     * @return
     */
    // http://**/avatar_default.png
    public static boolean isDefault(String avatarUrl) {
        return TextUtils.isEmpty(avatarUrl) || avatarUrl.endsWith("avatar_default.png");
    }

    /**
     * 根据用户名和 uid 获取默认头像（默认圆形）
     *
     * @param name 用户名
     * @param uid  uid
     * @return
     */
    public static Drawable getDrawable(String name, int uid) {
        return TextDrawable.builder().buildRound(getShortName(name), getColor(uid));
    }

    /**
     * 根据 uid 获取背景颜色
     *
     * @param uid
     * @return
     */
    @ColorInt public static int getColor(int uid) {
        return COLORS[uid % 10]; // 根据 uid 尾号决定背景颜色
    }

    /**
     * 获取短名称
     *
     * @param name
     * @return
     */
    public static String getShortName(String name) {
        if (TextUtils.isEmpty(name)) {
            return "";
        }
        // 英文，截取前面两位字母
        if (Pattern.matches(REGEX_EN, name)) {
            if (name.length() >= 2) {
                return name.substring(0, 2);
            } else {
                return name;
            }
        } else {
            // 中文，截取最后两位
            // 中英文混合，截取后面两个字
            if (name.length() >= 2) {
                return name.substring(name.length() - 2, name.length());
            } else {
                return name;
            }
        }
    }

    /**
     * 加载图片并以圆形显示
     *
     * @param iv  ImageView
     * @param url 图片url
     */
    public static void intoCircle(final ImageView iv, String url) {
        final Context context = iv.getContext();
        Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv);
    }

    /**
     * 加载图片并以圆形显示，使用默认头像作为占位图
     *
     * @param iv
     * @param name
     * @param uid
     * @param avatarUrl
     */
    public static void intoCircle(final ImageView iv, String name, int uid, String avatarUrl) {
        iv.setImageDrawable(getDrawable(name, uid));
        if (!isDefault(avatarUrl)) {
            intoCircle(iv, avatarUrl);
        }
    }

}
