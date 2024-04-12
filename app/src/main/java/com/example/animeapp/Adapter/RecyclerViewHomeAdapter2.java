package com.example.animeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.Model.Story;
import com.example.animeapp.R;

import java.util.List;

public class RecyclerViewHomeAdapter2 extends RecyclerView.Adapter<RecyclerViewHomeAdapter2.StoryViewHolder2> {
    private Context context;

    public RecyclerViewHomeAdapter2(Context context) {
        this.context = context;
    }

    public void setData(List<Story> list){
        this.stories = list;
        notifyDataSetChanged();
    }
    private List<Story> stories;
    @NonNull
    @Override
    public StoryViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_2,parent,false);

        return new StoryViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder2 holder, int position) {
        Story story = stories.get(position);
        if(story==null){
            return;
        }
//        Uri uri = Uri.parse(story.getCoverImage());
//        holder.imgstory.setImageURI(uri);
        holder.imgstory.setImageResource(R.drawable.truyen2);
        holder.tvstory.setText("CHÚA PHÙ HỘ CHO SỰ HỦY DIỆT CỦA TA");
        holder.tvchapter.setText("chương 10");
    }

    @Override
    public int getItemCount() {
        if(stories!=null){
            return stories.size();
        }
        return 0;
    }

    public class StoryViewHolder2 extends RecyclerView.ViewHolder{
        private ImageView imgstory;
        private TextView tvstory;
        private TextView tvchapter;
        public StoryViewHolder2(@NonNull View itemView) {
            super(itemView);
            imgstory = itemView.findViewById(R.id.iv_imagestory2);
            tvstory = itemView.findViewById(R.id.tv_namestory2);
            tvchapter = itemView.findViewById(R.id.tv_chapter2);
        }
    }
}
