package com.cogniaid.cogniaid.Client;

import com.cogniaid.cogniaid.Model.Conversation;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


/**
 * Created by alcanzer on 12/5/18.
 */

public interface TodoInterface {
    @POST
    Observable<ResponseBody> sendMessage(@Url String url, @Body Conversation conversation);
}

