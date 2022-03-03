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
import it.mwt.myhealth.model.Reservation;
import it.mwt.myhealth.util.ImageLoadTask;

public class MyExamsRecyclerViewAdapter extends RecyclerView.Adapter<MyExamsRecyclerViewAdapter.ViewHolder> {

    private List<Reservation> reservations;

    public  MyExamsRecyclerViewAdapter (List<Reservation> reservations){
        this.reservations = reservations;
        if (this.reservations == null) this.reservations =  new ArrayList<Reservation>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_exams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.name.setText(reservations.get(position).getExam());
        holder.reservation_date.setText(reservations.get(position).getReservationDate());
        holder.start_hour.setText(reservations.get(position).getStartHour());
        if (reservation.getImageUrl() != null) new ImageLoadTask(reservation.getImageUrl(), holder.image).execute();

    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView reservation_date;
        TextView start_hour;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.my_exams_adapter);
            reservation_date = itemView.findViewById(R.id.reservation_date);
            start_hour = itemView.findViewById(R.id.start_hour);
            image = itemView.findViewById(R.id.reservation_image);
        }
    }
}
