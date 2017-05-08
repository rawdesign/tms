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
        TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
        return new PropertyViewHolder(parent, itemTextView);
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }

}