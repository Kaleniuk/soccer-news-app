package me.dio.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import me.dio.soccernews.databinding.FragmentFavoritesBinding;
import me.dio.soccernews.ui.adapter.NewsAdapter;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;
    private FragmentFavoritesBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);


        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textFavorites;
//        favoritesViewModel.getText().observe(getViewLifecycleOwner(), binding.textFavorites::setText); //new Observer<String>() {
        //  @Override
        //  public void onChanged(@Nullable String s) {
        //       textView.setText(s);
        //  }
        // });
        loadFavoriteNews();
        return root;

    }

    private void loadFavoriteNews() {
        this.favoritesViewModel.loadFavoriteNews().observe(getViewLifecycleOwner(), localNews -> {
            binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvNews.setAdapter(new NewsAdapter(localNews, updatedNews -> {
                favoritesViewModel.saveNews(updatedNews);
                loadFavoriteNews();

            }));
        });
    }


//    private void loadFavoriteNews() {
//        MainActivity activity = (MainActivity) getActivity();
//        if (activity!=null) {
//            List<News> favoriteNews = activity.getDb().newsDao().loadFavoriteNews();
//            binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
//            binding.rvNews.setAdapter(new NewsAdapter(favoriteNews, updatedNews -> {
//                activity.getDb().newsDao().save(updatedNews);
//                loadFavoriteNews();
//            }));
//
//        }
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}