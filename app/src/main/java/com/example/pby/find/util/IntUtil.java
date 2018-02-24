package com.example.pby.find.util;

import android.support.constraint.ConstraintLayout;

import com.example.net.constant.ConstantVariable;
import com.example.pby.find.constant.ConstVariable;

/**
 * Created by pby on 2018/2/10.
 */

public class IntUtil {
    public static int buildLevel(int experience) {
        int level = 0;
        for (int i = 0; i < ConstVariable.EXPERIENCE_LIST.size(); i++) {
            if (ConstVariable.EXPERIENCE_LIST.get(i) > experience) {
                level = i + 1;
                break;
            }
        }
        return  level;
    }
}
