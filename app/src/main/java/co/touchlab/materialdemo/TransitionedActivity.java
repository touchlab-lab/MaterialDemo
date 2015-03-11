package co.touchlab.materialdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.gelormini.materialdesignexample.R;

public class TransitionedActivity extends BaseActionBarActivity
{

    public static Intent getLaunchIntent(Context context, Bitmap bitmapDrawable)
    {
        Intent intent = new Intent(context, TransitionsActivity.class);
        mBitmapDrawable = bitmapDrawable; //Set the drawable to animate
        return intent;
        //        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle());
    }

    private static Bitmap    mBitmapDrawable;
    private        ImageView mImageView;

    private Bitmap setupPhoto()
    {
        ((ImageView) findViewById(R.id.hero)).setImageBitmap(mBitmapDrawable);
        return mBitmapDrawable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());
        getWindow().setEnterTransition(new Explode());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transitions);

        setupPhoto();

        mImageView = (ImageView) findViewById(R.id.hero);

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
                ObjectAnimator color = ObjectAnimator.ofArgb(hero.getDrawable(), "tint",
                                                             getResources()
                                                                     .getColor(R.color.primary), 0);
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
                .ofArgb(hero.getDrawable(), "tint", 0, getResources().getColor(R.color.primary));
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
