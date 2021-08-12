package com.android.example.superheroindex.util;

import android.os.Binder;

import com.android.example.superheroindex.model.Hero;
import com.android.example.superheroindex.model.MyPojo;

public class ObjectWrapperForBinder extends Binder {

        private final Hero mData;

        public ObjectWrapperForBinder(Hero data) {
            mData = data;
        }

        public Hero getData() {
            return mData;
        }
    }

