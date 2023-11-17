package com.example.atividade3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textMessage = findViewById(R.id.textMessage);

        Intent i = getIntent();

        String message = i.getStringExtra("message");
        textMessage.setText(message);
    }
}