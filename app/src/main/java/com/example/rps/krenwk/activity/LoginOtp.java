package com.example.rps.krenwk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rps.krenwk.R;

public class LoginOtp extends AppCompatActivity implements View.OnClickListener {
    EditText txtotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_login);
        //getSupportActionBar().hide();

        txtotp = (EditText) findViewById(R.id.editText2);
        Button tombol = (Button) findViewById(R.id.buttonotp);

        tombol.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (txtotp.getText().toString().equals("0123")) {
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        } else
            Toast.makeText(LoginOtp.this, "You Input the Wrong OTP", Toast.LENGTH_SHORT).show();

    }
}

