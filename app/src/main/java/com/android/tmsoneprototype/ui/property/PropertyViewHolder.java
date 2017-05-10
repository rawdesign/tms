package com.android.tmsoneprototype.ui.property;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.tmsoneprototype.R;

public class PropertyViewHolder extends RecyclerView.ViewHolder {

    private final TextView mItemTextView;

    public PropertyViewHolder(final View parent, TextView itemTextView) {
        super(parent);
        mItemTextView = itemTextView;
    }

    public static PropertyViewHolder newInstance(View parent) {
        TextView tvTitle = (TextView) parent.findViewById(R.id.tv_title);
        return new PropertyViewHolder(parent, tvTitle);
    }

    public void setItemText(CharSequence text) {
        //mItemTextView.setText(text);
    }

}