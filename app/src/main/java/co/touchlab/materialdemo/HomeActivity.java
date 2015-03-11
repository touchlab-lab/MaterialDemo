package co.touchlab.materialdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import co.touchlab.materialdemo.fragments.CardViewFragment;
import co.touchlab.materialdemo.fragments.RecyclerViewMainFragment;


public class HomeActivity extends ActionBarActivity
{
    private CharSequence title;
    private ListView     drawerListView;
    private DrawerLayout drawerLayout;
    private final String[] topics = {"CardViews", "RecyclerViews","Shadows and Clipping", "Animations","Palette"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        title = getTitle();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                                                                       R.string.drawer_open,
                                                                       R.string.drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        setUpDrawer();
        selectItem(0);
    }

    private void setUpDrawer()
    {
        drawerListView = (ListView) findViewById(R.id.drawer_list);
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position != 0)
                {
                    selectItem(position - drawerListView.getHeaderViewsCount());
                }
            }
        });

        View header = LayoutInflater.from(this)
                                    .inflate(R.layout.header_drawer, drawerListView, false);
        drawerListView.addHeaderView(header);
        drawerListView.setAdapter(new ArrayAdapter<>(getSupportActionBar().getThemedContext(),
                                                     android.R.layout.simple_list_item_activated_1,
                                                     android.R.id.text1, topics));

    }

    private void selectItem(int position)
    {
        switch(position)
        {
            case 3:
                AnimationActivity.callMe(HomeActivity.this);
                break;
            case 4:
                startActivity(new Intent(HomeActivity.this, PaletteActivity.class));
                break;
            default:
                if(drawerLayout != null)
                {
                    drawerLayout.closeDrawer(drawerListView);
                }
                if(drawerListView != null)
                {
                    drawerListView
                            .setItemChecked(position + drawerListView.getHeaderViewsCount(), true);
                }
                onNavigationDrawerItemSelected(position);
                break;
        }

    }

    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(drawerListView))
        {
            drawerLayout.closeDrawer(drawerListView);
        }
        else
        {
            super.onBackPressed();
        }
    }

    public void onNavigationDrawerItemSelected(int position)
    {
        //        update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;

        switch(position)
        {
            case 0:
                fragment = CardViewFragment.newInstance();
                break;
            case 1:
                fragment = RecyclerViewMainFragment.newInstance();
                break;
            case 2:
                fragment = ShadowsAndClippingMainFragment.newInstance();
                break;
            default:
                fragment = PlaceholderFragment.newInstance(position + 1);
                break;
        }

        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        title = topics[position];
        restoreActionBar();
    }

    public void restoreActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

    }

}
