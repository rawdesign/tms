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

public class PropertyAddActivity extends AppCompatActivity implements PropertyAddView {

    private PropertyAddActivity propertyAddActivity = this;
    private PropertyAddPresenter presenter;

    private String owner, image, title, address, price;
    private boolean isUpload;

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
        presenter = new PropertyAddPresenterImp(this, propertyAddActivity);

        initToolbar();
        clearInput();
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.displayToast(propertyAddActivity, "Upload", Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void onValidate(boolean valid) {
        if (!valid) {
            return;
        } else {
            presenter.submit(owner, image, title, address, price);
        }
    }

    @Override
    public void onPreProcess() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onInternetFailed() {

    }

    @Override
    public void onErrorEmptyOwner() {
        Utils.displayToast(propertyAddActivity, "Owner tidak boleh kosong", Toast.LENGTH_SHORT);
    }

    @Override
    public void onErrorEmptyImage() {
        Utils.displayToast(propertyAddActivity, "Image tidak boleh kosong", Toast.LENGTH_SHORT);
    }

    @Override
    public void onErrorEmptyTitle() {
        inputTitle.requestFocus();
        errorTitle.setError("Title tidak boleh kosong");
    }

    @Override
    public void onErrorEmptyAddress() {
        inputAddress.requestFocus();
        errorAddress.setError("Address tidak boleh kosong");
    }

    @Override
    public void onErrorEmptyPrice() {
        inputPrice.requestFocus();
        errorPrice.setError("Price tidak boleh kosong");
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

    private void clearInput() {
        owner = "Budi";
        image = "upload/property/image.jpg";
        isUpload = true;

        tvOwner.setText(owner);
        Picasso.with(propertyAddActivity).load(R.drawable.placeholder_upload)
                .error(R.drawable.placeholder_upload)
                .placeholder(R.drawable.placeholder_upload)
                .into(imageView);
        inputTitle.setText(null);
        inputAddress.setText(null);
        inputPrice.setText(null);
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
            owner = Utils.textInput(tvOwner);
            title = Utils.textInput(inputTitle);
            address = Utils.textInput(inputAddress);
            price = Utils.textInput(inputPrice);
            presenter.validate(owner, isUpload, title, address, price);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}