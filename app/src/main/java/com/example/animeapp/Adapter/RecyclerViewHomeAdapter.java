package com.example.animeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.Model.Story;
import com.example.animeapp.R;

import java.util.List;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.StoryViewHolder> {
    private Context context;

    public RecyclerViewHomeAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Story> list){
        this.stories = list;
        notifyDataSetChanged();
    }
    private List<Story> stories;
    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_1,parent,false);

        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = stories.get(position);
        if(story==null){
            return;
        }
//        Uri uri = Uri.parse(story.getCoverImage());
//        holder.imgstory.setImageURI(uri);
        if(position==0){
            holder.imgstory.setImageResource(R.drawable.truyen1);
        }
        if(position==1){
            holder.imgstory.setImageResource(R.drawable.truyen2);
        }
        if(position==2){
            holder.imgstory.setImageResource(R.drawable.truyen3);
        }

    }

    @Override
    public int getItemCount() {
        if(stories!=null){
            return stories.size();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgstory;
        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgstory = itemView.findViewById(R.id.iv_imagestory);
        }

    }
}
