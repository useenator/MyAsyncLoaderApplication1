package com.example.useenator.myasyncloaderapplication1;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hmed on 02/03/16.
 */
public class StringsLoaderTask extends AsyncTaskLoader<List<String>> {

    public StringsLoaderTask(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<String> loadInBackground() {
        String[] data= getContext().getResources().getStringArray(R.array.strings_items);
        List<String> strings = Arrays.asList(data);
        //make some delay
        try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}


        return strings;
    }

    @Override
    public void deliverResult(List<String> data) {
        super.deliverResult(data);
    }
}
