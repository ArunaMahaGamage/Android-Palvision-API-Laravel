package com.palvision.androidpalvisionapilaravel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.palvision.androidpalvisionapilaravel.DataSave.DataSave;
import com.palvision.androidpalvisionapilaravel.main.CustomMainActivityAdapter;
import com.palvision.androidpalvisionapilaravel.main.Main;
import com.palvision.androidpalvisionapilaravel.secondListView.SecondListViewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateUsersList();
    }
    private void populateUsersList() {
        // Construct the data source
        ArrayList<Main> arrayOfUsers = Main.getUsers();
        // Create the adapter to convert the array to views
        CustomMainActivityAdapter adapter = new CustomMainActivityAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvUsers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener());
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    DataSave.saveData.put(0,position);

                    Intent mainCustomActivity = new Intent(MainActivity.this, SecondListViewActivity.class);
                    startActivity(mainCustomActivity);



                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Exception : " + e.getStackTrace(),
                            Toast.LENGTH_SHORT).show();}


                Toast.makeText(MainActivity.this, "Wait ... " + position,
                        Toast.LENGTH_LONG).show();
            }
        };
    }
}
