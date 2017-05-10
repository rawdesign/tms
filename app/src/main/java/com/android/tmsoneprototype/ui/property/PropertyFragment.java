package com.android.tmsoneprototype.ui.property;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.tmsoneprototype.R;
import com.android.tmsoneprototype.api.data.PropertyData;
import com.android.tmsoneprototype.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class PropertyFragment extends Fragment implements PropertyView {

    private PropertyPresenter presenter;

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
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_property, container, false);
        setupRecyclerView(recyclerView);

        presenter = new PropertyPresenterImp(this, getActivity());
        presenter.loadData();

        return recyclerView;
    }

    @Override
    public void onPreProcess() {
        Utils.displayToast(getActivity(), "load", Toast.LENGTH_SHORT);
    }

    @Override
    public void onSuccess(List<PropertyData> data) {
        System.out.println("Count Data : " + data.size());
    }

    @Override
    public void onFailed() {
        Utils.displayToast(getActivity(), "failed", Toast.LENGTH_SHORT);
    }

    @Override
    public void onInternetFailed() {
        Utils.displayToast(getActivity(), "no internet access", Toast.LENGTH_SHORT);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PropertyAdapter recyclerAdapter = new PropertyAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        int itemsCount = 6;
        for (int i = 0; i < itemsCount; i++) {
            itemList.add("Item " + i);
        }
        return itemList;
    }

}