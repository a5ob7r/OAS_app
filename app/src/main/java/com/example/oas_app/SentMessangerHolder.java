package com.example.oas_app;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SentMessangerHolder extends RecyclerView.ViewHolder {
    TextView messageText;

    SentMessangerHolder(View itemView) {
        super(itemView);

        messageText = itemView.findViewById(R.id.textView);
    }

    void bind(Message message) {
        messageText.setText(message.text);
    }
}
