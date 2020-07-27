package com.certified.certfieddating;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.certified.certfieddating.model.User;

import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "MainRecyclerViewAdapter";
    private ArrayList<User> mUsers = new ArrayList<>();
    private Context mContext;

    MainRecyclerViewAdapter(Context context, ArrayList<User> Users) {
        mContext = context;
        mUsers = Users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_feed, parent, false);
       ViewHolder holder = new ViewHolder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(mUsers.get(position).getProfile_image())
                .apply(requestOptions)
                .into(holder.image);

        holder.name.setText(mUsers.get(position).getName());
        holder.interestedIn.setText(mUsers.get(position).getInterested_in());
        holder.status.setText(mUsers.get(position).getStatus());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mUsers.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;
        private final TextView interestedIn;
        private final TextView status;
        private final CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.name);
            interestedIn = itemView.findViewById(R.id.interested_in);
            status = itemView.findViewById(R.id.status);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}