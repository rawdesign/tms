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
import com.android.tmsoneprototype.api.data.PropertyAddData;
import com.android.tmsoneprototype.db.model.PropertyAdd;
import com.android.tmsoneprototype.db.model.PropertyList;
import com.android.tmsoneprototype.db.repo.PropertyRepo;
import com.android.tmsoneprototype.util.Utils;
import com.android.tmsoneprototype.widget.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PropertyFragment extends Fragment implements PropertyView {

    public static PropertyPresenter mPresenter;
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
        mPresenter = new PropertyPresenterImp(this, getActivity());
        progress = new ProgressDialog(getActivity());
        getAll();

        return rootView;
    }

    @Override
    public void onPreProcess() {
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
        mRecyclerView.setAdapter(mAdapter); // set the adapter object to the Recyclerview
    }

    @Override
    public void onAddSuccess(List<PropertyAddData> data) {
        int result;
        PropertyRepo repo = new PropertyRepo();
        result = repo.updateStatus(data.get(0).getPropertyTitle(), "success");

        if(result >= 1){
            syncStatus(data.get(0).getPropertyTitle());
        }else{
            Utils.displayToast(getActivity(), "update failed", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onFailed() {
        progress.dismiss();
        mDatas = new ArrayList<PropertyList>();
        mAdapter = new PropertyAdapter(getActivity(), mDatas); // create an Object for Adapter;
        mRecyclerView.setAdapter(mAdapter); // set the adapter object to the Recyclerview
        Utils.displayToast(getActivity(), "no data", Toast.LENGTH_SHORT);
    }

    @Override
    public void onErrorSizeImage() {
        Utils.displayToast(getActivity(), "Image Size melebihi 10 MB", Toast.LENGTH_SHORT);
    }

    @Override
    public void onErrorExtensionImage() {
        Utils.displayToast(getActivity(), "Format image tidak valid", Toast.LENGTH_SHORT);
    }

    private void getAll() {
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(true);
        progress.setMessage("loading");
        progress.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.loadData();
            }
        }, 2000);
    }

    private int getItemPosition(String title) {
        for (int position = 0; position < mDatas.size(); position++)
            if (mDatas.get(position).getTitle().equals(title))
                return position;
        return 0;
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
        mPresenter.insert(propertyAdd);
    }

    public void syncStatus(String title) {
        PropertyList propertyList = mDatas.get(getItemPosition(title));
        propertyList.setStatus("success");
        mAdapter.notifyDataSetChanged();
    }

}