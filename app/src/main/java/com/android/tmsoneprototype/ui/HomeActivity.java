package com.android.tmsoneprototype.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.tmsoneprototype.R;
import com.android.tmsoneprototype.ui.menu.MenuFragment;
import com.android.tmsoneprototype.ui.owner.OwnerFragment;
import com.android.tmsoneprototype.ui.property.PropertyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private HomeActivity homeActivity = this;
    private int[] tabIcons = {
            R.drawable.ic_person_white_24dp,
            R.drawable.ic_home_white_24dp,
            R.drawable.ic_menu_white_24dp
    };

    @Bind(R.id.toolbar)
    Toolbar _toolbar;
    @Bind(R.id.viewpager)
    ViewPager _viewPager;
    @Bind(R.id.tabs)
    TabLayout _tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(homeActivity);

        setupToolbar();
        setupViewPager(_viewPager);
        setupTabLayout();
    }

    private void setupToolbar() {
        if(_toolbar != null){
            setSupportActionBar(_toolbar); //set toolbar in support action bar 
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true); //optional
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OwnerFragment(), "Owner");
        adapter.addFragment(new PropertyFragment(), "Properti");
        adapter.addFragment(new MenuFragment(), "Menu");
        viewPager.setAdapter(adapter);
    }

    private void setupTabLayout() {
        _tabLayout.setupWithViewPager(_viewPager);
        _tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        _tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        _tabLayout.getTabAt(2).setIcon(tabIcons[2]);
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