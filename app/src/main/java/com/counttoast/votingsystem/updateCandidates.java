package com.counttoast.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateCandidates extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4;
    Button update, logout;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_candidates);
        editText1 = (EditText) findViewById(R.id.t1);
        editText2 = (EditText) findViewById(R.id.t2);
        update = (Button) findViewById(R.id.button5);
        logout = (Button) findViewById(R.id.button6);
        mydb = new DBHelper(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String et1 = editText1.getText().toString();
                int rs = Integer.parseInt(editText2.getText().toString());
                boolean isinserted = mydb.insertCandidate(et1, rs);

                if (isinserted == true) {
                    Toast.makeText(updateCandidates.this, "Successfully inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(updateCandidates.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}