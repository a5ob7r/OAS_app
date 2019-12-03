package com.example.oas_app;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReceivedMessangerHolder extends RecyclerView.ViewHolder {
    TextView messageText, nameText;
    ImageView profileImage;

    public ReceivedMessangerHolder(@NonNull View itemView) {
        super(itemView);

        messageText = itemView.findViewById(R.id.textView3);
        nameText = itemView.findViewById(R.id.textView2);
    }

    void bind(Message message) {
        messageText.setText(message.text);
        nameText.setText("FizzBuzz Bot");
    }
}
