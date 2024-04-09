package com.example.animeapp.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.animeapp.Model.Story;
import com.example.animeapp.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private List<Story> storyList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // EditText để người dùng nhập từ khóa tìm kiếm
        EditText searchEditText = view.findViewById(R.id.search_bar);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // Lấy từ khóa tìm kiếm từ EditText
                    String searchTerm = searchEditText.getText().toString().trim();
                    // Lọc và hiển thị danh sách truyện
                    filterAndDisplayStoryList(searchTerm);
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Khởi tạo danh sách storyList
        storyList.add(new Story(1, "Tên truyện 1", "https://st.nettruyenbb.com/data/comics/235/ta-troi-sinh-da-la-nhan-vat-phan-dien-2184.jpg", "Tác giả 1", "Tóm tắt truyện 1", 1));
        storyList.add(new Story(2, "Tên truyện 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFEad9vqkY2sSoKfMLS3-U-BI5dKuXV9tWHA&usqp=CAU", "Tác giả 2", "Tóm tắt truyện 2", 2));
        storyList.add(new Story(3, "Tên truyện 3", "https://cdnntx.com/nettruyen/thumb/sat-thu-peter.jpg", "Tác giả 3", "Tóm tắt truyện 3", 3));
        storyList.add(new Story(4, "Tên truyện 4", "https://cdnntx.com/nettruyen/thumb/tinh-yeu-cua-ik-seob.jpg", "Tác giả 4", "Tóm tắt truyện 4", 4));
        storyList.add(new Story(5, "Tên truyện 5", "https://cdnntx.com/nettruyen/thumb/sat-thu-peter.jpg", "Tác giả 5", "Tóm tắt truyện 5", 5));
        storyList.add(new Story(6, "Tên truyện 6", "https://cdnntx.com/nettruyen/thumb/tinh-yeu-cua-ik-seob.jpg", "Tác giả 6", "Tóm tắt truyện 6", 6));
        storyList.add(new Story(7, "Tên truyện 7", "https://cdnntx.com/nettruyen/thumb/sat-thu-peter.jpg", "Tác giả 7", "Tóm tắt truyện 7", 7));
        // Thêm các đối tượng Story khác vào danh sách

        // Gọi hàm để hiển thị danh sách truyện
        displayStoryList(storyList);
    }
    private void filterAndDisplayStoryList(String searchTerm) {
        List<Story> filteredList = new ArrayList<>();
        for (Story story : storyList) {
            // Kiểm tra nếu tên truyện chứa từ khóa tìm kiếm
            if (story.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredList.add(story);
            }
        }
        displayStoryList(filteredList);
    }


    private void displayStoryList(List<Story> storyList) {
        LinearLayout storyListContainer = requireView().findViewById(R.id.story_list_container);
        storyListContainer.removeAllViews(); // Xóa các View cũ trước khi hiển thị danh sách mới

        for (Story story : storyList) {
            View storyView = LayoutInflater.from(getContext()).inflate(R.layout.item_story, storyListContainer, false);

            ImageView coverImage = storyView.findViewById(R.id.cover_image);
            TextView storyName = storyView.findViewById(R.id.story_name);
            TextView author = storyView.findViewById(R.id.author);

            // Gán dữ liệu cho các View
            Glide.with(requireContext())
                    .load(story.getCoverImage())
                    .into(coverImage);
            storyName.setText(story.getName());
            author.setText(story.getAuthor());

            storyListContainer.addView(storyView);
        }
    }
}