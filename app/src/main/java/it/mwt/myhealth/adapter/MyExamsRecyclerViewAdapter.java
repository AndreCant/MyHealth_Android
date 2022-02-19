package it.mwt.myhealth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import it.mwt.myhealth.R;
import it.mwt.myhealth.model.Reservation;

public class MyExamsRecyclerViewAdapter extends RecyclerView.Adapter<MyExamsRecyclerViewAdapter.ViewHolder> {

    private List<Reservation> reservations;

    private OnReservationSelected listener;

    public  MyExamsRecyclerViewAdapter (List<Reservation> reservations){
        this.reservations = reservations;
        if (this.reservations == null) this.reservations =  new ArrayList<Reservation>();
    }

    public void setOnReservationSelected(OnReservationSelected listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_exams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //riprendo elemento  del viewholder (name) e setto i parametri
        holder.name.setText(reservations.get(position).getReservationDate());
        holder.reservation_date.setText(reservations.get(position).getReservationDate());
        holder.start_hour.setText(reservations.get(position).getStartHour());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    //costruisco il viewholder per ritornarlo nel createViewHolder;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //elemento dell'adapter dove vogliamo fare il display della lista
        TextView name;
        TextView reservation_date;
        TextView start_hour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.my_exams_adapter);
            reservation_date = itemView.findViewById(R.id.reservation_date);
            start_hour = itemView.findViewById(R.id.start_hour);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Reservation reservation = reservations.get(getAbsoluteAdapterPosition());

            if (listener != null) listener.onSelected(reservation);
        }
    }

    public interface OnReservationSelected {
        void onSelected(Reservation reservation);
    }
}
