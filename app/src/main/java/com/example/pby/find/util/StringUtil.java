package com.example.pby.find.util;

import java.util.List;

/**
 * Created by pby on 2018/2/21.
 */

public class StringUtil {

    public static String getTitleFromContent(String content, List<String> imageList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (content != null) {
            stringBuilder.append(content);
        }
        if (imageList != null && imageList.size() != 0) {
            for (int i = 0; i < imageList.size(); i++) {
                stringBuilder.append("[图片]");
            }
        }
        return stringBuilder.toString();
    }
}
