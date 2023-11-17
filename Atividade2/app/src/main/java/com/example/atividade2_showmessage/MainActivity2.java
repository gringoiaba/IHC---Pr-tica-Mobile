package com.example.atividade2_showmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.TextView;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewMessage = findViewById(R.id.textView);

        // Get the intent that started the activity
        Intent i = getIntent();

        // Gets the string from the intent
        String message = i.getStringExtra("message");

        // Displays the message in the TextView
        textViewMessage.setText(message);
    }
}