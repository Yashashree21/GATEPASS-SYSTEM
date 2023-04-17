package com.example.gatepass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gatepass.Models.Register_model;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

public class Student_Register extends AppCompatActivity {
    TextView already;
    String temp="";
    String []list = {"Information Technology","Computer Engineering","Electronic & Telecommunication","Mechanical Engineering","Artificial Intelligence","Machine Learning","Electrical Engineering","Civil Engineering"};
    String []yearlist = {"1st Year","2nd Year","3rd Year","4th Year"};
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView2;
    ArrayAdapter<String> adapteritems;

    private FirebaseAuth mauth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    ProgressDialog pd;
    Button browse;
    Bitmap bitmap;
    Uri filepath;
    ImageView img;

    private EditText fullname,email,rollno,phoneno,password;
    private Button registerbtn,imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        getSupportActionBar().hide();

        ProgressDialog pd = new ProgressDialog( Student_Register.this);

        /////select department
        autoCompleteTextView = findViewById(R.id.auto_completetext1);
        adapteritems = new ArrayAdapter<>( this,R.layout.list_item,yearlist );
        autoCompleteTextView.setAdapter(adapteritems);
        autoCompleteTextView.setOnItemClickListener( new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
            }
        } );


        autoCompleteTextView2 = findViewById(R.id.auto_completetext);
        adapteritems = new ArrayAdapter<>( this,R.layout.list_item,list );
        autoCompleteTextView2.setAdapter(adapteritems);
        autoCompleteTextView2.setOnItemClickListener( new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
            }
        } );

//      already have an account
        already = findViewById(R.id.alreadyHaveAccount);
        already.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent( Student_Register.this,Student_login.class ) );
                finish();
            }
        } );

        ///////////////code for image upload
        img = findViewById(R.id.img);
        browse = findViewById(R.id.browse);
        browse.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                Dexter.withActivity( Student_Register.this)
                        .withPermission( Manifest.permission.READ_EXTERNAL_STORAGE )
                        .withListener( new PermissionListener( ) {
                            @Override
                            public void onPermissionGranted (PermissionGrantedResponse response) {
                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image File"),1);
                            }

                            @Override
                            public void onPermissionDenied (PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown (PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        } ).check();

            }
        } );

        //////////////////////////////////////
        fullname = findViewById(R.id.inputUsername);
        email = findViewById(R.id.inputEmail);
        rollno = findViewById(R.id.inputrollno);
        phoneno = findViewById(R.id.inputphone);
        password = findViewById(R.id.inputPassword);
        registerbtn = findViewById(R.id.btnRegister);
        registerbtn.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                if (TextUtils.isEmpty(fullname.getText().toString().trim())){
                    fullname.setError("Required");
                }else if (TextUtils.isEmpty(email.getText().toString().trim())){
                    email.setError("Required");
                }else if (TextUtils.isEmpty(rollno.getText().toString().trim())){
                    rollno.setError("Required");
                }
                else if  (TextUtils.isEmpty(phoneno.getText().toString().trim())){
                    phoneno.setError("Required");
                }else if (TextUtils.isEmpty(autoCompleteTextView.getText().toString().trim())){
                    ///////for dropdown checking
                    autoCompleteTextView.setError("Required");
                }else if (TextUtils.isEmpty(autoCompleteTextView2.getText().toString().trim())){
                    ///////for dropdown checking
                    autoCompleteTextView2.setError("Required");
                }
                else if (password.getText().toString().trim().length()<6){
                    password.setError("Password Should Be More Than 5 Digit Long");
                }
                else if (TextUtils.isEmpty(password.getText().toString().trim())){
                    password.setError("Required");
                }else if (phoneno.getText().toString().trim().length()!=10){
                    phoneno.setError("Phone Number Should Be 10 Digit Long,,Invalid Phone Number");
                } else {
                    uploadimagetostorage();
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setMessage("Please Wait While We Upload Student Image To Our Database...");
                    pd.setCancelable(false);
                    pd.show();
                }
            }
        } );
    }


    public void uploadimagetostorage ( ) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference uploader=storage.getReference(fullname.getText().toString());
        uploader.putFile(filepath)
                .addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>( ) {
                    @Override
                    public void onSuccess (UploadTask.TaskSnapshot taskSnapshot) {
                        uploader.getDownloadUrl().addOnSuccessListener( new OnSuccessListener<Uri>( ) {
                            @Override
                            public void onSuccess (Uri uri) {
                                temp= filepath.toString();
//                                pd.setTitle("Creating user in firebase , Please Hang On Tight");
//                                pd.show();
//                                pd.setCancelable(false);
//                                createuserinfirebase(email.getText().toString(),password.getText().toString(),temp);
                                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
//                                        .addOnCompleteListener( new OnCompleteListener<AuthResult>( ) {
//                                            @Override
//                                            public void onComplete (@NonNull Task<AuthResult> task) {
//
//                                            }
//                                        } )
                                        .addOnSuccessListener( new OnSuccessListener<AuthResult>( ) {
                                            @Override
                                            public void onSuccess (AuthResult authResult) {
                                                Toast.makeText(Student_Register.this, "success", Toast.LENGTH_SHORT ).show( );
                                                senddatatofirebase(fullname.getText().toString().trim(),email.getText().toString().trim(),rollno.getText().toString().trim(),
                                                                   phoneno.getText().toString().trim(),autoCompleteTextView.getText().toString().trim(),
                                                                   password.getText().toString().trim(),
                                                                   autoCompleteTextView2.getText().toString().trim(),uri.toString());
                                            }
                                        } )

                                        .addOnFailureListener( new OnFailureListener( ) {
                                            @Override
                                            public void onFailure (@NonNull Exception e) {
                                                Toast.makeText( Student_Register.this, ""+e.getMessage(), Toast.LENGTH_SHORT ).show( );
                                            }
                                        } );

                                //////////////////////////////////////////////////
                            }
                        } );
                    }
                } );
    }


    public void createuserinfirebase (String emailaddress, String userpassword,String photo) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(emailaddress, userpassword)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>( ) {
                    @Override
                    public void onComplete (@NonNull Task<AuthResult> task) {
                            senddatatofirebase(fullname.getText().toString().trim(),email.getText().toString().trim(),rollno.getText().toString().trim(),
                                               phoneno.getText().toString().trim(),autoCompleteTextView.getText().toString().trim(),password.getText().toString().trim(),autoCompleteTextView2.getText().toString().trim(),photo);
                    }
                } ).addOnFailureListener( new OnFailureListener( ) {
                    @Override
                    public void onFailure (@NonNull Exception e) {
                        Toast.makeText( Student_Register.this, ""+e.getMessage(), Toast.LENGTH_SHORT ).show( );
                        pd.dismiss();
                    }
                } );

    }


    private void senddatatofirebase (String a, String b, String c, String d, String e, String f, String g,String photourl) {

        database = FirebaseDatabase.getInstance();
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = database.getReference("USERDATA").child(userid);
        Register_model registermodel = new Register_model();
        registermodel.setFullname(a);
        registermodel.setEmail(b);
        registermodel.setRollno(c);
        registermodel.setPhoneno(d);
        registermodel.setDepartment(g);
        registermodel.setPassword(f);
        registermodel.setUrl(photourl);
        registermodel.setYear(e);

        databaseReference.addValueEventListener( new ValueEventListener( ) {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(registermodel);
                Toast.makeText( Student_Register.this, "USER CREATED SUCCESSFULLY\nDATA ADDED SUCCESSFULLY TO OUR SERVER ", Toast.LENGTH_LONG ).show( );
                Intent i = new Intent( Student_Register.this,MainActivity.class  );
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
//                pd.dismiss();
                finish();
            }


            @Override
            public void onCancelled (@NonNull DatabaseError error) {
                Toast.makeText( Student_Register.this, ""+error.getMessage(), Toast.LENGTH_SHORT ).show( );
                pd.dismiss();
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==1  && resultCode==RESULT_OK)
        {
            filepath=data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream( filepath);
                bitmap= BitmapFactory.decodeStream( inputStream);
                img.setImageBitmap(bitmap);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}