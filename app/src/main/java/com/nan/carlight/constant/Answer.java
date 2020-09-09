package com.nan.carlight.constant;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        Answer.UNKNOWN,
        Answer.DIPPED_HEADLIGHT,
        Answer.HIGH_BEAM,
        Answer.CLEARANCE_LAMP,
        Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM,
        Answer.CLOSE_ALL
})
@Retention(RetentionPolicy.SOURCE)
public @interface Answer {
    int UNKNOWN = -1;
    int DIPPED_HEADLIGHT = 1;
    int HIGH_BEAM = 2;
    int CLEARANCE_LAMP = 3;
    int DIPPED_HEADLIGHT_AND_HIGH_BEAM = 4;
    int CLOSE_ALL = 5;
}
