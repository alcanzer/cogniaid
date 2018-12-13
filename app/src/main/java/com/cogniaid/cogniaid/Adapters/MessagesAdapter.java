package com.cogniaid.cogniaid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cogniaid.cogniaid.Model.Message;
import com.cogniaid.cogniaid.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by alcanzer on 12/8/18.
 */

public class MessagesAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Message> messages;
    private static final int VIEW_SENT = 1;
    private static final int VIEW_RECV = 2;

    public MessagesAdapter(Context mContext, ArrayList<Message> messages){
        this.mContext = mContext;
        this.messages = messages;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == VIEW_SENT){
            view = LayoutInflater.from(mContext).inflate(R.layout.message_sent, parent, false);
            return new SentViewHolder(view);
        } else if(viewType == VIEW_RECV){
            view = LayoutInflater.from(mContext).inflate(R.layout.message_recv, parent, false);
            return new ReceivedViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        switch(holder.getItemViewType()){
            case VIEW_RECV:
                ((ReceivedViewHolder)holder).bind(message);
                break;
            case VIEW_SENT:
                ((SentViewHolder)holder).bind(message);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if(message.isBot()){
            return VIEW_RECV;
        }
        return VIEW_SENT;
    }

    private class ReceivedViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public ReceivedViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.message_recv);
        }

        void bind(Message message){
            mTextView.setText(message.getMessage());
        }
    }

    private class SentViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public SentViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.message_sent);
        }

        void bind(Message message){
            mTextView.setText(message.getMessage());
        }
    }
}
