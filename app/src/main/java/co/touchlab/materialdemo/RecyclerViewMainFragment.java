package co.touchlab.materialdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RecyclerViewMainFragment extends Fragment
{
    public static RecyclerViewMainFragment newInstance()
    {
        RecyclerViewMainFragment fragment = new RecyclerViewMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerViewMainFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recycler_view_main, container, false);

        Button recyclerListBtn = (Button) rootView.findViewById(R.id.btn_recycler_list);
        Button recyclerGridBtn = (Button) rootView.findViewById(R.id.btn_recycler_grid);
        Button recyclerStaggeredBtn = (Button) rootView.findViewById(R.id.btn_recycler_staggered);

        recyclerListBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RecyclerListActivity.callMe(getActivity());
            }
        });

        recyclerGridBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RecyclerGridActivity.callMe(getActivity());
            }
        });

        recyclerStaggeredBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RecyclerStaggeredActivity.callMe(getActivity());
            }
        });
        return rootView;
    }
}
