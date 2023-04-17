package com.example.gatepass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.gatepass.Adapters.ApprovedAdapter;
import com.example.gatepass.Adapters.CommonAdapter;
import com.example.gatepass.Models.ApprovedModel;
import com.example.gatepass.Models.CommonModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CommonActivity extends AppCompatActivity {

    RecyclerView recview;
    CommonAdapter adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_common );
        getSupportActionBar().setTitle("History");

        recview=findViewById(R.id.common_list);
        recview.setLayoutManager(new LinearLayoutManager(this));
        ProgressDialog progressDialog = new ProgressDialog( this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please Wait While We Load Data...");
        progressDialog.show();


        FirebaseRecyclerOptions<CommonModel> options =
                new FirebaseRecyclerOptions.Builder<CommonModel>()
                        .setQuery( FirebaseDatabase.getInstance().getReference().child( "CommonList"), CommonModel.class)
                        .build();

        adapter=new CommonAdapter(options);
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