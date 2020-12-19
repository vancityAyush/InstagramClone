package com.ak11.instagramclone;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ak11.instagramclone.ChatViewHolder;
import com.ak11.instagramclone.R;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;

public class chatRecyclerAdapter extends RecyclerView.Adapter<ChatViewHolder>  {

    private ArrayList <HashMap<String,String>>chats;
    private String sender,receiver;

    public chatRecyclerAdapter(String sender, String receiver) {
        chats = new ArrayList();
        this.sender = sender;
        this.receiver = receiver;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chat_viewholder,parent,false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(view);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        TextView textView = holder.getTextView();
    textView.setText(chats.get(position).get("msg"));
    if(chats.get(position).get("from").equals(sender)){
        textView.setGravity(Gravity.RIGHT);
    }

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public  void addData(ParseObject message){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("msg",message.get("message").toString());
        hashMap.put("from",message.get("from").toString());
        chats.add(hashMap);
        notifyItemInserted(chats.size()-1);
    }
}
