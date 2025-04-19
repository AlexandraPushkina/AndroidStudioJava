package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.homework1.databinding.ActivityMainBinding;
import com.example.homework1.databinding.NavHeaderBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
                    NavigationView.OnNavigationItemSelectedListener  {

    private ActivityMainBinding binding;
    private NavHeaderBinding bindingHeader;
    private EditText Result;
    private final Button[] buttons = new Button[16];
    private CalculatorLogic calculatorLogic = new CalculatorLogic();
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Linking Activity with layout-file activity_main.xml

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(binding.toolbar);

        drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        binding.digit0.setOnClickListener(this);  // initialization + set action onClick()
        binding.digit1.setOnClickListener(this);
        binding.digit2.setOnClickListener(this);
        binding.digit3.setOnClickListener(this);
        binding.digit4.setOnClickListener(this);
        binding.digit5.setOnClickListener(this);
        binding.digit6.setOnClickListener(this);
        binding.digit7.setOnClickListener(this);
        binding.digit8.setOnClickListener(this);
        binding.digit9.setOnClickListener(this);
        binding.plus.setOnClickListener(this);
        binding.minus.setOnClickListener(this);
        binding.division.setOnClickListener(this);
        binding.multiple.setOnClickListener(this);
        binding.clear.setOnClickListener(this);
        binding.equal.setOnClickListener(this);
        binding.equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;  //view - clicked button
        String buttonText = button.getText().toString();

        if (buttonText.matches("[0-9]")) { // Regex
            binding.result.append(buttonText);
        } else if (buttonText.equals("+")){
            binding.result.append("+");
        } else if (buttonText.equals("-")){
            binding.result.append("-");
        } else if (buttonText.equals("/")){
            binding.result.append("/");
        } else if (buttonText.equals("x")){  // multiple
            binding.result.append("x");
        } else if (buttonText.equals("clear")) {
            String expression = binding.result.getText().toString();
            String result = calculatorLogic.clear(expression);
            binding.result.setText(result);
        } else if (buttonText.equals("=")){
            String expression = binding.result.getText().toString();
            String result = calculatorLogic.calculate(expression);
            binding.result.setText(result);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return true;
    }
}