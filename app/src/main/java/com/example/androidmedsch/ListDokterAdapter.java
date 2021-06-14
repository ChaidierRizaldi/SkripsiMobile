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

//kurang implement Filterable
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

        String nama = list_dokter.get(position).getNama();
        String jabatan = list_dokter.get(position).getJabatan();
        String bidang = list_dokter.get(position).getBidangKepakaran();

        holder.tv_nama_pengajar.setText(nama);
        holder.tv_gelar_pengajar.setText(jabatan);
        holder.tv_bidang_pengajar.setText(bidang);

    }

    @Override
    public int getItemCount() {

        return list_dokter.size();
    }


//    @Override
//    public Filter getFilter() {
//        return list_filter;
//    }
//
//    private Filter list_filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            ArrayList<ModelAllDokter> filteredList = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0){
//                filteredList.addAll(list_dokter_full);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (ModelAllDokter item : list_dokter_full){
//                    if (item.getNama().toLowerCase().contains(filterPattern)){
//                        filteredList.add(item);
//                    }
//                }
//            }
//
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            list_dokter.clear();
//            list_dokter.addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//    };


    public class ListDokterViewHold extends RecyclerView.ViewHolder{
        TextView tv_nama_pengajar, tv_gelar_pengajar, tv_bidang_pengajar;
        AllDokter data_dokter;
        public ListDokterViewHold(@NonNull View itemView) {
            super(itemView);

            tv_nama_pengajar = itemView.findViewById(R.id.tv_nama_pengajar);
            tv_gelar_pengajar =  itemView.findViewById(R.id.tv_gelar_pengajar);
            tv_bidang_pengajar = itemView.findViewById(R.id.tv_bidang_pengajar);

            itemView.setOnClickListener(v -> {
                listener.onItemClick(data_dokter, getBindingAdapterPosition());
            });
        }
    }


    public interface OnClickListener{
        void onItemClick(AllDokter dokter, int position);
    }
}
