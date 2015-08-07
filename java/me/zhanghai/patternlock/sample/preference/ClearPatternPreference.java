/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock.sample.preference;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import me.zhanghai.patternlock.sample.R;
import me.zhanghai.patternlock.sample.util.PatternLockUtils;
import me.zhanghai.patternlock.sample.util.ToastUtils;

public class ClearPatternPreference extends DialogPreference {


    public ClearPatternPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClearPatternPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            Context context = getContext();
            PatternLockUtils.clearPattern(context);
            ToastUtils.show(R.string.pattern_cleared, context);
        }
    }
}
