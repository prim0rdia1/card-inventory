package com.example.gadau.sqldemo.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadau.sqldemo.R;
import com.example.gadau.sqldemo.data.Contants;
import com.example.gadau.sqldemo.data.DataItem;
import com.example.gadau.sqldemo.data.DatabaseHandler;

public class InfoPage extends AppCompatActivity {
    private static final int WAS_CHANGED = 1;
    private DataItem di;
    private String s;
    private boolean isChanged = false;
    private DatabaseHandler dB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);
        dB = DatabaseHandler.getInstance(this);

        //Set edit button in toolbar
        setUpToolbar();
        setUpButtons();

        //Take apart package for viewing
        Intent i = getIntent();
        s = getIntent().getExtras().getString(Contants.EXTRA_ID);
        fillData();

    }

    private void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(""); toolbar.setSubtitle("");
    }

    private void setUpButtons(){
        ImageView cancelButton = (ImageView)findViewById(R.id.button_cancel2);
        ImageView editButton = (ImageView)findViewById(R.id.button_edit);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditPage();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoPage.this.finish();
            }
        });
    }

    private void fillData(){
        di = dB.getItemByID(s);

        TextView outID = (TextView) findViewById(R.id.output_id);
        TextView outVendor = (TextView) findViewById(R.id.output_vendor);
        TextView outLoc = (TextView) findViewById(R.id.output_location);
        TextView outQty = (TextView) findViewById(R.id.output_qty);

        outID.setText(di.getID());
        outVendor.setText(di.getVendor());
        outLoc.setText(di.getLocation());
        outQty.setText(di.getQty());
    }

    private void launchEditPage(){
        Intent intent = new Intent(this, EditInfo.class);
        intent.putExtra(Contants.EXTRA_ID, s);
        intent.putExtra(Contants.EXTRA_VENDOR, "");
        intent.putExtra(Contants.READY_TO_LOAD, true);
        intent.putExtra(Contants.IS_EXISTING, true);
        startActivityForResult(intent, WAS_CHANGED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WAS_CHANGED){
            if (resultCode == RESULT_OK) {
                fillData();
                isChanged = true;
            } else if (resultCode == Contants.RESULT_DELETED) {
                Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
