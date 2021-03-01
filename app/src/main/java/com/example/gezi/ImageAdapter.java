package com.example.gezi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements Filterable {

    private Context mcontext;
    private List<Upload> muploads;
    private List<Upload> m1uploads;

    public ImageAdapter(Context context, List<Upload> uploads) {
        mcontext = context;
        muploads = uploads;
        m1uploads=uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadCurrent = muploads.get(position);
        holder.textViewSehir.setText(uploadCurrent.getSehir());
        holder.textViewMetin.setText(uploadCurrent.getMetin());
        holder.kullanıcı.setText(uploadCurrent.getKullanıcı());
        Picasso.get().load(uploadCurrent.getImageURL()).fit().into(holder.ımageView);
    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();

                List<Upload> filtered = new ArrayList<>();

                if (query.isEmpty()) {
                    filtered.addAll(m1uploads);
                } else {
                    for (Upload u : muploads) {
                        if (u.getSehir().toLowerCase().contains(query.toLowerCase())) {
                            filtered.add(u);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }

            @Override
            @SuppressWarnings("unchecked")
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                muploads = (ArrayList<Upload>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{


        public TextView textViewSehir;
        public TextView textViewMetin;
        public TextView kullanıcı;
        public ImageView ımageView;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSehir=itemView.findViewById(R.id.upload_sehir);
            ımageView=itemView.findViewById(R.id.image_view_upload);
            textViewMetin=itemView.findViewById(R.id.upload_metin);
            kullanıcı=itemView.findViewById(R.id.upload_kullanıcı);

        }
    }
}
