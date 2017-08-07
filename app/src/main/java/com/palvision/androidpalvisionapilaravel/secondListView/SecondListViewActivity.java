package com.palvision.androidpalvisionapilaravel.secondListView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.palvision.androidpalvisionapilaravel.DataSave.DataSave;
import com.palvision.androidpalvisionapilaravel.MainActivity;
import com.palvision.androidpalvisionapilaravel.R;
import com.palvision.androidpalvisionapilaravel.customer.CustomerDeleteActivity;
import com.palvision.androidpalvisionapilaravel.customer.CustomerInsertActivity;
import com.palvision.androidpalvisionapilaravel.customer.CustomerRetriveActivity;
import com.palvision.androidpalvisionapilaravel.customer.CustomerUpdateActivity;
import com.palvision.androidpalvisionapilaravel.product.ProductDeleteActivity;
import com.palvision.androidpalvisionapilaravel.product.ProductInsertActivity;
import com.palvision.androidpalvisionapilaravel.product.ProductRetriveActivity;
import com.palvision.androidpalvisionapilaravel.product.ProductUpdateActivity;

import java.util.ArrayList;

public class SecondListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_list_view);

        populateUsersList();
    }
    private void populateUsersList() {
        // Construct the data source
        ArrayList<CRUD> arrayOfUsers = CRUD.getUsers();
        // Create the adapter to convert the array to views
        SecoundListviewAdapter adapter = new SecoundListviewAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv_CustomerActivity_Customer);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener());

    }
    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    Integer mainListPosition = (Integer) DataSave.saveData.get(0);



                    for (int i = 0; i < initArray().length; i++) {
                        for (int j = 0; j < initArray().length; j++) {
                            if ((i == mainListPosition) && (j == position)) {
                                Toast.makeText(SecondListViewActivity.this, mainListPosition+" Wait ... " + position,
                                        Toast.LENGTH_LONG).show();
                                Intent [][] intents = initArray();
                                Intent mExplore = intents[i][j];
                                startActivity(mExplore);
                            }

                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(SecondListViewActivity.this, "Exception : " + e.getStackTrace(),
                            Toast.LENGTH_SHORT).show();}
            }
        };
    }
    private Intent[][] initArray() {

        Intent [][] intents = new Intent[6][4];

        intents [0][0] = new Intent(SecondListViewActivity.this, CustomerInsertActivity.class);
        intents [0][1] = new Intent(SecondListViewActivity.this, CustomerUpdateActivity.class);
        intents [0][2] = new Intent(SecondListViewActivity.this, CustomerRetriveActivity.class);
        intents [0][3] = new Intent(SecondListViewActivity.this, CustomerDeleteActivity.class);

        intents [1][0] = new Intent(SecondListViewActivity.this, ProductInsertActivity.class);
        intents [1][1] = new Intent(SecondListViewActivity.this, ProductUpdateActivity.class);
        intents [1][2] = new Intent(SecondListViewActivity.this, ProductRetriveActivity.class);
        intents [1][3] = new Intent(SecondListViewActivity.this, ProductDeleteActivity.class);

        intents [2][0] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [2][1] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [2][2] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [2][3] = new Intent(SecondListViewActivity.this, MainActivity.class);

        intents [3][0] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [3][1] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [3][2] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [3][3] = new Intent(SecondListViewActivity.this, MainActivity.class);

        intents [4][0] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [4][1] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [4][2] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [4][3] = new Intent(SecondListViewActivity.this, MainActivity.class);

        intents [5][0] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [5][1] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [5][2] = new Intent(SecondListViewActivity.this, MainActivity.class);
        intents [5][3] = new Intent(SecondListViewActivity.this, MainActivity.class);

        return intents;
    }
}
