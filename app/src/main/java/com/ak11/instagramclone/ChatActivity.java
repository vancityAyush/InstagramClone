package com.ak11.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private EditText edtMessage;
    private Button btnSend;
    private String sender,receiver;
    private  chatRecyclerAdapter chatRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sender = ParseUser.getCurrentUser().getUsername();
        receiver = getIntent().getStringExtra("username");
        setTitle(receiver+"'s Inbox");

        recyclerView = findViewById(R.id.chatRecyclerView);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        chatRecyclerAdapter = new chatRecyclerAdapter(sender,receiver);
        ParseQuery<ParseObject> parseQuery1 = ParseQuery.getQuery("Chats");
        parseQuery1.whereEqualTo("from",sender);
        parseQuery1.whereEqualTo("to",receiver);

        ParseQuery<ParseObject> parseQuery2 = ParseQuery.getQuery("Chats");
        parseQuery2.whereEqualTo("from",receiver);
        parseQuery2.whereEqualTo("to",sender);

        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
        queries.add(parseQuery1);
        queries.add(parseQuery2);
        ParseQuery<ParseObject> innerQuery = ParseQuery.or(queries);
        innerQuery.addAscendingOrder("createdAt");


        innerQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if( objects.size() > 0 )
                        for( ParseObject message :objects){
                            chatRecyclerAdapter.addData(message);
                        }
                }
            }
        });
        recyclerView.setAdapter(chatRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {

        if(edtMessage.getText().toString().isEmpty()){
            FancyToast.makeText(ChatActivity.this,"Message is empty!!!",FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,false).show();
        }
        else {
            ParseObject parseObject = new ParseObject("Chats");
            parseObject.put("to", receiver);
            parseObject.put("from", sender);
            parseObject.put("message", edtMessage.getText().toString());
            parseObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        chatRecyclerAdapter.addData(parseObject);
                        FancyToast.makeText(ChatActivity.this, "Message Sent!", FancyToast.LENGTH_SHORT,
                                FancyToast.SUCCESS, false).show();
                    }
                    else

                        FancyToast.makeText(ChatActivity.this, e.getMessage(), FancyToast.LENGTH_SHORT,
                                FancyToast.ERROR, false).show();
                }
            });
            edtMessage.setText("");
        }
    }
}