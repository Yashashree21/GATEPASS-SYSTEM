package com.example.gatepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Student_login extends AppCompatActivity {

    private TextView txtsignup,forgot;
    private Button loginbtn;
    private EditText email,password;
    private  FirebaseAuth fauth;
    private boolean pauth;
    EditText enteremail;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student_login );

        getSupportActionBar().setTitle("Student Page");

        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        ProgressDialog pd = new ProgressDialog( Student_login.this);

        fauth = FirebaseAuth.getInstance();

//        enteremail = findViewById(R.id.enteremail);
        forgot = findViewById(R.id.forgotPassword);
        forgot.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent(Student_login.this,ForgotPassword.class) );
            }
        } );

        loginbtn = findViewById(R.id.btnlogin);
        loginbtn.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                if (TextUtils.isEmpty(email.getText().toString().trim())){
                    email.setError("Required");
                }else  if (TextUtils.isEmpty(password.getText().toString().trim())){
                    password.setError("Required");
                }else {
                    checkcredentials(email.getText().toString().trim(),password.getText().toString().trim(),pd);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setMessage("Please Wait While We Check Credentials In Our Database...");
                    pd.setCancelable(false);
                    pd.show();
                }
            }
        } );
    }




    private void checkcredentials (String emailcheck, String passcheck,ProgressDialog pd) {
        fauth.signInWithEmailAndPassword(emailcheck,passcheck).addOnCompleteListener( new OnCompleteListener<AuthResult>( ) {
            @Override
            public void onComplete (@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText( Student_login.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT ).show( );
                    startActivity( new Intent(Student_login.this,ProfilePage.class) );
                    finish();
                }else {
                    Toast.makeText( Student_login.this, ""+task.getException().getMessage(), Toast.LENGTH_LONG ).show( );
                }
                pd.dismiss();
            }
        } );
    }
}