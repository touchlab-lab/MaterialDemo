package co.touchlab.materialdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class ShadowsAndClippingMainFragment extends Fragment
{
    public static ShadowsAndClippingMainFragment newInstance()
    {
        ShadowsAndClippingMainFragment fragment = new ShadowsAndClippingMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ShadowsAndClippingMainFragment()
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
        View rootView = inflater.inflate(R.layout.fragment_shadows_and_clipping_main, container, false);

        final ImageView photoToClip = (ImageView)rootView.findViewById(R.id.photo_to_clip);

        photoToClip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(photoToClip.getClipToOutline())
                {
                    photoToClip.setClipToOutline(false);
                }
                else
                {
                    photoToClip.setClipToOutline(true);
                }
            }
        });

        return rootView;
    }
}
