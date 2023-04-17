package com.example.gatepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private Button sendemailbtn;
    private EditText mail;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forgot_password );
        getSupportActionBar().hide();

        mail = findViewById(R.id.enteremail);

        sendemailbtn = findViewById(R.id.resetbtnid);
        sendemailbtn.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
               if (TextUtils.isEmpty(mail.getText().toString().trim())){
                   mail.setError("Enter Email First");
               }else{
                   sendresetpasswordlink(mail.getText().toString().trim());
               }
            }
        } );
    }

    private void sendresetpasswordlink (String email) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( ForgotPassword.this, "Password Reset Link Has Been Send to Your Email Address Kindly Check ", Toast.LENGTH_LONG ).show( );
                        }
                    }
                })
                .addOnFailureListener( new OnFailureListener( ) {
                    @Override
                    public void onFailure (@NonNull Exception e) {
                        Toast.makeText( ForgotPassword.this, ""+e.getMessage(), Toast.LENGTH_SHORT ).show( );
                    }
                } );

    }
}