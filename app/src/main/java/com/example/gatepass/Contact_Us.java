package com.example.gatepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Contact_Us extends AppCompatActivity {


    TextView phone,email,askquery;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contact_us );

        getSupportActionBar().setTitle("Contact Us");



//        phone call code
        phone = findViewById(R.id.call);
        phone.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                String num ="9307742082";
                Uri u = Uri.parse("tel:" + num);
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                    finish();
                }
                catch (SecurityException s)
                {
                    Toast.makeText(Contact_Us.this, "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }
            }
        } );

//        email code
        email = findViewById(R.id.email_contact);
        email.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                String emailsend = "shrushtipingale@gmail.com";
                String emailsubject = "Communication Email";
                String emailbody = "Hi There ,I Wanna Communicate With You Regarding Major Project .....";

                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailsend});
                intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
                intent.putExtra(Intent.EXTRA_TEXT, emailbody);

                // set type of intent
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        } );



//      ask query on whatsapp
        askquery = findViewById(R.id.send_your_message);
        askquery.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                String phoneNumber = "+919307742082";
                String message = "Hello Major Project Team , I wanna Communicate With You Regarding..";

                Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + message);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        } );

    }


     boolean isAppInstalled (String s) {
        PackageManager packageManager = getPackageManager();
        boolean is_installed;

        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            is_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            is_installed = false;
            e.printStackTrace();
        }
        return is_installed;
    }

}