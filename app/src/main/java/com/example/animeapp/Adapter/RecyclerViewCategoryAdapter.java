package com.example.animeapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.Api.OnItemClickListener;
import com.example.animeapp.Model.Category;
import com.example.animeapp.R;

import java.util.List;

public class RecyclerViewCategoryAdapter extends RecyclerView.Adapter<RecyclerViewCategoryAdapter.CategoryHolder> {
    private Context context;
    public OnItemClickListener mListener;
    View selectedView;
    private int previousClickedPosition = RecyclerView.NO_POSITION;
    public RecyclerViewCategoryAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.mListener=listener;
    }
    public void setData(List<Category> list){
        this.categories = list;
        notifyDataSetChanged();
    }
    private List<Category> categories;

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        TextView tvitem = view.findViewById(R.id.tv_category);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(selectedView!=null){
//                    TextView tvitem1 = selectedView.findViewById(R.id.tv_category);
//                    tvitem1.setTextColor(Color.BLACK);
//                }
//                tvitem.setTextColor(Color.RED);
//                selectedView=view;
//            }
//        });
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = categories.get(position);
        if(category==null){
            return;
        }
//        Uri uri = Uri.parse(story.getCoverImage());
//        holder.imgstory.setImageURI(uri);
        holder.tvcategory.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedView!=null){
                    TextView tvitem1 = selectedView.findViewById(R.id.tv_category);
                    tvitem1.setTextColor(Color.BLACK);
                }
                holder.tvcategory.setTextColor(Color.RED);
                selectedView=v;
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(categories!=null){
            return categories.size();
        }
        return 0;
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        TextView tvcategory;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvcategory = itemView.findViewById(R.id.tv_category);

        }
    }
}
