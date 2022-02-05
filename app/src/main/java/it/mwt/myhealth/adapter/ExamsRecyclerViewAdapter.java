package it.mwt.myhealth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.mwt.myhealth.R;

public class ExamsRecyclerViewAdapter extends RecyclerView.Adapter<ExamsRecyclerViewAdapter.ViewHolder> {

    private String[] exams;


    public  ExamsRecyclerViewAdapter (String[] exams){
        this.exams = exams;
        if (this.exams == null) this.exams = new String[0];
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_exams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //riprendo elemento  del viewholder (name) e setto i parametri
        holder.name.setText(exams[position]);
    }

    @Override
    public int getItemCount() {
        return exams.length;
    }


    //costruisco il viewholder per ritornarlo nel createViewHolder;
    public class ViewHolder extends RecyclerView.ViewHolder{


        //elemento dell'adapter dove vogliamo fare il display della lista
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.exams_adapter);
        }
    }
}
