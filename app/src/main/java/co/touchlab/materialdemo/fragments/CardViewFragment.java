package co.touchlab.materialdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import co.touchlab.materialdemo.R;

public class CardViewFragment extends Fragment
{

    public static CardViewFragment newInstance()
    {
        return new CardViewFragment();
    }

    public CardViewFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        final View welcomeCard = getView().findViewById(R.id.welcome);
        welcomeCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Animation animation = AnimationUtils
                        .loadAnimation(getActivity(), R.anim.slide_out_to_right);
                animation.setAnimationListener(new Animation.AnimationListener()
                {
                    @Override
                    public void onAnimationStart(Animation animation)
                    {
                        welcomeCard.setOnClickListener(null);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        welcomeCard.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation)
                    {

                    }
                });
                welcomeCard.startAnimation(animation);
            }
        });
    }

}
