package com.example.gatepass.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gatepass.Models.ApprovedModel;
import com.example.gatepass.Models.CommonModel;
import com.example.gatepass.Models.Getdataofpassmodel;
import com.example.gatepass.R;
import com.example.gatepass.SendSMS;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class GetDataOfPassAdapter extends FirebaseRecyclerAdapter<Getdataofpassmodel,GetDataOfPassAdapter.myViewHolder> {

    public GetDataOfPassAdapter (@NonNull FirebaseRecyclerOptions<Getdataofpassmodel> options) {
        super( options );
    }


    @Override
    protected void onBindViewHolder (@NonNull myViewHolder holder,  final int position, @NonNull Getdataofpassmodel model) {
        holder.username.setText(model.getFullname());
        holder.year.setText(model.getYear());
        holder.dept.setText(model.getDepartment());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yy");
        String str = formatter.format(date);
        holder.datetime.setText(str);
        holder.reason.setText(model.getReason());

        //////////////ACCEPT button code//
        holder.accept.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder( v.getContext( ));
                builder2.setMessage("Are You Sure Admin , You Want To Allow GatePass To  \n"+model.getFullname());
                builder2.setTitle("Confirmation !");
                builder2.setCancelable(false);
                builder2.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
//                    senduserdatatoapprovedlist(model.getFullname(),model.getDepartment(),model.getEmail(),"Approved");
//                    Toast.makeText( v.getContext(), "GATE PASS Of "+model.getFullname() +" Has Been APPROVED", Toast.LENGTH_SHORT ).show( );
//                    SendSMS sms = new SendSMS();
//                    sms.
//
//                  SEND MESSAGE TO USER ABOUT REQUEST HAS BEEN APPROVED


                    ////SEND USER MESSAGE ABOUT REQUEST HAS BEEN ACCEPTED
                    SendSMS sms = new SendSMS();
                    sms.sendmessage(model.getEmail(),"approved");
                    Toast.makeText( v.getContext(), "Notification Delivered To "+model.getEmail(), Toast.LENGTH_SHORT ).show( );
                    //SEND USER DATA TO APPROVED LIST
                    senduserdatatoapprovedlist( model.getFullname( ),model.getDepartment(),model.getUrl());
                    ///send user data to common list
                    senduserdatatocommonlist( model.getFullname( ),model.getDepartment(),"approved",model.getUrl());
                    //DELETING DATA FROM SERVER
                    FirebaseDatabase.getInstance().getReference().child("PASSDATA")
                            .child( Objects.requireNonNull( getRef( position ).getKey( ) ) ).removeValue();
//                            .addOnSuccessListener( new OnSuccessListener<Void>( ) {
//                                @Override
//                                public void onSuccess (Void unused) {
//                                    Toast.makeText( sms, "User Request Accepted", Toast.LENGTH_SHORT ).show( );
//                                }
//                            } );
                    //////////to keep record of data
//                    AddHistory ah = new AddHistory();
//                    ah.addhistory(model.getFullname(),model.getDepartment(),"approved");
                    //////////////
                });

                builder2.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder2.create();
                alertDialog.show();
            }
        } );


        //////////////delete button code//
        holder.decline.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder( v.getContext( ));
                builder.setMessage("Are You Sure Admin , You Want To Decline Request Of \n"+model.getFullname());
                builder.setTitle("Delete !");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    FirebaseDatabase.getInstance().getReference().child("PASSDATA")
                                    .child( Objects.requireNonNull( getRef( position ).getKey( ) ) ).removeValue()
                            .addOnSuccessListener( new OnSuccessListener<Void>( ) {
                                @Override
                                public void onSuccess (Void unused) {
                                    Toast.makeText( v.getContext(), "User Request Declined\n Gate Pass Rejected", Toast.LENGTH_SHORT ).show( );

                                    ///////SEND MESSAGE TO USER ABOUT
                                    SendSMS smsd = new SendSMS();
                                    smsd.sendmessage(model.getEmail(),"rejected");
                                    //SEND USER DATA TO REJECTED LIST
                                    senduserdatatocommonlist( model.getFullname( ),model.getDepartment(),"rejected",model.getUrl());                                }
                            } )
                            .addOnFailureListener( new OnFailureListener( ) {
                                @Override
                                public void onFailure (@NonNull Exception e) {
                                    Toast.makeText( v.getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT ).show( );
                                }
                            } );
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        } );
    }



    private void senduserdatatocommonlist (String fullname, String department, String status,String url) {

        CommonModel cm = new CommonModel();
        cm.setName(fullname);
        cm.setDepartment(department);
        cm.setStatus(status);
        cm.setUrl(url);
        FirebaseDatabase.getInstance().getReference().child("CommonList").child(fullname)
                .setValue(cm);
    }




    //    APPROVED LIST SEND TO SECURITY
    public void senduserdatatoapprovedlist (String fullname, String department,String url) {
        ApprovedModel am = new ApprovedModel();
        am.setFullname(fullname);
        am.setDepartment(department);
        am.setUrl(url);
        FirebaseDatabase.getInstance().getReference().child("Approvedlist").child(fullname)
                .setValue(am);
    }





    @NonNull
    @Override
    public myViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView username,year,dept,datetime,reason;
        ImageView accept,decline;
        public myViewHolder (@NonNull View itemView) {
            super( itemView );

            accept = itemView.findViewById(R.id.accept);
            decline = itemView.findViewById(R.id.decline);
            username = itemView.findViewById(R.id.username);
            year= itemView.findViewById(R.id.year);
            dept = itemView.findViewById(R.id.dept);
            datetime = itemView.findViewById(R.id.datetime);
            reason = itemView.findViewById(R.id.reason);
        }
    }



}
