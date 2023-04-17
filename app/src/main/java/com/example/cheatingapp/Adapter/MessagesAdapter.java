package com.example.cheatingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cheatingapp.ModelClass.myMessage;
import com.example.cheatingapp.R;
import com.example.cheatingapp.databinding.ItemRececiveBinding;
import com.example.cheatingapp.databinding.ItemSentBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessagesAdapter extends  RecyclerView.Adapter{
    Context context;
    ArrayList<myMessage> messages;
    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    public MessagesAdapter(Context context, ArrayList<myMessage> messages) {
        this.context = context;
        this.messages = messages;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==ITEM_SENT){
            View view= LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return  new SentViewHolder(view);
        }else {
            View view= LayoutInflater.from(context).inflate(R.layout.item_rececive,parent,false);
            return  new ReceiverViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        myMessage message=messages.get(position);
        if (FirebaseAuth.getInstance().getUid().equals(message.getSenderId()))
        {
            return ITEM_SENT;
        }else {
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        myMessage message=messages.get(position);
        if (holder.getClass()==SentViewHolder.class){
            SentViewHolder viewHolder=(SentViewHolder) holder;
            viewHolder.binding.message.setText(message.getMessage());
        }else {
            ReceiverViewHolder viewHolder=(ReceiverViewHolder) holder;
            viewHolder.binding.message.setText(message.getMessage());
        }
    }
    @Override
    public int getItemCount() {
        return messages.size();
    }
    public  class SentViewHolder extends RecyclerView.ViewHolder{
         ItemSentBinding binding;
        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemSentBinding.bind(itemView);
        }
    }
    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
     ItemRececiveBinding binding;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemRececiveBinding.bind(itemView);
        }
    }
}
