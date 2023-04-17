package com.example.gatepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageSlider imageSlider;
    private LinearLayout studentlogin,wardenlogin,adminlogin,contactus;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

//      IMAGE SLIDER CODE
        imageSlider = findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModel =  new ArrayList<>();
        slideModel.add(new SlideModel(R.drawable.palloti,ScaleTypes.FIT));
        slideModel.add(new SlideModel(R.drawable.pall,ScaleTypes.FIT));
        slideModel.add(new SlideModel(R.drawable.pallotti_img,ScaleTypes.FIT));
        slideModel.add(new SlideModel(R.drawable.pallotti_img1,ScaleTypes.FIT));
        slideModel.add(new SlideModel(R.drawable.hostel_img1,ScaleTypes.FIT));
        imageSlider.setImageList(slideModel,ScaleTypes.FIT);

        studentlogin = findViewById(R.id.student_login);
        studentlogin.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                Intent i =new Intent(MainActivity.this,Student_login.class);
                startActivity(i);
            }
        } );


        wardenlogin = findViewById(R.id.warden_login);
        wardenlogin.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                Intent i =new Intent( MainActivity.this, Security_Login.class);
                startActivity(i);
            }
        } );


        adminlogin = findViewById(R.id.admin_login);
        adminlogin.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                Intent i =new Intent(MainActivity.this,Admin_Login.class);
                startActivity(i);
            }
        } );


        contactus = findViewById(R.id.contact_us);
        contactus.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                Intent i =new Intent(MainActivity.this,Contact_Us.class);
                startActivity(i);
            }
        } );




        /////code for messaging
        FirebaseMessaging.getInstance().subscribeToTopic( "notification")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }
                    }
                });



    }

    @Override
    public void onBackPressed ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Are You Sure you Wanna Exit ?");

        // Set Alert Title
        builder.setTitle("Confirmation!");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}