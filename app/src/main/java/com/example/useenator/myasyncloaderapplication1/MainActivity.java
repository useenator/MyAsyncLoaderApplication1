package com.example.useenator.myasyncloaderapplication1;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView mListView;
    private LoaderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listView
        mAdapter=new LoaderAdapter(this);
        mListView=(ListView)findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);

        //loaderManager init
        getSupportLoaderManager().initLoader(0,null,mLoaderCallback);

    }


    private LoaderManager.LoaderCallbacks<List<String>> mLoaderCallback=new LoaderManager.LoaderCallbacks<List<String>>() {
        @Override
        public Loader<List<String>> onCreateLoader(int id, Bundle args) {
            return new StringsLoaderTask(getApplicationContext());
        }

        @Override
        public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
            mAdapter.swapData(data);

        }

        @Override
        public void onLoaderReset(Loader<List<String>> loader) {
            mAdapter.swapData(Collections.<String>emptyList());

        }
    };
}
