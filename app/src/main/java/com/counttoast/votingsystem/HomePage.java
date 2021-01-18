package com.counttoast.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    private Button logout;
    public RadioButton r1, r2, r3, r4;
    public Button button;
    RadioGroup radioGroup;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        logout = (Button) findViewById(R.id.button1);
        radioGroup = findViewById(R.id.radiog);
        button = findViewById(R.id.buttonr);
        r1 = (RadioButton) findViewById(R.id.radio);
        r2 = (RadioButton) findViewById(R.id.radio1);
        r3 = (RadioButton) findViewById(R.id.radio2);
        r4 = (RadioButton) findViewById(R.id.radio3);
        mydb = new DBHelper(this);
        int checked = radioGroup.getCheckedRadioButtonId();
        if (checked == -1) {
            Toast.makeText(getApplicationContext(), "pls select your choice", Toast.LENGTH_LONG).show();
        } else {

            findRadioButton(checked);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void findRadioButton(int checked) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (checked) {
                    case R.id.radio:
                        int isinserted = mydb.previousBal(r1.getText().toString());
                        button.setText(isinserted);
                        break;
                    case R.id.radio1:
                        int isinserted1 = mydb.previousBal(r2.getText().toString());
                        button.setText(isinserted1);
                        break;
                    case R.id.radio2:
                        int isinserted2 = mydb.previousBal(r3.getText().toString());
                        button.setText(isinserted2);
                        break;
                    case R.id.radio3:
                        int isinserted3 = mydb.previousBal(r4.getText().toString());
                        button.setText(isinserted3);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}