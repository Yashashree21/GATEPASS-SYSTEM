package com.example.gatepass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gatepass.Models.Register_model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ApplyGatePass extends AppCompatActivity {


    Button btnapplygatepass;
    String []year = {"1St Year","2nd Year","3rd Year","4th Year"};
    String []list = {"Information Technology","Computer Engineering","Electronic & Telecommunication","Mechanical Engineering","Artificial Intelligence","Machine Learning","Electrical Engineering","Civil Engineering"};
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView2;
    ArrayAdapter<String> adapteritems;
    ArrayAdapter<String> adapteritemsdept;
    EditText fullname,rollno,phoneno,reason;
    private FirebaseAuth mauth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_apply_gate_pass );

        getSupportActionBar().hide();


        fullname = findViewById(R.id.inputUsername);
        rollno = findViewById(R.id.inputrollno);
        phoneno = findViewById(R.id.inputphone);
        reason = findViewById(R.id.input_reason);
        ProgressDialog pd = new ProgressDialog( ApplyGatePass.this);

        autoCompleteTextView = findViewById(R.id.auto_completetext_year);
        adapteritems = new ArrayAdapter<>( this,R.layout.list_item,year );
        autoCompleteTextView.setAdapter(adapteritems);
        autoCompleteTextView.setOnItemClickListener( new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
//                Toast.makeText( ApplyGatePass.this, "SELECTED ITEM IS : "+item, Toast.LENGTH_SHORT ).show( );

            }
        } );


        autoCompleteTextView2 = findViewById(R.id.auto_completetext_dept);
        adapteritemsdept = new ArrayAdapter<>( this,R.layout.list_item2,list);
        autoCompleteTextView2.setAdapter(adapteritemsdept);
        autoCompleteTextView2.setOnItemClickListener( new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
            }
        } );


//        btnapplygatepass = findViewById(R.id.btnapplygatepass);
//        btnapplygatepass.setOnClickListener( new View.OnClickListener( ) {
//            @Override
//            public void onClick (View v) {
//                if (TextUtils.isEmpty( fullname.getText().toString().trim())){
//                    fullname.setError("Required");
//                }else if (TextUtils.isEmpty(autoCompleteTextView.getText().toString().trim())){
//                    autoCompleteTextView.setError("Required");
//                }else if (TextUtils.isEmpty(rollno.getText().toString().trim())){
//                    rollno.setError("Required");
//                }else if (TextUtils.isEmpty(phoneno.getText().toString().trim())){
//                    phoneno.setError("Required");
//                }else if (TextUtils.isEmpty(autoCompleteTextView2.getText().toString().trim())){
//                    ///////for dropdown checking
//                    autoCompleteTextView2.setError("Required");
//                }
//                else if (TextUtils.isEmpty(reason.getText().toString().trim())){
//                    reason.setError("Required");
//                }else if (phoneno.getText().toString().trim().length()!=10){
//                    phoneno.setError("Phone Number Should Be 10 Digit Long,,Invalid Phone Number");
//                } else {
////                    confirmuser(pd);
//                }
//            }
//        } );

    }

//    public void confirmuser (String reason) {
//        AlertDialog.Builder builder = new AlertDialog.Builder( ApplyGatePass.this);
//
//        builder.setMessage("Are You Sure you Want To Apply For Gate Pass ?");
//        builder.setTitle("Confirmation!");
//        builder.setCancelable(false);
//
//
//        ProfileModel pm = new ProfileModel();
//        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
//           senduserdatatobackend(pm.getFullname(),autoCompleteTextView.getText().toString().trim(),pm.getRollno(),
//                                 pm.getPhoneno(),autoCompleteTextView2.getText().toString().trim(),reason);
////            pd.setProgressStyle( ProgressDialog.STYLE_SPINNER);
////            pd.setMessage("Please Wait While We Register Yourself To Our Database...");
////            pd.setCancelable(false);
////            pd.show();
//        });
//        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
//            dialog.cancel();
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

//    public void senduserdatatobackend (String a, String b, String c, String d, String e, String f, String email, String uid, FirebaseDatabase database) {
//        databaseReference = database.getReference( "GATEPASSES").child( uid);
//        Register_model registermodel = new Register_model();
//        registermodel.setFullname(a);
//        registermodel.setEmail(email);
//        registermodel.setYear(b);
//        registermodel.setRollno(c);
//        registermodel.setPhoneno(d);
//        registermodel.setDepartment(e);
//        registermodel.setReason(f);
//
////        databaseReference.addValueEventListener( new ValueEventListener( ) {
////            @Override
////            public void onDataChange (@NonNull DataSnapshot snapshot) {
////                databaseReference.setValue(registermodel);
////                Toast.makeText( ApplyGatePass.this, "Your Request For Gate Pass Has Been Send To Admin\n You Will Be Notified Soon", Toast.LENGTH_LONG ).show( );
////                Intent i = new Intent( ApplyGatePass.this, MainActivity.class  );
////                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                startActivity(i);
////                finish();
//////                pd.dismiss();
////            }
////
////            @Override
////            public void onCancelled (@NonNull DatabaseError error) {
////                Toast.makeText( ApplyGatePass.this, ""+error.getMessage(), Toast.LENGTH_SHORT ).show( );
////            }
////        } );
//
//        databaseReference.addChildEventListener( new ChildEventListener( ) {
//            @Override
//            public void onChildAdded (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                databaseReference.setValue(registermodel);
//                Toast.makeText( ApplyGatePass.this, "sucess", Toast.LENGTH_SHORT ).show( );
//            }
//
//            @Override
//            public void onChildChanged (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved (@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled (@NonNull DatabaseError error) {
//
//            }
//        } );
//    }

}