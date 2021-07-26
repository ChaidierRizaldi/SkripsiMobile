package com.example.androidmedsch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmedsch.model.get.dokter.AllDokter;

import java.util.List;

public class ListDokterAdapter extends RecyclerView.Adapter<ListDokterAdapter.ListDokterViewHold> {

   public List<AllDokter> list_dokter;
   public OnClickListener listener;

    public ListDokterAdapter(List<AllDokter> list_dokter) {
        this.list_dokter = list_dokter;
        notifyDataSetChanged();

    }

    public void SetOnClickListener(OnClickListener listener_item){
        this.listener = listener_item;
    }

    @NonNull
    @Override
    public ListDokterViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_dokter, parent, false);
        return new ListDokterViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDokterViewHold holder, final int position) {
        final AllDokter data_dokter = list_dokter.get(position);

        String nama = list_dokter.get(position).getNama();
        String jabatan = list_dokter.get(position).getJabatan();
        String bidang = list_dokter.get(position).getBidangKepakaran();

        holder.tv_nama_pengajar.setText(nama);
        holder.tv_gelar_pengajar.setText(jabatan);
        holder.tv_bidang_pengajar.setText(bidang);

        holder.itemView.setOnClickListener(v -> {
           listener.onItemClick(data_dokter, position);
        });
    }

    @Override
    public int getItemCount() {

        return list_dokter.size();
    }

    public class ListDokterViewHold extends RecyclerView.ViewHolder{
        TextView tv_nama_pengajar, tv_gelar_pengajar, tv_bidang_pengajar;
        AllDokter data_dokter;
        public ListDokterViewHold(@NonNull View itemView) {
            super(itemView);

            tv_nama_pengajar = itemView.findViewById(R.id.tv_nama_pengajar);
            tv_gelar_pengajar =  itemView.findViewById(R.id.tv_gelar_pengajar);
            tv_bidang_pengajar = itemView.findViewById(R.id.tv_bidang_pengajar);
        }
    }


    public interface OnClickListener{
        void onItemClick(AllDokter dokter, int position);
    }
}
