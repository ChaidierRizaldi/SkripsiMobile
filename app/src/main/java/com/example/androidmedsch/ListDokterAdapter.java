package com.example.androidmedsch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmedsch.model.get.dokter.AllDokter;

import java.util.List;

//kurang implement Filterable
public class ListDokterAdapter extends RecyclerView.Adapter<ListDokterAdapter.ListDokterViewHold> {

   public List<AllDokter> list_dokter;
   public Context context;

    public ListDokterAdapter(List<AllDokter> list_dokter, Context context) {
        this.list_dokter = list_dokter;
        this.context = context;
        notifyDataSetChanged();
    }

//    public void setData(List<AllDokter> list_dokter){
//     this.list_dokter = list_dokter;;
//        notifyDataSetChanged();
//   }

    @NonNull
    @Override
    public ListDokterViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_dokter, parent, false);
        return new ListDokterViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDokterViewHold holder, final int position) {

        String nama = list_dokter.get(position).getNama();
        String jabatan = list_dokter.get(position).getJabatan();

        holder.tv_nama_pengajar.setText(nama);
        holder.tv_gelar_pengajar.setText(jabatan);

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


    public static class ListDokterViewHold extends RecyclerView.ViewHolder  {
        ImageView iv_pengajar;
        TextView tv_nama_pengajar, tv_gelar_pengajar;

        public ListDokterViewHold(@NonNull View itemView) {
            super(itemView);

            iv_pengajar = itemView.findViewById(R.id.iv_pengajar);
            tv_nama_pengajar = itemView.findViewById(R.id.tv_nama_pengajar);
            tv_gelar_pengajar =  itemView.findViewById(R.id.tv_gelar_pengajar);
//            this.clickListener = clickListener;

//            itemView.setOnClickListener(this);
        }

//       @Override
//       public void onClick(View v) {
//            clickListener.onItemClick(getAdapterPosition());
//       }
   }
//
//
//    public interface OnClickListener{
//        void onItemClick(Integer position);
//    }
}
