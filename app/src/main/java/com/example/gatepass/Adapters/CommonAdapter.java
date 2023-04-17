package com.example.gatepass.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gatepass.Models.CommonModel;
import com.example.gatepass.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommonAdapter extends FirebaseRecyclerAdapter<CommonModel,CommonAdapter.myViewHolder> {

    public CommonAdapter (@NonNull FirebaseRecyclerOptions<CommonModel> options) {
        super( options );
    }

    @Override
    protected void onBindViewHolder (@NonNull CommonAdapter.myViewHolder holder, int position, @NonNull CommonModel model) {
        holder.name.setText(model.getName());
        holder.dept.setText(model.getDepartment());
        holder.status.setText(model.getStatus());
        Glide.with( holder.img.getContext()).load( model.getUrl()).into( holder.img);
    }

    @NonNull
    @Override
    public CommonAdapter.myViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext()).inflate( R.layout.status_item, parent, false);
        return new myViewHolder( view );
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView name,dept,status;
        public myViewHolder (@NonNull View itemView) {
            super( itemView );
            img = itemView.findViewById( R.id.profile_image);
            name = itemView.findViewById(R.id.common_name);
            dept = itemView.findViewById(R.id.common_dept);
            status = itemView.findViewById(R.id.common_status);

        }
    }
}
