package com.android.tmsoneprototype.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.tmsoneprototype.R;
import com.android.tmsoneprototype.api.PropertyAPI;
import com.android.tmsoneprototype.api.data.PropertyAddData;
import com.android.tmsoneprototype.api.response.PropertyAddResponse;
import com.android.tmsoneprototype.app.TMSOnePrototypeApp;
import com.android.tmsoneprototype.db.model.PropertyList;
import com.android.tmsoneprototype.db.repo.PropertyRepo;
import com.android.tmsoneprototype.service.Retrofit;
import com.android.tmsoneprototype.ui.menu.MenuFragment;
import com.android.tmsoneprototype.ui.owner.OwnerFragment;
import com.android.tmsoneprototype.ui.property.PropertyFragment;
import com.android.tmsoneprototype.ui.property.add.PropertyAddActivity;
import com.android.tmsoneprototype.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private HomeActivity homeActivity = this;
    private int[] tabIcons = {
            R.drawable.ic_person_white_24dp,
            R.drawable.ic_home_white_24dp,
            R.drawable.ic_menu_white_24dp
    };

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.fab_owner)
    FloatingActionButton fabOwner;
    @Bind(R.id.fab_property)
    FloatingActionButton fabProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(homeActivity);

        initToolbar();
        initViewPager(viewPager);
        initTabLayout();

        fabOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.displayToast(homeActivity, "Owner Clicked", Toast.LENGTH_SHORT);
            }
        });

        fabProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.intent(homeActivity, PropertyAddActivity.class);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        TMSOnePrototypeApp.activityResumed(); // On Pause notify the Application
    }

    @Override
    protected void onResume() {
        super.onResume();
        TMSOnePrototypeApp.activityResumed(); // On Resume notify the Application
    }

    private void initToolbar() {
        if(toolbar != null){
            setSupportActionBar(toolbar); //set toolbar in support action bar 
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true); //optional
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
    }

    private void initViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OwnerFragment(), "Owner");
        adapter.addFragment(new PropertyFragment(), "Properti");
        adapter.addFragment(new MenuFragment(), "Menu");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                animateFab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                animateFab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void animateFab(int position) {
        switch (position) {
            case 0:
                fabOwner.show();
                fabProperty.hide();
                break;
            case 1:
                fabOwner.hide();
                fabProperty.show();
                break;
            case 2:
                fabOwner.hide();
                fabProperty.hide();
                break;
            default:
                fabOwner.show();
                fabProperty.hide();
                break;
        }
    }

    public void checkNetwork(Context context, boolean isConnected) {
        if(isConnected){
            Utils.displayToast(context, "on", Toast.LENGTH_SHORT);
            PropertyRepo repo = new PropertyRepo();
            List<PropertyList> propertyLists = repo.getPending();
            if(propertyLists != null && !propertyLists.isEmpty()){
                for (int i = 0; i < propertyLists.size(); i++) {
                    System.out.println("===============================");
                    System.out.println("Id : " + propertyLists.get(i).getId());
                    System.out.println("Owner : " + propertyLists.get(i).getOwner());
                    System.out.println("Title : " + propertyLists.get(i).getTitle());
                    System.out.println("Address : " + propertyLists.get(i).getAddress());
                    System.out.println("Price : " + propertyLists.get(i).getPrice());
                    System.out.println("Image : " + propertyLists.get(i).getImg());
                    System.out.println("Create Date : " + propertyLists.get(i).getCreateDate());
                    System.out.println("===============================");

                    if(Utils.haveNetworkConnection(context)){
                        Utils.displayToast(context, "Resync..", Toast.LENGTH_SHORT);
                        //Create Upload Server Client
                        PropertyAPI service = Retrofit.setup().create(PropertyAPI.class);

                        //File creating from selected URL
                        File file = new File(propertyLists.get(i).getImg());

                        //Create RequestBody instance from file
                        RequestBody requestOwner = RequestBody.create(MediaType.parse("text/plain"), propertyLists.get(i).getOwner());
                        RequestBody requestTitle = RequestBody.create(MediaType.parse("text/plain"), propertyLists.get(i).getTitle());
                        RequestBody requestAddress = RequestBody.create(MediaType.parse("text/plain"), propertyLists.get(i).getAddress());
                        RequestBody requestPrice = RequestBody.create(MediaType.parse("text/plain"), propertyLists.get(i).getPrice());
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                        //MultipartBody.Part is used to send also the actual file name
                        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

                        Call<PropertyAddResponse> call = service.insert(requestOwner, requestTitle, requestAddress, requestPrice, body);
                        call.enqueue(new Callback<PropertyAddResponse>() {
                            @Override
                            public void onResponse(Call<PropertyAddResponse> call, Response<PropertyAddResponse> response) {
                                String status = response.body().getStatus();
                                switch (status) {
                                    case "200":
                                        int result;
                                        List<PropertyAddData> propertyAddDatas = response.body().getData();
                                        PropertyRepo repo = new PropertyRepo();
                                        result = repo.updateStatus(propertyAddDatas.get(0).getPropertyTitle(), "success");

                                        if(result >= 1){
                                            PropertyFragment propertyFragment = new PropertyFragment();
                                            propertyFragment.syncStatus(propertyAddDatas.get(0).getPropertyTitle());
                                        }else{
                                            //failed
                                        }
                                        break;
                                    default:
                                        //failed
                                        break;
                                }
                            }

                            @Override
                            public void onFailure(Call<PropertyAddResponse> call, Throwable t) {
                                // Log error here since request failed
                            }
                        });
                    }
                }
            }
        }else{
            Utils.displayToast(context, "off", Toast.LENGTH_SHORT);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
            //return null; //return null to display only the icon
        }
    }

}