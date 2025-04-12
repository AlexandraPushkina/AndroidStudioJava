package com.example.homework1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Result;
    private final Button[] buttons = new Button[16];
    private CalculatorLogic calculatorLogic = new CalculatorLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Linking Activity with layout-file activity_main.xml

        buttons[0] = findViewById(R.id.digit0);  // R - folder res
        buttons[1] = findViewById(R.id.digit1);
        buttons[2] = findViewById(R.id.digit2);
        buttons[3] = findViewById(R.id.digit3);
        buttons[4] = findViewById(R.id.digit4);
        buttons[5] = findViewById(R.id.digit5);
        buttons[6] = findViewById(R.id.digit6);
        buttons[7] = findViewById(R.id.digit7);
        buttons[8] = findViewById(R.id.digit8);
        buttons[9] = findViewById(R.id.digit9);
        buttons[10] = findViewById(R.id.plus);
        buttons[11] = findViewById(R.id.minus);
        buttons[12] = findViewById(R.id.division);
        buttons[13] = findViewById(R.id.multiple);
        buttons[14] = findViewById(R.id.clear);
        buttons[15] = findViewById(R.id.equal);

        for (Button button : buttons) {
            button.setOnClickListener((View.OnClickListener) this);
        }
        Result = findViewById(R.id.result);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;  //view - clicked button
        String buttonText = button.getText().toString();

        if (buttonText.matches("[0-9]")) { // Regex
            Result.append(buttonText);
        } else if (buttonText.equals("+")){
            Result.append("+");
        } else if (buttonText.equals("-")){
            Result.append("-");
        } else if (buttonText.equals("/")){
            Result.append("/");
        } else if (buttonText.equals("x")){  // multiple
            Result.append("x");
        } else if (buttonText.equals("clear")) {
            Result.setText("");                 // TODO: realize clear function
        } else if (buttonText.equals("=")){
            String expression = Result.getText().toString();
            String result = calculatorLogic.calculate(expression);  // displays 0
            Result.setText(result);
        }
    }
}