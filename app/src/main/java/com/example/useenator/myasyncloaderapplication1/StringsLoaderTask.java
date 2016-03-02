package com.example.useenator.myasyncloaderapplication1;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hmed on 02/03/16.
 */
public class StringsLoaderTask extends AsyncTaskLoader<List<String>> {
    List<String> mCachedData;
    //define an action for the broad cast receiver.
    public static final String ACTION_FORCE_LOAD ="com.loader.ACTION_FORCE_LOAD";

    public StringsLoaderTask(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        //register a broad cast receiver
        LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(getContext());
        IntentFilter filter=new IntentFilter(ACTION_FORCE_LOAD);
        localBroadcastManager.registerReceiver(mBroadcastReceiver, filter);
//        //// Or we can do
//        LocalBroadcastManager
//                .getInstance(getContext())
//                .registerReceiver(
//                        mBroadcastReceiver,
//                        new IntentFilter(ACTION_FORCE_LOAD)
//                );

        //check if it's the the fist load.
        if (mCachedData==null){
            forceLoad();
        }else{
            this.deliverResult(mCachedData);
        }
    }

    @Override
    public List<String> loadInBackground() {
        String[] data= getContext().getResources().getStringArray(R.array.strings_items);
        List<String> strings = Arrays.asList(data);
        //make some delay
        try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}

        Log.d("ForceLoad loadInBackgnd", "ForceLoad");

        return strings;
    }

    @Override
    protected void onReset() {
        //unregister the broadcast receiver
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mBroadcastReceiver);
        super.onReset();
    }

    @Override
    public void deliverResult(List<String> data) {
        //fill the cache to prevent force loading
        mCachedData=data;
        super.deliverResult(data);
    }
    ////////////////////// Broadcast receiver class definition. /////////////////////////
    BroadcastReceiver mBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //on intent action receiver DO
            forceLoad();

            Log.d("ForceLoad onReceive", "ForceLoad");
        }
    };
}
