package com.palvision.androidpalvisionapilaravel.vender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.palvision.androidpalvisionapilaravel.MySingleton;
import com.palvision.androidpalvisionapilaravel.R;

import org.json.JSONException;
import org.json.JSONObject;

public class VendorDeleteActivity extends AppCompatActivity {
    JSONObject json;
    EditText mId, mName, mStaffNumber;
    String mVId, mVName, mVStaffNumber;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_delete);

        initObjects();
    }
    private void initObjects() {
        mId = (EditText) findViewById(R.id.et_vendor_delete_id);
        /*
        mName = (EditText) findViewById(R.id.et_vendor_insert_name);
        mStaffNumber =(EditText) findViewById(R.id.et_vendor_insert_staffnumber);
        */

        submit = (Button) findViewById(R.id.btn_vendor_delete);

        json = new JSONObject();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVId =  mId.getText().toString();
                /*
                mVName = mName.getText().toString();
                mVStaffNumber = mStaffNumber.getText().toString();
                */

                createJSON();
            }
        });
    }

    private void createJSON() {

        try {
            json.put("id",mVId);
            /*
            json.put("name",mVName);
            json.put("address",mVStaffNumber);
            */

            callBackend();

            System.out.println(json);

            Toast.makeText(VendorDeleteActivity.this, "json ...  " +json ,
                    Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void callBackend() {
        String url = "http://192.168.1.101/palvision/vendor/delete/" +json.toString();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(VendorDeleteActivity.this, "Data sent to Server " + response,
                                Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println(error);
                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}
