package com.android.tmsoneprototype.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.tmsoneprototype.db.model.PropertyList;
import com.android.tmsoneprototype.db.repo.PropertyRepo;
import com.android.tmsoneprototype.ui.property.PropertyFragment;
import com.android.tmsoneprototype.util.Utils;

import java.util.List;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if(Utils.haveNetworkConnection(context)){
            PropertyRepo repo = new PropertyRepo();
            List<PropertyList> propertyLists = repo.getPending();
            syncStatusProperty(propertyLists);
        }else{
            Utils.displayToast(context, "not connected", Toast.LENGTH_LONG);
        }
    }

    private void syncStatusProperty(List<PropertyList> data) {
        if(data != null && !data.isEmpty()){
            for (int i = 0; i < data.size(); i++) {
                PropertyFragment propertyFragment = new PropertyFragment();
                propertyFragment.syncStatus(data.get(i).getTitle());

                System.out.println("===============================");
                System.out.println("Id : " + data.get(i).getId());
                System.out.println("Owner : " + data.get(i).getOwner());
                System.out.println("Title : " + data.get(i).getTitle());
                System.out.println("Address : " + data.get(i).getAddress());
                System.out.println("Price : " + data.get(i).getPrice());
                System.out.println("Image : " + data.get(i).getImg());
                System.out.println("Create Date : " + data.get(i).getCreateDate());
                System.out.println("===============================");
            }
        }
    }

}