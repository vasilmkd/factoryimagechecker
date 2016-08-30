package com.vasilev.factoryimagechecker.util;

import android.support.annotation.NonNull;

public final class CheckerTags {

    private CheckerTags() {}

    private static final String SHAMU_N = "shamun";
    private static final String ANGLER_N = "anglern";
    private static final String VOLANTISG_N = "volantisgn";

    public static final String SHAMU_CHECKER_TAG = "shamu-checker";
    public static final String ANGLER_CHECKER_TAG = "angler-checker";
    public static final String VOLANTISG_CHECKER_TAG = "volantisg-checker";

    public static String match(@NonNull String tag) {
        switch (tag) {
            case SHAMU_CHECKER_TAG:
                return SHAMU_N;
            case ANGLER_CHECKER_TAG:
                return ANGLER_N;
            case VOLANTISG_CHECKER_TAG:
                return VOLANTISG_N;
            default:
                throw new IllegalArgumentException("Unknown tag: " + tag);
        }
    }
}
