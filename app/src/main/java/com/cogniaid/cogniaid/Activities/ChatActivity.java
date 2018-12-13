package com.cogniaid.cogniaid.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.cogniaid.cogniaid.Adapters.MessagesAdapter;
import com.cogniaid.cogniaid.Client.RetroClient;
import com.cogniaid.cogniaid.Client.TodoInterface;
import com.cogniaid.cogniaid.Model.Conversation;
import com.cogniaid.cogniaid.Model.Message;
import com.cogniaid.cogniaid.Model.QueryInput;
import com.cogniaid.cogniaid.Model.QueryParams;
import com.cogniaid.cogniaid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView messageView;
    private EditText messageText;
    private Button sndBtn;
    private MessagesAdapter adapter;
    private ArrayList<Message> messages;
    private String chatLink;
    private TodoInterface restCall;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messages = new ArrayList<>();
        chatLink = getIntent().getStringExtra("link");
        messageView = findViewById(R.id.messageList);
        messageText = findViewById(R.id.messageText);
        sndBtn = findViewById(R.id.sndBtn);
        adapter = new MessagesAdapter(this, messages);
        messageView.setLayoutManager(new LinearLayoutManager(this));
        messageView.setAdapter(adapter);
        restCall = RetroClient.getClient().create(TodoInterface.class);

        if(messages.isEmpty()){
            sendMessage("Hello");
        }

        sndBtn.setOnClickListener((v) -> {
            if(!messageText.getText().toString().isEmpty()){
                messages.add(new Message(messageText.getText().toString(), false));
                adapter.notifyDataSetChanged();
                sendMessage(messageText.getText().toString());
                messageText.setText("");
            }
        });
    }

    private void sendMessage(String message){

        restCall.sendMessage(chatLink, buildConversation(message))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(res -> {
                    JSONObject jObj = new JSONObject(res.string());
                    JSONArray fulfillment = jObj.getJSONObject("queryResult").getJSONArray("fulfillmentMessages");
                    for(int i = 0; i < fulfillment.length(); i++){
                        if(fulfillment.getJSONObject(i).getJSONObject("payload").getJSONObject("web") != null){
                            return fulfillment.getJSONObject(i).getJSONObject("payload").getJSONObject("web").getJSONArray("simpleResponse");
                        }
                    }
                    return null;
                })
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                messages.add(new Message(jsonArray.getJSONObject(i).get("text").toString(), true));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ObserverError", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        adapter.notifyDataSetChanged();
                    }
                });


    }

    private Conversation buildConversation(String message){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("source","web");
        HashMap<String, String> hash = new HashMap<>();
        hash.put("text", message);
        hash.put("language_code", "en-us");
        QueryParams qp = new QueryParams();
        qp.setPayload(hashMap);
        QueryInput qI = new QueryInput();
        qI.setText(hash);
        Conversation conversation = new Conversation();
        conversation.setSession("awfkajdkshjf");
        conversation.setQueryParams(qp);
        conversation.setQueryInput(qI);
        return conversation;
    }

    @Override
    protected void onDestroy() {
        if(disposable != null){
            disposable.dispose();
        }
        super.onDestroy();
    }
}
