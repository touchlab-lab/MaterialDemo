package co.touchlab.materialdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class RevealAnimationActivity extends Activity
{

    View      mCardView;
    ImageView mImageView;
    Button    mButton;
    private View mImageToCrop;

    public static void callMe(Context context)
    {
        Intent intent = new Intent(context, RevealAnimationActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_animation);

        mImageView = (ImageView) findViewById(R.id.photo);
        mCardView = findViewById(R.id.card);
        mButton = (Button) findViewById(R.id.btn_reveal);
        mImageToCrop = findViewById(R.id.photo_to_clip);
        mImageToCrop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mImageToCrop.getClipToOutline())
                {
                    mImageToCrop.setClipToOutline(false);

                }
                else
                {
                    mImageToCrop.setClipToOutline(true);

                }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mCardView.getVisibility() == View.VISIBLE)
                {
                    circularRevealHide(mCardView.getId());
                }
                else
                {
                    circularRevealShow(mCardView.getId());
                }
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
