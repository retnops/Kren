package com.example.rps.krenwk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rps.krenwk.R;

import java.util.List;

public class LoginEmail extends AppCompatActivity {
    EditText amail;
    Button blogin;
    Button bmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_login);
        //getSupportActionBar().hide();
        //initialize editTexts and buttons
        amail = (EditText) findViewById(R.id.editText);
        blogin = (Button) findViewById(R.id.buttonlogin);
        bmail = (Button) findViewById(R.id.buttonmail);

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
                if (amail.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter Email Address", Toast.LENGTH_LONG).show();
                }  else {
                    DBhelper db = new DBhelper(getApplicationContext());
                    //get data from database
                    final List<Users> user = db.getAllUsers();
                    String username = amail.getText().toString().trim();
                    if (username.equals(user.get(0).getAlamatmail())) {
                        Intent k = new Intent(getApplicationContext(), LoginOtp.class);
                        startActivity(k);
                    }  else if (!username.equals(user.get(0).getAlamatmail())) {
                        Toast.makeText(LoginEmail.this, "Wrong Email Address", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        bmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), LoginPhone.class);
                startActivity(Intent);


            }
        });
    }
}