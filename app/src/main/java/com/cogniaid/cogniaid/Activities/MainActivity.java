package com.cogniaid.cogniaid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cogniaid.cogniaid.Client.RetroClient;
import com.cogniaid.cogniaid.Client.TodoInterface;
import com.cogniaid.cogniaid.R;
import com.cogniaid.cogniaid.Model.Conversation;
import com.cogniaid.cogniaid.Model.QueryParams;
import com.cogniaid.cogniaid.Constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void startChat(View view){
        Intent intent;
        switch(view.getId()){
            case R.id.business:
                intent = new Intent(this, ChatActivity.class);
                intent.putExtra("link", Constants.BUSINESS);
                startActivity(intent);
                break;
            case R.id.other:
                intent = new Intent(this, ChatActivity.class);
                intent.putExtra("link", Constants.AUTO_DEALER);
                break;
            default:
                break;
        }
    }
}
