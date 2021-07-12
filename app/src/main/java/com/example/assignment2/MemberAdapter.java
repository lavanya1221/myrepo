package com.example.assignment2;


import android.content.ContentValues;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder> {

    private List<Member> members = new ArrayList<>();
    private Context context;


    public MemberAdapter(Context context) {
        this.context = context;
    }


    @Override
    public MemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MemberHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MemberHolder holder, int position) {
        Member currMember = members.get(position);
        holder.name.setText(currMember.getName());
        holder.agency.setText(currMember.getAgency());

        Glide
                .with(context)
                .load(currMember.getImage())
                .into(holder.image);
        // holder.image.setImageDrawable(LoadImageFromWebOperations(currMember.getImage()));
        holder.wikipedia.setText(currMember.getWikipedia());
        holder.status.setText(currMember.getStatus());


        holder.removeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                members.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public void setMembers(List<Member> members) {
        this.members = members;
        notifyDataSetChanged();
    }

    class MemberHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView agency;
        private ImageView image;
        private TextView wikipedia;
        private TextView status;
        private ImageButton removeImg;

        public MemberHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.membername);
            agency = itemView.findViewById(R.id.memberagency);
            image = itemView.findViewById(R.id.memberimage);
            wikipedia = itemView.findViewById(R.id.wikipedia);
            status = itemView.findViewById(R.id.status);
            removeImg=itemView.findViewById(R.id.imageButton);
        }


    }

}




