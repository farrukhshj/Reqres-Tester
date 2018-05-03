package shuja.com.reqrestester.network.service;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import shuja.com.reqrestester.model.CreateUserDTO;
import shuja.com.reqrestester.model.UserEntity;
import shuja.com.reqrestester.utility.NoNetworkException;
import shuja.com.reqrestester.utility.OkHttpManager;
import shuja.com.reqrestester.utility.WebException;

public class UserService {
    private static String baseURL = "https://reqres.in/";

    public static UserEntity getUsersFromEndPoint() throws NoNetworkException, WebException, IOException {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseURL + "api/users").newBuilder();

        Request.Builder request = new Request.Builder();
        request.url(httpBuilder.build()).build();
        request.get();

        Response response = OkHttpManager.performRequest(request.build());
        String responseString = response.body().string();
        Log.d("User Service:", " JSON Response = " + responseString);
        return new Gson().fromJson(responseString, UserEntity.class);

    }

    public static String postUserToEndPoint(CreateUserDTO createUserDTO) throws NoNetworkException, WebException, SocketTimeoutException {
        String responseString = "";
        HttpUrl httpUrl = HttpUrl.parse(baseURL + "api/users");

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(httpUrl);

        String body = new Gson().toJson(createUserDTO);
        requestBuilder.post(RequestBody.create(MediaType.parse("application/json"), body));
        Response response = OkHttpManager.performRequest(requestBuilder.build());
        try {
             responseString = response.body().string();
             Log.d("User Service:", " JSON Response = " + responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
