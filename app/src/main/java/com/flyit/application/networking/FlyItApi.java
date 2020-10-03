package com.flyit.application.networking;

import com.flyit.application.models.AuthenticationToken;
import com.flyit.application.models.AuthenticationTokenRefresh;
import com.flyit.application.models.Flight;
import com.flyit.application.models.SignUp;
import com.flyit.application.models.SingIn;
import com.flyit.application.models.User;

import java.util.List;

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
    Call<AuthenticationToken> singIn(@Body SingIn signIn);

    @POST("Auth/SingUp")
    Call<ResponseBody> singUp(@Body SignUp signUp);

    @POST("Auth/Revoke")
    Call<AuthenticationToken> revoke(@Body AuthenticationTokenRefresh authenticationTokenRefresh);

    @GET("Flight")
    Call<List<Flight>> getFlight();

    @GET("Flight/{flightNo}")
    Call<Flight> getSearchFlight (@Path("flightNo") String flightNo);
}
