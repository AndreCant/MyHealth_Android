package it.mwt.myhealth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.mwt.myhealth.R;
import it.mwt.myhealth.model.Doctor;
import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.util.ImageLoadTask;

public class DoctorsRecyclerViewAdapter extends RecyclerView.Adapter<DoctorsRecyclerViewAdapter.ViewHolder>{

    private List<Doctor> doctors;

    private DoctorsRecyclerViewAdapter.OnDoctorSelected listener;

    public  DoctorsRecyclerViewAdapter (List<Doctor> doctors){
        this.doctors = doctors;
        if (this.doctors == null) this.doctors =  new ArrayList<>();
    }

    public void setOnDoctorSelected(DoctorsRecyclerViewAdapter.OnDoctorSelected listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DoctorsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctors, parent, false);
        return new DoctorsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsRecyclerViewAdapter.ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.name.setText(doctor.getName() + " " + doctor.getSurname());
        if (doctor.getImageUrl() != null) new ImageLoadTask(doctor.getImageUrl(), holder.image).execute();
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.doctors_adapter);
            image = itemView.findViewById(R.id.doctor_list_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Doctor doctor = doctors.get(getAbsoluteAdapterPosition());

            if (listener != null) listener.onSelected(doctor);
        }
    }

    public interface OnDoctorSelected {
        void onSelected(Doctor doctor);
    }
}
