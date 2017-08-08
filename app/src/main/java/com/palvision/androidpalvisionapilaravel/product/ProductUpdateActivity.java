package com.palvision.androidpalvisionapilaravel.product;

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

public class ProductUpdateActivity extends AppCompatActivity {
    EditText mId, mPrice, mType, mManufacturer, mCustomerId;
    Button submit;
    String mPId, mPPrice, mPType, mPManufacturer, mPCustomerId;

    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);

        initObjects();
    }
    private void initObjects() {
        mId = (EditText) findViewById(R.id.et_product_update_id);
        mPrice = (EditText) findViewById(R.id.et_product_update_price);
        mType =(EditText) findViewById(R.id.et_product_update_type);
        mManufacturer = (EditText) findViewById(R.id.et_product_update_manufacturer);
        mCustomerId = (EditText) findViewById(R.id.et_product_update_customer_id);

        submit = (Button) findViewById(R.id.btn_product_update);

        json = new JSONObject();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPId =  mId.getText().toString();
                mPPrice = mPrice.getText().toString();
                mPType = mType.getText().toString();
                mPManufacturer = mManufacturer.getText().toString();
                mPCustomerId = mCustomerId.getText().toString();

                createJSON();
            }
        });
    }

    private void createJSON() {

        try {
            json.put("id",mPId);
            json.put("price",mPPrice);
            json.put("type",mPType);
            json.put("manufacturer",mPManufacturer);
            json.put("customer_id",mPCustomerId);

            callBackend();

            System.out.println(json);

            Toast.makeText(ProductUpdateActivity.this, "json ...  " +json ,
                    Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void callBackend() {
        String url = "http://192.168.1.101/palvision/product/update/" +json.toString();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ProductUpdateActivity.this, "Data sent to Server " + response,
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
