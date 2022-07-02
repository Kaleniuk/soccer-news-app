package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        List<News> news = new ArrayList<>();
        news.add(new News("Ferroviaria tem desfalque "," is simply dummy text of the printing and typesetting" ));
        news.add(new News("Ferroviaria tem desfalque "," It is a long established fact that a reader will be distracted by the" ));
        news.add(new News("Ferroviaria tem desfalque "," is simply dummy text of the printing and typesetting" ));
        news.add(new News("Ferroviaria tem desfalque "," It is a long established fact that a reader will be distracted by the" ));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}