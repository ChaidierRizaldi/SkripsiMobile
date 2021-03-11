package com.example.androidmedsch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListJadwalAdapter extends RecyclerView.Adapter<ListJadwalAdapter.ListJadwalViewHold> {

    private ArrayList<ListJadwal> models;

    public  ListJadwalAdapter(ArrayList<ListJadwal> list_jadwal){
        this.models = list_jadwal;
    }

    @NonNull
    @Override
    public ListJadwalViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide, parent, false);
        return new ListJadwalViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListJadwalViewHold holder, int position) {
        holder.jam.setText(models.get(position).getJamKuliah());
        holder.mata_kuliah.setText(models.get(position).getMataKuliah());
        holder.pengajar.setText(models.get(position).getPengajar());
        holder.ruang_kelas.setText(models.get(position).getRuangKelas());
        holder.status.setText(models.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ListJadwalViewHold extends RecyclerView.ViewHolder{
        TextView jam, mata_kuliah, pengajar, ruang_kelas, status;
        public ListJadwalViewHold(View view) {
            super(view);

            jam = view.findViewById(R.id.tv_jam);
            mata_kuliah = view.findViewById(R.id.tv_matkul);
            pengajar = view.findViewById(R.id.tv_pengajar);
            ruang_kelas = view.findViewById(R.id.tv_ruang_kelas);
            status = view.findViewById(R.id.tv_status);
        }
    }


}
