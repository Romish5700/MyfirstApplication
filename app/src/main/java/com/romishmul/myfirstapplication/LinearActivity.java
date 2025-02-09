package com.romishmul.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

public class LinearActivity extends AppCompatActivity {
    Button gotogame,regulations;
    private static final int START_GAME = 222;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear2);

        gotogame = findViewById(R.id.gotogame);
        gotogame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LinearActivity.this, GameActivity.class);
                startActivityForResult(intent, START_GAME);
            }


        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == START_GAME)
            if (resultCode == RESULT_OK) {
                int i = data.getIntExtra("num_gueses", -1);
                String s = data.getStringExtra("user_name");
                Toast.makeText(this, "game finished counter= "+i+" user="+s, Toast.LENGTH_SHORT).show();
            }
    }
}