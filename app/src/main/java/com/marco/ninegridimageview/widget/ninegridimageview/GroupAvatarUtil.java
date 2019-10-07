package com.marco.ninegridimageview.widget.ninegridimageview;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Marco
 * date : 2019/10/7 0007 21:35
 * description :
 */
public class GroupAvatarUtil {

    public static final int AVATAR_LIMIT = 9;

    static NineGridImageViewAdapter adapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String s) {

        }

        /**
         *
         * @param context
         * @param imageView
         * @param s 文本内容或图片url
         * @param uid 颜色值id
         * @param textSize   文字大小
         */
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String s, Integer uid,
                                      int textSize, float xRatio, float yRatio) {
            //if (s.contains("http://")) {
            if (Patterns.WEB_URL.matcher(s).matches()) { //判断有效url，图片
                Glide.with(context).load(s).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            } else {
                TextDrawable drawable = TextDrawable.builder()
                        .beginConfig()
                        .fontSize(dip2px(context, textSize))
                        .xRatio(xRatio)
                        .yRatio(yRatio)
                        .endConfig()
                        .buildRect(TextUtils.isEmpty(s) ? "" : s.substring(s.length() - 1), AvatarUtils.getColor(uid));
                imageView.setImageDrawable(drawable);
            }
        }

        //重写该方法自定义生成ImageView方式，用于九宫格头像中的一个个图片控件，可以设置ScaleType等属性
        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }
    };

    /**
     * 直接加载单张图片url
     */
    public static void setAvatar(NineGridImageView livAvatar, String avatarUrl) {
        List<String> list = new ArrayList<>();
        List<Integer> colorIdList = new ArrayList<>();
        list.add(avatarUrl);
        colorIdList.add(0);
        livAvatar.setAdapter(adapter);
        livAvatar.setUIdList(colorIdList);
        livAvatar.setImagesData(list);
    }

    /**
     * 设置多人群头像
     *
     * @param livAvatar   群头像控件
     * @param list        url或文本集合
     * @param colorIdList 颜色id集合
     * @param gap         宫格间距
     * @param textSize    文本大小
     */
    public static void setAvatar(NineGridImageView livAvatar, List list,
                                 List<Integer> colorIdList, int gap, int textSize) {
        livAvatar.setTextSize(textSize);
        livAvatar.setGap(gap); //设置宫格间距
        livAvatar.setAdapter(adapter);
        livAvatar.setUIdList(colorIdList);
        livAvatar.setImagesData(list);
    }

    /**
     * 设置学习群默认头像
     */
    public static void setAvatar(NineGridImageView iv, List<String> avatarList
            , List<Integer> uidList, int textSize) {

        List<String> list = new ArrayList<>();
        List<Integer> uidlist = new ArrayList<>();

        if (avatarList != null && avatarList.size() > 0) {
            //最多取前N名数据
            for (int i = 0; i < avatarList.size(); i++) {
                String avatarUrl = avatarList.get(i);
                list.add(avatarUrl);
                uidlist.add(uidList.size()>i?uidList.get(i):0);
                if (list.size() == AVATAR_LIMIT) {
                    break;
                }
            }
        }
        if (list.size() == 0 || list.size() == 3) {
            list.add("");
            uidlist.add(0);
        }

        setAvatar(iv, list, uidlist, 4, textSize);
    }

    public static void setAvatar(NineGridImageView iv, List<String> avatarList, List<Integer> uidList) {
        setAvatar(iv, avatarList, uidList, 18);
    }

    /*-------------------------辅助方法-------------------------------*/

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
