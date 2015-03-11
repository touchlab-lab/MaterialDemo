package co.touchlab.materialdemo.adapters;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.touchlab.materialdemo.R;

/**
 * Created by dgelormini on 3/6/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MainViewHolder>
{

    private List<String> mDataset;

    // Create new views (invoked by the layout manager)
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ImageView cardImage;
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.card_list_item, parent, false);
        cardImage = (ImageView) v.findViewById(R.id.card_list_photo);

        Picasso.with(v.getContext()).load(R.drawable.gsxr).fit().into(cardImage);

        // set the view's size, margins, paddings and layout parameters
        final MainViewHolder vh = new MainViewHolder(v, new MainViewHolder.ItemClickListener()
        {
            @Override
            public void onClick(View caller, MainViewHolder viewHolder)
            {
                Log.d("CardAdapter", "clicked: " + viewHolder.getPosition());
                if(! mDataset.isEmpty())
                {
                    mDataset.remove(viewHolder.getPosition());
                    notifyItemRemoved(viewHolder.getPosition());
                }
            }
        });
        return vh;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CardAdapter(List<String> myDataset)
    {
        mDataset = myDataset;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position)
    {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        // each data item is just a string in this case
        ItemClickListener onClickListener = null;

        public TextView mTextView;
        View clickableView;

        public MainViewHolder(View v, ItemClickListener itemClickListener)
        {
            super(v);
            this.onClickListener = itemClickListener;
            clickableView = v.findViewById(R.id.card_view_overlay);

            mTextView = (TextView) v.findViewById(R.id.main_list_text);
            clickableView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            onClickListener.onClick(v, this);
        }

        public static interface ItemClickListener
        {
            public void onClick(View caller, MainViewHolder viewHolder);
        }

    }
}
