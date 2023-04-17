package com.example.gatepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gatepass.Models.Getdataofpassmodel;
import com.example.gatepass.Models.ProfileModel;
import com.example.gatepass.Models.Register_model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class ProfilePage extends AppCompatActivity {

    CardView history,logout;
    FloatingActionButton fab;
    TextView username,useremail,rollno,mobile,department,password,year;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String a="";
    String b="";
    String c="";
    String d="";
    String e="";
    String f="";
    String urlimg = "";


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_page );

        getSupportActionBar().setTitle("Profile Page");

        final String[] userid = {""};



        logout = findViewById(R.id.card2);
        logout.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder( ProfilePage.this);
                builder.setMessage("Are You Sure you Wanna Logout ?");
                builder.setTitle("Logout");
                builder.setCancelable(false);
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    startActivity( new Intent(ProfilePage.this,MainActivity.class) );
                    finish();
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        } );




        ProgressDialog progressDialog = new ProgressDialog( this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please Wait While We Load Data from Database...");
        progressDialog.show();


        username = findViewById(R.id.textView);
        useremail = findViewById(R.id.textView2);
        rollno = findViewById(R.id.roll_fld);
        mobile = findViewById(R.id.mobile_fld);
        department = findViewById(R.id.dept_fld);
        password = findViewById(R.id.pass_fld);
        year = findViewById(R.id.year_fld);


        final FirebaseDatabase[] database = {FirebaseDatabase.getInstance( )};
        DatabaseReference ref = database[0].getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        DatabaseReference userRef = ref.child("USERDATA").child(uid);

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                        ProfileModel profilemodel = dataSnapshot.getValue(ProfileModel.class);
                        username.setText( profilemodel.getFullname());
                        useremail.setText(profilemodel.getEmail());
                        rollno.setText(profilemodel.getRollno());
                        mobile.setText(profilemodel.getPhoneno());
                        department.setText(profilemodel.getDepartment());
                        password.setText(profilemodel.getPassword());
                        year.setText(profilemodel.getYear());
                        urlimg = profilemodel.getUrl();

                        a=profilemodel.getFullname();
                        b=profilemodel.getYear();
                        c=profilemodel.getRollno();
                        d=profilemodel.getPhoneno();
                        e=profilemodel.getDepartment();
                        f=profilemodel.getEmail();
                        progressDialog.dismiss();


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle errors here
                    Toast.makeText(ProfilePage.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT ).show( );
                }
            });


        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(ProfilePage.this)
                        .setContentHolder( new ViewHolder( R.layout.dialogcontent))
                        .setExpanded( true,500 )
                        .create();

                View  myview = dialogPlus.getHolderView();
                EditText reason = myview.findViewById(R.id.enter_reason);
                Button apply = myview.findViewById(R.id.apply_pass);
                apply.setOnClickListener( new View.OnClickListener( ) {
                    @Override
                    public void onClick (View v) {
                        if (TextUtils.isEmpty(reason.getText().toString().trim())){
                            reason.setError("Reason Required");
                        }else{
                            sendgatepassdata(a,b,c,d,e,f,reason.getText().toString().trim());
                        }
                    }
                } );
                dialogPlus.show();
            }
        } );
        }


    private void sendgatepassdata (String a, String b, String c, String d, String e, String f, String reason) {
        Getdataofpassmodel model = new Getdataofpassmodel( );
        model.setFullname( a );
        model.setYear( b );
        model.setRollno( c );
        model.setPhoneno( d );
        model.setDepartment( e );
        model.setEmail( f );
        model.setReason( reason );
        model.setUrl(urlimg);



        FirebaseDatabase.getInstance( ).getReference( ).child( "PASSDATA" )
                .child( FirebaseAuth.getInstance( ).getCurrentUser( ).getUid( ) )
                .setValue( model )
                .addOnSuccessListener( new OnSuccessListener<Void>( ) {
                    @Override
                    public void onSuccess (Void unused) {
                        Toast.makeText( ProfilePage.this, "Your Request For Gate Pass Has Been Send To Admin\n You Will Be Notified Soon", Toast.LENGTH_LONG ).show( );
                        startActivity( new Intent( ProfilePage.this, MainActivity.class ) );
                        finish( );
                        Intent i = new Intent( ProfilePage.this, MainActivity.class );
                        i.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity( i );
                        finish( );
                    }
                } )
                .addOnFailureListener( new OnFailureListener( ) {
                    @Override
                    public void onFailure (@NonNull Exception e) {
                        Toast.makeText( ProfilePage.this, "" + e.getMessage( ), Toast.LENGTH_SHORT ).show( );
                    }
                } );
    }

}