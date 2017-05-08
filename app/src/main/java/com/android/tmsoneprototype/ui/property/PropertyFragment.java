package com.android.tmsoneprototype.ui.property;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tmsoneprototype.R;

import java.util.ArrayList;
import java.util.List;

public class PropertyFragment extends Fragment {

    private Context context = getActivity();

    public PropertyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_property, container, false);

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_property, container, false);
        setupRecyclerView(recyclerView);
        return recyclerView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        PropertyAdapter recyclerAdapter = new PropertyAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        int itemsCount = 21;
        for (int i = 0; i < itemsCount; i++) {
            itemList.add("Item " + i);
        }
        return itemList;
    }

}