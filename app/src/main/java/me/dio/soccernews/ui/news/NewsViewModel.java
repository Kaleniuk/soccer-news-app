package me.dio.soccernews.ui.news;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

import me.dio.soccernews.data.local.AppDatabase;
import me.dio.soccernews.data.remote.SoccerNewsApi;
import me.dio.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final SoccerNewsApi api;


    public NewsViewModel() {
//       this.news = new MutableLiveData<>();

//        List<News> news = new ArrayList<>();
//        news.add(new News("Ferroviaria tem desfalque "," is simply dummy text of the printing and typesetting" ));
//        news.add(new News("Ferroviaria tem desfalque "," It is a long established fact that a reader will be distracted by the" ));
//        news.add(new News("Ferroviaria tem desfalque "," is simply dummy text of the printing and typesetting" ));
//        news.add(new News("Ferroviaria tem desfalque "," It is a long established fact that a reader will be distracted by the" ));
//       this.news.setValue(news);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kaleniuk.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(SoccerNewsApi.class);



        this.findNews();
    }


    private void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                if (response.isSuccessful()){
                    news.setValue(response.body());
                }else{


                    //TODO Tratamento de erros
                }

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                     //TODO Tratamento de erros
                Log.e("erro api", "Não deu sucesso ");

            }
        });
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}