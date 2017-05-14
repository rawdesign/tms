package com.android.tmsoneprototype.ui.property;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.tmsoneprototype.R;
import com.android.tmsoneprototype.db.model.PropertyAdd;
import com.android.tmsoneprototype.db.model.PropertyList;
import com.android.tmsoneprototype.util.Utils;
import com.android.tmsoneprototype.widget.ScrollLinearLayoutManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PropertyFragment extends Fragment implements PropertyView {

    private PropertyPresenter presenter;
    public static List<PropertyList> mDatas;
    public static PropertyAdapter mAdapter;
    public static RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private ProgressDialog progress;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

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
        View rootView = inflater.inflate(R.layout.fragment_property, container, false);
        ButterKnife.bind(this, rootView);
        mRecyclerView = recyclerView;
        presenter = new PropertyPresenterImp(this, getActivity());
        progress = new ProgressDialog(getActivity());
        getAll();

        return rootView;
    }

    @Override
    public void onPreProcess() {
        mRecyclerView.setVisibility(View.GONE);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new ScrollLinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager); // use a linear layout manager
    }

    @Override
    public void onSuccess(List<PropertyList> data) {
        progress.dismiss();
        mDatas = data;
        mAdapter = new PropertyAdapter(getActivity(), mDatas); // create an Object for Adapter;
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setAdapter(mAdapter); // set the adapter object to the Recyclerview
    }

    @Override
    public void onFailed() {
        progress.dismiss();
        Utils.displayToast(getActivity(), "no data", Toast.LENGTH_SHORT);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void getAll(){
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(true);
        progress.setMessage("loading");
        progress.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.loadData();
            }
        }, 2000);
    }

    public void addItem(PropertyAdd propertyAdd) {
        int position = 0;
        PropertyList list = new PropertyList();
        list.setId(propertyAdd.getId());
        list.setOwner(propertyAdd.getOwner());
        list.setTitle(propertyAdd.getTitle());
        list.setAddress(propertyAdd.getAddress());
        list.setPrice(propertyAdd.getPrice());
        list.setImg(propertyAdd.getImg());
        list.setImgThmb(propertyAdd.getImgThmb());
        list.setStatus(propertyAdd.getStatus());
        list.setCreateDate(propertyAdd.getCreateDate());

        mDatas.add(position, list);
        mAdapter.notifyItemInserted(position);
        mRecyclerView.scrollToPosition(position);
    }

}