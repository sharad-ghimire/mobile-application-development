package com.mad.exercise5;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Train> trains = new ArrayList<>();
    private RecyclerView mTrainRecyclerView;
    private TrainAdapter mTrainAdapter;
    private ProgressBar pBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trains.add(new Train("Rohit Platform 3", "5", "Late", "Nepal", "15:01"));
                trains.add(new Train("Jason Platform 3", "5", "On Time", "Nepal", "15:01"));
                initRecycler();
            }
        });
    }

    private void initData() {
        trains.add(new Train("Artarmion Platform 3", "7", "On Time", "Ashfield", "15:01"));
        trains.add(new Train("Sharad Platform 3", "234", "Late", "Nepal", "15:01"));
        trains.add(new Train("Rohit Platform 3", "5", "Late", "Nepal", "15:01"));
//        mTrainAdapter.notifyDataSetChanged();
        initRecycler();
    }

    private void initRecycler() {
        mTrainRecyclerView = findViewById(R.id.activity_main_train_recyclerview);
        mTrainAdapter = new TrainAdapter(this,trains);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mTrainRecyclerView.setLayoutManager(mLayoutManager);
        mTrainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTrainRecyclerView.setAdapter(mTrainAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_refresh:
//                return true;
                new SpinnerRefreshAsyncTask().execute();
            case R.id.action_delete:
//                return true;
                trains.clear();
                initRecycler();
            case R.id.action_quit:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SpinnerRefreshAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPreExecute() {
            pBar = findViewById(R.id.pbar);
            pBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
//            mTrainRecyclerView.setVisibility(View.VISIBLE);
            pBar.setVisibility(View.INVISIBLE);


        }
    }
}
