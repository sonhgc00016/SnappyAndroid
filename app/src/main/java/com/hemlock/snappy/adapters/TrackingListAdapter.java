package com.hemlock.snappy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hemlock.snappy.R;
import com.hemlock.snappy.model.tracking.Tracking;

import java.util.ArrayList;

/**
 * Created by lookonlyatme on 12/15/16.
 */

public class TrackingListAdapter extends RecyclerView.Adapter<TrackingListAdapter.ViewHolder> {
    private ArrayList<Tracking> mTrackings;
    public IMyViewHolderClicks mListener;

    public TrackingListAdapter(ArrayList<Tracking> mTrackings, IMyViewHolderClicks pListener) {
        this.mTrackings = mTrackings;
        this.mListener = pListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tracking_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTrackingId.setText(mTrackings.get(position).getId());
        holder.tvCurrentStatus.setText(mTrackings.get(position).getCurrentStatus());
    }

    @Override
    public int getItemCount() {
        return mTrackings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTrackingId, tvCurrentStatus;

        ViewHolder(View v) {
            super(v);
            tvTrackingId = (TextView) v.findViewById(R.id.tv_tracking_id);
            tvCurrentStatus = (TextView) v.findViewById(R.id.tv_current_status);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface IMyViewHolderClicks {
        void onItemClick(View view, int position);
    }
}
