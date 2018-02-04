package com.example.hawk.usj_realm.Helpers;

import android.os.Binder;

/**
 * Created by Chard on 2/4/18.
 */

public class ObjectWrapperForBinder extends Binder {

    private final Object mData;

    public ObjectWrapperForBinder(Object data) {
        mData = data;
    }

    public Object getData() {
        return mData;
    }
}
