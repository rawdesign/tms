package com.android.tmsoneprototype.ui.property.add;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tmsoneprototype.R;
import com.android.tmsoneprototype.util.Utils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PropertyAddActivity extends AppCompatActivity {

    private PropertyAddActivity propertyAddActivity = this;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_owner)
    TextView tvOwner;
    @Bind(R.id.framelayout)
    FrameLayout frameLayout;
    @Bind(R.id.imageview)
    ImageView imageView;
    @Bind(R.id.input_text_title)
    TextInputLayout errorTitle;
    @Bind(R.id.input_title)
    EditText inputTitle;
    @Bind(R.id.input_text_address)
    TextInputLayout errorAddress;
    @Bind(R.id.input_address)
    EditText inputAddress;
    @Bind(R.id.input_text_price)
    TextInputLayout errorPrice;
    @Bind(R.id.input_price)
    EditText inputPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_add);
        ButterKnife.bind(propertyAddActivity);
        clearInput();
        initToolbar();

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.displayToast(propertyAddActivity, "Upload", Toast.LENGTH_SHORT);
            }
        });
    }

    private void clearInput() {
        tvOwner.setText("Budi");
        Picasso.with(propertyAddActivity).load(R.drawable.placeholder_upload)
                .error(R.drawable.placeholder_upload)
                .placeholder(R.drawable.placeholder_upload)
                .into(imageView);
        inputTitle.setText(null);
        inputAddress.setText(null);
        inputPrice.setText(null);
    }

    private void initToolbar() {
        if(toolbar != null){
            setSupportActionBar(toolbar); //set toolbar in support action bar
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true); //optional
            getSupportActionBar().setTitle("Tambah Properti");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    propertyAddActivity.finish();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_property_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_save) {
            Utils.displayToast(propertyAddActivity, "Submit", Toast.LENGTH_SHORT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}