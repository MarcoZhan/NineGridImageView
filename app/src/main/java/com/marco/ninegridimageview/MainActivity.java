package com.marco.ninegridimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marco.ninegridimageview.widget.ninegridimageview.GroupAvatarUtil;
import com.marco.ninegridimageview.widget.ninegridimageview.NineGridImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NineGridImageView mIvGroupAvatar;
    NineGridImageView mIvGroupAvatar0;
    NineGridImageView mIvGroupAvatar1;
    NineGridImageView mIvGroupAvatar2;
    NineGridImageView mIvGroupAvatar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvGroupAvatar = findViewById(R.id.iv_group_avatar);
        mIvGroupAvatar0 = findViewById(R.id.iv_group_avatar0);
        mIvGroupAvatar1 = findViewById(R.id.iv_group_avatar1);
        mIvGroupAvatar2 = findViewById(R.id.iv_group_avatar2);
        mIvGroupAvatar3 = findViewById(R.id.iv_group_avatar3);
        setAvatarData();

    }

    /**
     * 效果测试
     */
    private void setAvatarData() {
        List<String> avatarList = new ArrayList<>();
        avatarList.add("https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg");
        avatarList.add("https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a62e824376d98d1069d40a31113eb807/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg");
        avatarList.add("");
        avatarList.add("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=0c78105b888ba61ec0eece2f713597cc/0e2442a7d933c8956c0e8eeadb1373f08202002a.jpg");
        avatarList.add("https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a8d97dc3c11b9d1695c79c61c3deb4eb/bba1cd11728b4710670d12d0cccec3fdfc032388.jpg");
        avatarList.add("http://img4.imgtn.bdimg.com/it/u=2610329675,1027042541&fm=26&gp=0.jpg");
        avatarList.add("");
        avatarList.add("http://img1.imgtn.bdimg.com/it/u=1321463267,128419202&fm=26&gp=0.jpg");
        avatarList.add("http://img0.imgtn.bdimg.com/it/u=4246326797,2657995307&fm=26&gp=0.jpg");
        avatarList.add("http://b.hiphotos.baidu.com/image/h%3D300/sign=05b297ad39fa828bce239be3cd1e41cd/0eb30f2442a7d9337119f7dba74bd11372f001e0.jpg");

        List<Integer> uidList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            uidList.add(i + 2000);
            if (uidList.size() == 2) {
                GroupAvatarUtil.setAvatar(mIvGroupAvatar2, avatarList.subList(0, 2), uidList);
            } else if (uidList.size() == 3) {
                GroupAvatarUtil.setAvatar(mIvGroupAvatar1, avatarList.subList(0, 3), uidList);
            } else if (uidList.size() == 4) {
                GroupAvatarUtil.setAvatar(mIvGroupAvatar0, avatarList.subList(0, 4), uidList);
            }
        }

        GroupAvatarUtil.setAvatar(mIvGroupAvatar3, avatarList.get(0));
        if (avatarList.size() < 2) {
            GroupAvatarUtil.setAvatar(mIvGroupAvatar, avatarList.get(0));
        } else {
            //设置默认多人头像
            GroupAvatarUtil.setAvatar(mIvGroupAvatar, avatarList, uidList);
        }

    }

}
