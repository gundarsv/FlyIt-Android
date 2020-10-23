package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.flyit.application.models.News;
import com.flyit.application.models.Resource;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private final String TAG = getClass().getSimpleName();
    private FlyItApi flyItApi;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;

    private static NewsRepository newsRepository = null;

    private NewsRepository(Context context)
    {
        this.flyItApi = RetrofitService.getRetrofitInstance(context).create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }

    public static NewsRepository getNewsRepository(Context context)
    {
        if(newsRepository == null)
        {
            newsRepository = new NewsRepository(context);
        }

        return newsRepository;
    }

    public MutableLiveData<Resource<ArrayList<News>>> getNewsByAirportIata(String iata)
    {
        final MutableLiveData<Resource<ArrayList<News>>> newsMutableLiveData = new MutableLiveData<>();
        flyItApi.getNewsByAirportIata(iata).enqueue(new Callback<ArrayList<News>>() {
            @Override
            public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {
                if(response.isSuccessful())
                {
                    newsMutableLiveData.setValue(Resource.success((response.body())));
                }
                else {
                    newsMutableLiveData.setValue(Resource.error(response.body().toString(), null));
                }
                }

            @Override
            public void onFailure(Call<ArrayList<News>> call, Throwable t) {
                newsMutableLiveData.setValue(Resource.failure(t.getMessage()));

            }
        });
        return newsMutableLiveData;
    }
}
