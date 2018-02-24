package com.example.pby.find.constant;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pby on 2018/2/7.
 */

public class ConstVariable {
    public static final String EXPRESSION_REDULAR = "image_[\\s\\S]*.png";

    public static final String DEFAULT_HEAD_URL = "https://upload.jianshu.io/users/upload_avatars/9124992/c56d68b9-89af-48a2-a93a-48e7dcac778f.jpg";

    public static final List<Integer> EXPERIENCE_LIST = Arrays.asList(10, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000, 1000000, 2000000, 5000000, 10000000, 20000000);

    public static final List<String> RANK_LIST = Arrays.asList("世外高人", "机智邮箱", "金针蘑菇", "顺丰水表", "求你别说", "图样森破"
            , "醒工之砖", "白富草莓", "高大富帅", "抠脚大汉", "闹太~套", "屌爆吓尿", "过多极爽", "真老毅丝", "给本老跪", "理据必服", "男默女泪", "内涵帝王");


    public static final int CODE_GET = 1;
    public static final int CODE_MORE = 2;

    public static final int CODE_ALL = 3;
    public static final int CODE_HOT = 4;

    public static final int CODE_USER = 5;

    public static final int CODE_HISTORY_HEAD = 6;

    public static final int CODE_UPDATE = 7;
    public static final int CODE_DELETE = 8;

    public static final int CODE_COLLECTION = 9;


    public static final String STRING_FOLLOW = "关注";
    public static final String STRING_NO_CANCEL = "取消关注";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PWD = "pwd";
    public static final String KEY_AUTO_LOGIN = "auto_login";
    public static final String KEY_NIGHT = "night";

    public static final String NAME_SHAREDPREFERENCE = "find";
}
