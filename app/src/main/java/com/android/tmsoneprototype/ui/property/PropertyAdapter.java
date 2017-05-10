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
import com.android.tmsoneprototype.api.data.PropertyData;
import com.android.tmsoneprototype.util.Const;
import com.android.tmsoneprototype.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static Activity activity;
    static List<PropertyData> propertys;

    public PropertyAdapter(Activity activity, List<PropertyData> propertys) {
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

        void bindData(final PropertyData propertyData){
            Picasso.with(activity).load(Const.BASE_URL + propertyData.getPropertyImg())
                    .error(R.drawable.placeholder_upload)
                    .placeholder(R.drawable.placeholder_upload)
                    .into(ivProperty);
            tvPrice.setText(Utils.formatRupiah(Double.valueOf(propertyData.getPropertyPrice())));
            tvTitle.setText(propertyData.getPropertyTitle());
            tvDescription.setText(propertyData.getPropertyAddress());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.displayToast(activity, propertyData.getPropertyTitle(), Toast.LENGTH_SHORT);
                }
            });
        }
    }

}