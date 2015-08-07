/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock.sample.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import me.zhanghai.patternlock.sample.R;
import me.zhanghai.patternlock.sample.util.PatternLockUtils;
import me.zhanghai.patternlock.sample.util.PreferenceContract;

public class SetPatternPreference extends Preference
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    public SetPatternPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SetPatternPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SetPatternPreference(Context context) {
        super(context);
    }

    @Override
    protected void onAttachedToHierarchy(PreferenceManager preferenceManager) {
        super.onAttachedToHierarchy(preferenceManager);

        PreferenceManager.getDefaultSharedPreferences(getContext())
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPrepareForRemoval() {
        super.onPrepareForRemoval();

        PreferenceManager.getDefaultSharedPreferences(getContext())
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public CharSequence getSummary() {
        Context context = getContext();
        return PatternLockUtils.hasPattern(context) ?
                context.getString(R.string.pref_summary_set_pattern_has) :
                context.getString(R.string.pref_summary_set_pattern_none);
    }

    @Override
    protected void onClick() {
        PatternLockUtils.setPatternByUser(getContext());
    }

    @Override
    public boolean shouldDisableDependents() {
        return super.shouldDisableDependents() || !PatternLockUtils.hasPattern(getContext());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (TextUtils.equals(key, PreferenceContract.KEY_PATTERN_SHA1)) {
            notifyChanged();
            notifyDependencyChange(shouldDisableDependents());
        }
    }
}
