package co.touchlab.materialdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import co.touchlab.materialdemo.adapters.CardAdapter;
import co.touchlab.materialdemo.views.FloatingActionButton;

public class RecyclerStaggeredActivity extends Activity
{

    FloatingActionButton       mActionButton;
    RecyclerView               mRecyclerView;
    RecyclerView.Adapter       mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<String>               mDataSet;

    public static void callMe(Context context)
    {
        Intent intent = new Intent(context, RecyclerStaggeredActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid);

        mDataSet = populateDataSet();
        mActionButton = (FloatingActionButton) findViewById(R.id.fab_icon);
        mActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addItem("New Item");
            }
        });
        mAdapter = new CardAdapter(mDataSet);

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_recycler_grid);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addItem(String item)
    {
        Log.d("StaggeredGrid", "adding item: " + item + " mDataSet size: " + mDataSet.size());
        mDataSet.add(item);
        mAdapter.notifyItemInserted(mDataSet.size());
    }

    private List<String> populateDataSet()
    {

        List<String> dataSet = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            dataSet.add("List item " + i);
        }
        return dataSet;
    }
}
