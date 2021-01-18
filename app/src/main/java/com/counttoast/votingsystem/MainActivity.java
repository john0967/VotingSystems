package com.counttoast.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Login, SignUp1;
    EditText em, ps;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = (Button) findViewById(R.id.button);
        SignUp1 = (Button) findViewById(R.id.button2);
        em = (EditText) findViewById(R.id.email_id);
        ps = (EditText) findViewById(R.id.password_id);

        DB = new DBHelper(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String em1 = em.getText().toString();
                String ps1 = ps.getText().toString();

                if (em1.equals("") || ps1.equals("")) {
                    Toast.makeText(MainActivity.this, "pls insert data", Toast.LENGTH_LONG).show();
                } else if (em1.equals("admin") && ps1.equals("1234")) {
                    Intent intent = new Intent(getApplicationContext(), updateCandidates.class);
                    startActivity(intent);
                } else {
                    Boolean checkuser = DB.checkuser(em1, ps1);
                    if (checkuser == true) {
                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
        SignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUp();
            }
        });
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void openSignUp() {
        Intent intent1 = new Intent(this, SignUp.class);
        startActivity(intent1);
        finish();
    }
}