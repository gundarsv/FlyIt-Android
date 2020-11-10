package com.flyit.application.networking;

import com.flyit.application.models.Airport;
import com.flyit.application.models.AuthenticationToken;
import com.flyit.application.models.AuthenticationTokenRefresh;
import com.flyit.application.models.Flight;
import com.flyit.application.models.FlightSearch;
import com.flyit.application.models.News;
import com.flyit.application.models.SignUp;
import com.flyit.application.models.SingIn;
import com.flyit.application.models.User;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FlyItApi {
    @GET("User")
    Call<User> getUser();

    @POST("Auth/SignIn")
    Call<AuthenticationToken> SignIn(@Body SingIn signIn);

    @POST("Auth/SignUp")
    Call<ResponseBody> SignUp(@Body SignUp signUp);

    @POST("Auth/Revoke")
    Call<AuthenticationToken> revoke(@Body AuthenticationTokenRefresh authenticationTokenRefresh);

    @GET("Flight")
    Call<ArrayList<Flight>> getFlight();

    @GET("Flight/Search/{flightNo}")
    Call<FlightSearch> getSearchFlight (@Path("flightNo") String flightNo);

    @POST("Flight")
    Call<Flight> addFlight (@Body FlightSearch flightSearch);

    @GET("News/{iata}/airport")
    Call<ArrayList<News>> getNewsByAirportIata(@Path("iata") String iata);

    @GET("Airport/airport/{iata}")
    Call<Airport> getAirportByIata(@Path("iata") String iata);
}
