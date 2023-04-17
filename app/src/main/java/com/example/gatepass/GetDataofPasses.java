package com.example.gatepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gatepass.Adapters.GetDataOfPassAdapter;
import com.example.gatepass.Models.Getdataofpassmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetDataofPasses extends AppCompatActivity {

    RecyclerView recview;
    GetDataOfPassAdapter adapter;
    FloatingActionButton btnreg;
    Button getdata;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get_dataof_passes );


        ///////////////WORK IS YET TO BE DONE

        btnreg = findViewById(R.id.fab_reg_user);
        btnreg.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent(  GetDataofPasses.this,Student_Register.class) );
            }
        } );

        getdata = findViewById(R.id.gethistory);
        getdata.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity(new Intent( GetDataofPasses.this,CommonActivity.class ));
            }
        } );


        getSupportActionBar().setTitle("GatePass Requests");

        recview=(RecyclerView)findViewById(R.id.recyclerview_getdata);
        recview.setLayoutManager(new LinearLayoutManager(this));
        ProgressDialog progressDialog = new ProgressDialog( this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please Wait While We Load Data...");
        progressDialog.show();

        FirebaseRecyclerOptions<Getdataofpassmodel> options =
                new FirebaseRecyclerOptions.Builder<Getdataofpassmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PASSDATA"), Getdataofpassmodel.class)
                        .build();

        adapter=new GetDataOfPassAdapter(options);
        recview.setAdapter(adapter);
        progressDialog.dismiss();

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}