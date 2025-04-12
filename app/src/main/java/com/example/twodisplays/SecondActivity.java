package com.example.twodisplays;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private ImageView birthdayImage;
    private TextView birthdayGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        birthdayImage = findViewById(R.id.birthdayImage);
        birthdayGreeting = findViewById(R.id.birthdayGreeting);

        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0); // 0 - значение по умолчанию, если age не найден


        birthdayGreeting.setText(String.format("С днем рождения, %s! Вам %d лет!", name, age));
    }
}