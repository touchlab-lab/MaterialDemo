package co.touchlab.materialdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class RecyclerListActivity extends Activity
{
    private RecyclerView               mRecyclerView;
    private RecyclerView.Adapter       mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button                     mAddButton;
    private Button                     mRemoveButton;

    public static void callMe(Context context)
    {
        Intent intent = new Intent(context, RecyclerListActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_recycler_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);*/
    }

}
