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

public class Admin_Login extends AppCompatActivity {

    private TextView forgot,txtsignup;
    Button loginbtn;
    EditText adminname,adminpassword;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_login );

        getSupportActionBar().setTitle("Admin Page");

//        txtsignup = findViewById(R.id.textViewSignUp);
//        txtsignup.setOnClickListener( new View.OnClickListener( ) {
//            @Override
//            public void onClick (View v) {
//                startActivity( new Intent(Admin_Login.this,Student_Register.class) );
//            }
//        } );



        forgot = findViewById(R.id.forgotPassword);
        forgot.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                Toast.makeText( Admin_Login.this, "PLEASE CONTACT ADMINISTRATOR TO RESET PASSWORD", Toast.LENGTH_LONG ).show( );
            }
        } );

        adminname = findViewById(R.id.inputEmail);
        adminpassword = findViewById(R.id.inputPassword);
        loginbtn = findViewById(R.id.btnlogin);
        loginbtn.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                if (TextUtils.isEmpty(adminname.getText().toString().trim())){
                    adminname.setError("Required..");
                }else if (TextUtils.isEmpty(adminpassword.getText().toString().trim())){
                    adminpassword.setError("Required..");
                }else {
                    loginadmin(adminname.getText().toString().trim(),adminpassword.getText().toString().trim());
                }
            }
        } );
    }

    private void loginadmin (String a, String b) {

        if (a.equals("admin") && b.equals("admin")){
            startActivity( new Intent( Admin_Login.this,GetDataofPasses.class ) );
        }else {
            Toast.makeText( this, "Invalid User Name Or Password", Toast.LENGTH_SHORT ).show( );
        }
    }
}