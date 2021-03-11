package com.example.androidmedsch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListDokterAdapter extends RecyclerView.Adapter<ListDokterAdapter.ListDokterViewHold> {
    private ArrayList<ListDokter> list_dokter;
    final private ListItemClickListener listener_daftar;

    public ListDokterAdapter(ArrayList<ListDokter>list_dokter, ListItemClickListener listener){
        this.list_dokter = list_dokter;
        listener_daftar = listener;


        //Ketika udah pake database
//        list_dokter.clear();
//        list_dokter.addAll(list_dokter);
//        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListDokterViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_dokter, parent, false);
        return new ListDokterViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDokterViewHold holder, int position) {
        holder.iv_pengajar.setImageResource(list_dokter.get(position).getmImagePengajar());
        holder.tv_nama_pengajar.setText(list_dokter.get(position).getNama());
        holder.tv_gelar_pengajar.setText(list_dokter.get(position).getGelar());
    }

    @Override
    public int getItemCount() {
        return list_dokter.size();
    }

    public interface ListItemClickListener{
        void onClickListener(int clickedItem);
    }

    public class ListDokterViewHold extends RecyclerView.ViewHolder {
        ImageView iv_pengajar;
        TextView tv_nama_pengajar, tv_gelar_pengajar;
        public ListDokterViewHold(@NonNull View itemView) {
            super(itemView);

            iv_pengajar = itemView.findViewById(R.id.iv_pengajar);
            tv_nama_pengajar = itemView.findViewById(R.id.tv_nama_pengajar);
            tv_gelar_pengajar =  itemView.findViewById(R.id.tv_gelar_pengajar);
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        public void onClick(View v){
            int clickedPosition = getAdapterPosition();
            listener_daftar.onClickListener(clickedPosition);

        }
    }
}
