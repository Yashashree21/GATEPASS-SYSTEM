package com.example.gatepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gatepass.Adapters.ApprovedAdapter;
import com.example.gatepass.Adapters.GetDataOfPassAdapter;
import com.example.gatepass.Models.ApprovedModel;
import com.example.gatepass.Models.Getdataofpassmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;

public class ApprovedList extends AppCompatActivity {
    RecyclerView recview;
    ApprovedAdapter adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_approved_list );

        getSupportActionBar().setTitle("Approved Students List");
        recview=findViewById(R.id.approved_list_recyclerview);
        recview.setLayoutManager(new LinearLayoutManager( this));
        ProgressDialog progressDialog = new ProgressDialog( this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please Wait While We Load Data...");
        progressDialog.show();


        FirebaseRecyclerOptions<ApprovedModel> options =
                new FirebaseRecyclerOptions.Builder<ApprovedModel>()
                        .setQuery( FirebaseDatabase.getInstance().getReference().child( "Approvedlist"), ApprovedModel.class)
                        .build();

        adapter=new ApprovedAdapter(options);
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