package co.touchlab.materialdemo.fragments;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import co.touchlab.materialdemo.R;

public class DrawableMainFragment extends Fragment
{

    public static DrawableMainFragment newInstance()
    {
        return new DrawableMainFragment();
    }

    public DrawableMainFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drawable_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ImageView star = (ImageView) getActivity().findViewById(R.id.star_vector);
        Drawable drawable = star.getDrawable();
        if(drawable instanceof Animatable)
        {
            ((Animatable) drawable).start();
        }
        ImageView androidImageView = (ImageView) getView().findViewById(R.id.heart);
        Drawable androidImageViewDrawable = androidImageView.getDrawable();
        if (androidImageViewDrawable instanceof Animatable) {
            ((Animatable) androidImageViewDrawable).start();
        }

    }

}
