package me.dio.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import me.dio.soccernews.databinding.FragmentNewsBinding;
import me.dio.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;
    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       // final TextView textView = binding.textNews;

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));

        newsViewModel.getNews().observe(getViewLifecycleOwner(), news ->{
                    binding.rvNews.setAdapter(new NewsAdapter(news));

                });


                 // new Observer<String>() {
        //    @Override
      //      public void onChanged(@Nullable String s) {
        //        textView.setText(s);
       //     }
      //  });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}