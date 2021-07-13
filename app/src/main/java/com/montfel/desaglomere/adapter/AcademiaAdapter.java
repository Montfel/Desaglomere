package com.montfel.desaglomere.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.montfel.desaglomere.R;
import com.montfel.desaglomere.model.AcademiaModel;

import java.util.List;

public class AcademiaAdapter extends RecyclerView.Adapter<AcademiaAdapter.MyViewHolder> {

    private List<AcademiaModel> listaAcademia;

    public AcademiaAdapter(List<AcademiaModel> listaAcademia) {
        this.listaAcademia = listaAcademia;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_academia_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AcademiaModel academia = listaAcademia.get(position);
        holder.tvAcademia.setText(academia.getHorario());
    }

    @Override
    public int getItemCount() {
        return this.listaAcademia.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvAcademia;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvAcademia = itemView.findViewById(R.id.tvAcademia);
        }
    }
}
