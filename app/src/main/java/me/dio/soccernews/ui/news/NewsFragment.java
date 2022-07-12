package me.dio.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import me.dio.soccernews.MainActivity;
import me.dio.soccernews.data.local.SoccerNewsDb;
import me.dio.soccernews.databinding.FragmentNewsBinding;
import me.dio.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;
    private FragmentNewsBinding binding;
    private SoccerNewsDb db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textNews;
        //   db = Room.databaseBuilder(getContext(),SoccerNewsDb.class, "soccer-news").allowMainThreadQueries().build();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));

        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            //                MainActivity activity = (MainActivity) getActivity();
            //                if (activity != null) {
            //                    activity.getDb().newsDao().save(updatedNews);
            //                }
            binding.rvNews.setAdapter(new NewsAdapter(news, newsViewModel::saveNews));

        });


        // new Observer<String>() {
        //    @Override
        //      public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //     }
        //  });

        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    //TODO: Iniciar SwipeRefreshLayout (loading).
                    break;
                case DONE:
                    //TODO: Finalizar SwipeRefreshLayout (loading).
                    break;
                case ERROR:
                    //TODO: Finalizar SwipeRefreshLayout (loading).
                    //TODO: Mostrar um erro genérico.
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}