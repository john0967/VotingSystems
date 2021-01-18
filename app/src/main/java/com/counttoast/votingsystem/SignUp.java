package com.counttoast.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SignUp extends AppCompatActivity {

    private TextView fname, lname, password1, email1, phoneno;
    Button register, logout;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mydb = new DBHelper(this);
        fname = (TextView) findViewById(R.id.fn);
        lname = (TextView) findViewById(R.id.ln);
        email1 = (TextView) findViewById(R.id.email_id);
        password1 = (TextView) findViewById(R.id.password);
        phoneno = (TextView) findViewById(R.id.phone);
        register = (Button) findViewById(R.id.button3);
        logout = (Button) findViewById(R.id.button4);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email1.getText().toString();
                String fname1 = fname.getText().toString();
                String lname1 = lname.getText().toString();
                String pass = password1.getText().toString();
                String phone = phoneno.getText().toString();

                if (email != "" && fname1 != "" && lname1 != "" && pass != "" && phone != "") {
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && pass.length() > 6 && (phone.matches("[0-9]{10}") && phone.length() == 10)) {
                        boolean isinserted = mydb.insertData(fname1, lname1, email, pass, phone);
                        if (isinserted == true) {
                            Toast.makeText(SignUp.this, "Successfully inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SignUp.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        }
                    } else
                        Toast.makeText(getApplicationContext(), "Please enter valid input", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG).show();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


}