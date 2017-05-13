package com.android.tmsoneprototype.ui.property;

import android.app.ProgressDialog;
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
import com.android.tmsoneprototype.ui.property.add.PropertyAddModel;
import com.android.tmsoneprototype.util.Utils;
import com.android.tmsoneprototype.widget.ScrollLinearLayoutManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PropertyFragment extends Fragment implements PropertyView {

    private PropertyPresenter presenter;
    public static List<PropertyData> mDatas;
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
        presenter.loadData();

        return rootView;
    }

    @Override
    public void onPreProcess() {
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(true);
        progress.setMessage("loading");
        progress.show();

        mRecyclerView.setVisibility(View.GONE);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new ScrollLinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager); // use a linear layout manager
    }

    @Override
    public void onSuccess(List<PropertyData> data) {
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

    @Override
    public void onInternetFailed() {
        progress.dismiss();
        Utils.displayToast(getActivity(), "no internet access", Toast.LENGTH_SHORT);
    }

    public void addItem(PropertyAddModel propertyAddModel) {
        int position = 0;
        mDatas.add(position, new PropertyData(
                propertyAddModel.getId(), propertyAddModel.getOwner(), propertyAddModel.getTitle(),
                propertyAddModel.getAddress(), propertyAddModel.getPrice(), propertyAddModel.getImg(),
                propertyAddModel.getImgThmb(), propertyAddModel.getStatus()
        ));
        mAdapter.notifyItemInserted(position);
        mRecyclerView.scrollToPosition(position);
    }

}