package com.example.oas_app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.example.oas_service.IFizzBuzzService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<Message> mMessage;

    private IFizzBuzzService fbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        mMessage = new ArrayList<Message>();

        mMessageRecycler = findViewById(R.id.recyclerview);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageAdapter = new MessageListAdapter(this, mMessage);
        mMessageRecycler.setAdapter(mMessageAdapter);

        Intent intent = new Intent("com.example.oas_service.FIZZ_BUZZ_SERVICE_BIND");
        intent.setPackage("com.example.oas_service");
        bindService(intent, fbConnection, Context.BIND_AUTO_CREATE);
    }

    public void sendMessage() {
        EditText v = findViewById(R.id.editText);
        String m = v.getText().toString();
        v.getText().clear();

        int n;

        updateMessage(m, true);

        try {
            n = Integer.parseInt(m);
        } catch (NumberFormatException e) {
            updateMessage("Input integer!", false);
            return;
        }

        try {
            List<String> fbList = fbService.fizzBuzz(n);
            m = fbList.toString();
            updateMessage(m, false);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void updateMessage(String s, boolean isCurrentUser) {
        mMessage.add(new Message(s, isCurrentUser));
        mMessageAdapter.notifyItemInserted(mMessage.size()-1);
        mMessageRecycler.smoothScrollToPosition(mMessage.size()-1);
    }

    private ServiceConnection fbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            fbService = IFizzBuzzService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            fbService = null;
        }
    };
}
