package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.News;
import com.flyit.application.models.Resource;
import com.flyit.application.repositories.NewsRepository;

import java.util.ArrayList;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;
    private MutableLiveData<Resource<ArrayList<News>>> news;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        this.newsRepository = NewsRepository.getNewsRepository(application.getApplicationContext());
    }
    public LiveData<Resource<ArrayList<News>>> getNewsByAirportIata(String iata)
    {
        if(news == null)
        {
            this.news = newsRepository.getNewsByAirportIata(iata);
        }
        return this.news;
    }
}
