package com.example.rps.krenwk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rps.krenwk.R;

import java.util.List;

public class LoginPhone extends AppCompatActivity {
    EditText nophone;
    Button blogin;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_login);
        //getSupportActionBar().hide();

        //initialize editTexts and buttons
        nophone = (EditText) findViewById(R.id.editText);
        blogin = (Button) findViewById(R.id.buttonlogin);
        b2 = (Button) findViewById(R.id.button2);

        //DabHandler object initialization
        DBhelper db = new DBhelper(getApplicationContext());
        //get data from database
        final List<Users> user = db.getAllUsers();
        //if no data added add a new data to table
        if (user.size() == 0) {
            Users u = new Users(
                    "1",
                    "aata",
                    "0123",
                    "a@gmail.com",
                    "-6.233333",
                    "106.853253");
            db.addUser(u);
        }
        db.close();

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nophone.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_LONG).show();
                }  else {
                    DBhelper db = new DBhelper(getApplicationContext());
                    //get data from database
                    final List<Users> user = db.getAllUsers();
                    String username = nophone.getText().toString().trim();
                    if (username.equals(user.get(0).getNomorphone())) {
                        Intent i = new Intent(getApplicationContext(), LoginOtp.class);
                        startActivity(i);
                    }  else if (!username.equals(user.get(0).getNomorphone())) {
                        Toast.makeText(LoginPhone.this, "Wrong Phone Number", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), LoginEmail.class);
                startActivity(j);


            }
        });
    }
}