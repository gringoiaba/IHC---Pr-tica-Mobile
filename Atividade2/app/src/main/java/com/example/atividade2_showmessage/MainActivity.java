package com.example.atividade2_showmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMessage = findViewById(R.id.editTextText);
    }

    public void sendMessage(View v) {
        String message = editTextMessage.getText().toString();

        // Creates a new intent that'll start the next activity
        Intent i = new Intent(this, MainActivity2.class);

        // Sends the message to the next activity
        i.putExtra("message", message);

        // Starts the second activity
        startActivity(i);
    }
}