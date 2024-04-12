package com.example.animeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeapp.Model.Page;
import com.example.animeapp.R;

import java.util.ArrayList;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {
    private Context ct;
    private ArrayList<Page> arr;

    public PageAdapter(ArrayList<Page> arr, Context ct) {
        this.ct = ct;
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_page, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Page page = arr.get(position);
        String pageNumber = String.valueOf(page.getPageNumber());
        holder.txtSoTrang.setText(pageNumber);
        Glide.with(ct).load(page.getImage()).into(holder.img1Page);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1Page;
        TextView txtSoTrang;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSoTrang = itemView.findViewById(R.id.txtSoTrang);
            img1Page = itemView.findViewById(R.id.img1Page);
        }
    }
}