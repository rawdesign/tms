package com.android.tmsoneprototype.ui;

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
import com.android.tmsoneprototype.ui.menu.MenuFragment;
import com.android.tmsoneprototype.ui.owner.OwnerFragment;
import com.android.tmsoneprototype.ui.property.PropertyFragment;
import com.android.tmsoneprototype.util.Utils;

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
    Toolbar toolbar;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.fabOwner)
    FloatingActionButton fabOwner;
    @Bind(R.id.fabProperty)
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
                Utils.displayToast(homeActivity, "Property Clicked", Toast.LENGTH_SHORT);
            }
        });
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