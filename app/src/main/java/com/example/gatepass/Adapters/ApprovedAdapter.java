package com.example.gatepass.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gatepass.Models.ApprovedModel;
import com.example.gatepass.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ApprovedAdapter extends FirebaseRecyclerAdapter<ApprovedModel,ApprovedAdapter.myViewHolder> {


//    VERY VERY IMPORTANT FILE DON'T MAKE ANY CHANGES
    public ApprovedAdapter (@NonNull FirebaseRecyclerOptions<ApprovedModel> options) {
        super( options );
    }

    @Override
    protected void onBindViewHolder (@NonNull ApprovedAdapter.myViewHolder holder, int position, @NonNull ApprovedModel model) {
        holder.name.setText(model.getFullname());
        holder.dept.setText(model.getDepartment());
        Glide.with(holder.img.getContext()).load(model.getUrl()).into(holder.img);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yy");
        String str = formatter.format(date);
        holder.date.setText(str);
        holder.delete.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                FirebaseDatabase.getInstance().getReference().child( "Approvedlist")
                        .child( Objects.requireNonNull( getRef( position ).getKey( ) ) ).removeValue();
            }
        } );
    }

    @NonNull
    @Override
    public ApprovedAdapter.myViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext()).inflate( R.layout.approved_list, parent, false);
        return new myViewHolder(view);
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        ImageView delete;
        TextView name,dept,date;
        public myViewHolder (@NonNull View itemView) {
            super( itemView );

            img = itemView.findViewById( R.id.profile_image);
            name = itemView.findViewById(R.id.name);
            dept = itemView.findViewById(R.id.dept_app);
            date = itemView.findViewById(R.id.dept_date);
            delete = itemView.findViewById(R.id.dept_delete);

        }
    }
}
