package co.touchlab.materialdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class AnimationActivity extends ActionBarActivity
{

    private ImageView mHeroImage;
    private ImageView mRevealImage;

    public static void callMe(Context context)
    {
        Intent intent = new Intent(context, AnimationActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mHeroImage = (ImageView) findViewById(R.id.shared_transition_element);
        mRevealImage = (ImageView) findViewById(R.id.photo);

        Button circularRevealBtn = (Button) findViewById(R.id.btn_animation_reveal);

        circularRevealBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mRevealImage.getVisibility() == View.VISIBLE)
                {
                    circularRevealHide(mRevealImage.getId());
                }
                else
                {
                    circularRevealShow(mRevealImage.getId());
                }
            }
        });

        mHeroImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = TransitionedActivity.getLaunchIntent(AnimationActivity.this,
                                                                     ((BitmapDrawable) mHeroImage
                                                                             .getDrawable())
                                                                             .getBitmap());

                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(AnimationActivity.this, mHeroImage,
                                                      "hero_icon").toBundle());
            }
        });
    }


    private void circularRevealShow(int viewId)
    {
        // previously invisible view
        View myView = findViewById(viewId);

        // get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        anim.setDuration(1000);
        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void circularRevealHide(int viewId)
    {
        // previously visible view
        final View myView = findViewById(viewId);

        // get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        // get the initial radius for the clipping circle
        int initialRadius = myView.getWidth();

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
        anim.setDuration(1000);
        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        anim.start();
    }

}
