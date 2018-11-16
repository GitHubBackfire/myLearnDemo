package com.astuetz.handler_demo;

import android.util.Log;
/**
 * 在不同线程中访问的是同一个ThreadLocal对象，但是它们通过ThreadLocal获取到的值却是不一样
 */
import static android.content.ContentValues.TAG;

public class ThreadLocalDemo {
    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<Boolean>();

    public void test() {
        mBooleanThreadLocal.set(true);
        System.out.println(TAG+"[Thread#main]mBooleanThreadLocal=" + mBooleanThreadLocal.get());
        new Thread("Thread#1") {
            @Override
            public void run() {
                mBooleanThreadLocal.set(false);
                System.out.println(TAG+"[Thread#1]mBooleanThreadLocal=" + mBooleanThreadLocal.get());
            }

        }.start();
        new Thread("Thread#2") {
            @Override
            public void run() {
                System.out.println(TAG+"[Thread#2]mBooleanThreadLocal=" + mBooleanThreadLocal.get());
            }

        }.start();
    }


}
