package com.example.gatepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Security_Login extends AppCompatActivity {


    private TextView forgot;
    private EditText username,password;
    Button login;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_security_login );
        getSupportActionBar().setTitle("Security Page");

        forgot = findViewById(R.id.forgotPassword);
        forgot.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                Toast.makeText( Security_Login.this, "PLEASE CONTACT ADMINISTRATOR TO RESET PASSWORD", Toast.LENGTH_LONG ).show( );
            }
        } );

        username = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);


        login = findViewById(R.id.btnlogin);
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (TextUtils.isEmpty(username.getText().toString())){
                    username.setError("Required");
                }else if (TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Required");
                }else {
                    loginsecurityguard(username.getText().toString().trim(),password.getText().toString().trim());
                }
            }
        } );
    }



    private void loginsecurityguard (String a, String b) {

        if (a.equals("security1") && b.equals("sec1@123") || a.equals("security2") && b.equals("sec2@123")){
            startActivity(new Intent( Security_Login.this,ApprovedList.class ) );
        }else {
            Toast.makeText( this, " Invalid Username Or Password", Toast.LENGTH_SHORT ).show( );
        }
    }
}