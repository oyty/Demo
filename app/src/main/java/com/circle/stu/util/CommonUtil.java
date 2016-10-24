package com.circle.stu.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by oyty on 10/24/16.
 */

public class CommonUtil {

    /**
     * 验证手机号是否有效（大陆）
     */
    public static boolean isMobileValid(String mobile) {
        if (TextUtils.isEmpty(mobile) || !TextUtils.isDigitsOnly(mobile)) {
            return false;
        }
        return Pattern.matches("^(\\+86)?(1[3-9][0-9])\\d{8}$", mobile);
    }

}
