package com.nan.carlight.constant;

import androidx.collection.ArrayMap;

import java.util.Map;

public class AnswerConstant {

    public static Map<Integer, String> sAnswerStringMap;

    static {
        sAnswerStringMap = new ArrayMap<>();
        sAnswerStringMap.put(Answer.UNKNOWN, "未知");
        sAnswerStringMap.put(Answer.DIPPED_HEADLIGHT, "开启近光灯");
        sAnswerStringMap.put(Answer.HIGH_BEAM, "开启远光灯");
        sAnswerStringMap.put(Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM, "交替使用远近光灯");
        sAnswerStringMap.put(Answer.CLEARANCE_LAMP, "关闭大灯，开启示廓灯");
        sAnswerStringMap.put(Answer.CLOSE_ALL, "关闭所有灯光");
    }

}
