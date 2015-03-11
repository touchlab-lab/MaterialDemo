package co.touchlab.materialdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

public class TransitionedActivity extends ActionBarActivity
{

    public static Intent getLaunchIntent(Context context, Bitmap bitmapDrawable)
    {
        Intent intent = new Intent(context, TransitionedActivity.class);
        mBitmapDrawable = bitmapDrawable; //Set the drawable to animate
        return intent;
    }

    private static Bitmap mBitmapDrawable;

    private Bitmap setupPhoto()
    {
        ((ImageView) findViewById(R.id.hero)).setImageBitmap(mBitmapDrawable);
        return mBitmapDrawable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setEnterTransition(new Explode());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transitioned);

        setupPhoto();

        ImageView mImageView = (ImageView) findViewById(R.id.hero);

        mImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        getWindow().getEnterTransition().addListener(new Transition.TransitionListener()
        {
            @Override
            public void onTransitionStart(Transition transition)
            {

            }

            @Override
            public void onTransitionEnd(Transition transition)
            {
                ImageView hero = (ImageView) findViewById(R.id.hero);
                ObjectAnimator color = ObjectAnimator
                        .ofArgb(hero.getDrawable(), "tint", getResources().getColor(R.color.tint),
                                0);
                color.start();

                getWindow().getEnterTransition().removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition)
            {

            }

            @Override
            public void onTransitionPause(Transition transition)
            {

            }

            @Override
            public void onTransitionResume(Transition transition)
            {

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        ImageView hero = (ImageView) findViewById(R.id.hero);
        ObjectAnimator color = ObjectAnimator
                .ofArgb(hero.getDrawable(), "tint", 0, getResources().getColor(R.color.tint));
        color.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                finishAfterTransition();
            }
        });
        color.start();

    }
}
