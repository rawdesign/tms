package com.android.tmsoneprototype.ui.property;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tmsoneprototype.R;
import com.android.tmsoneprototype.db.model.PropertyList;
import com.android.tmsoneprototype.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static Activity activity;
    static List<PropertyList> propertys;

    public PropertyAdapter(Activity activity, List<PropertyList> propertys) {
        this.activity = activity;
        this.propertys = propertys;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_property, parent, false);
        return new PropertyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PropertyHolder holder = (PropertyHolder) viewHolder;
        ((PropertyHolder)holder).bindData(propertys.get(position));
    }

    @Override
    public int getItemCount() {
        return propertys.size();
    }

    /* VIEW HOLDERS */

    static class PropertyHolder extends RecyclerView.ViewHolder {
        ImageView ivProperty;
        TextView tvPrice;
        TextView tvTitle;
        TextView tvDescription;
        ImageView ivStatus;
        public PropertyHolder(View itemView) {
            super(itemView);
            ivProperty = (ImageView) itemView.findViewById(R.id.iv_property);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_status);
        }

        void bindData(PropertyList list){
            String image = list.getImg();
            String price = list.getPrice();
            final String title = list.getTitle();
            String address = list.getAddress();
            String status = list.getStatus();

            Picasso.with(activity).load(Utils.getImageURL(image))
                    .error(R.drawable.placeholder_upload)
                    .placeholder(R.drawable.placeholder_upload)
                    .into(ivProperty);
            tvPrice.setText(Utils.formatRupiah(Double.valueOf(price)));
            tvTitle.setText(title);
            tvDescription.setText(address);
            switch (status) {
                case "pending":
                    Picasso.with(activity).load(R.drawable.hourglass_24).into(ivStatus);
                    break;
                case "success":
                    Picasso.with(activity).load(R.drawable.checkmark_24).into(ivStatus);
                    break;
                case "failed":
                    Picasso.with(activity).load(R.drawable.delete_24).into(ivStatus);
                    break;
                default:
                    Picasso.with(activity).load(R.drawable.hourglass_24).into(ivStatus);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.displayToast(activity, title, Toast.LENGTH_SHORT);
                }
            });
        }
    }

}