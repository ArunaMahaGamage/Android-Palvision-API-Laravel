package com.palvision.androidpalvisionapilaravel.sale;

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
import com.palvision.androidpalvisionapilaravel.vender.VendorInsertActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SaleInsertActivity extends AppCompatActivity {
    JSONObject json;
    EditText mId, mProduct, mDate, mSumTotal, mCustomerId, mShopId, mVendorId;
    String mSId, mSProduct, mSDate, mSSumTotal, mSCustomerId, mSShopId, mSVendorId;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_insert);
    }
    private void initObjects() {
        mId = (EditText) findViewById(R.id.et_sale_insert_id);
        mProduct = (EditText) findViewById(R.id.et_sale_insert_product);
        mDate =(EditText) findViewById(R.id.et_sale_insert_date);
        mSumTotal =(EditText) findViewById(R.id.et_sale_insert_sum_total);
        mCustomerId =(EditText) findViewById(R.id.et_sale_insert_customer_id);
        mShopId =(EditText) findViewById(R.id.et_sale_insert_shop_id);
        mVendorId =(EditText) findViewById(R.id.et_sale_insert_vender_id);


        submit = (Button) findViewById(R.id.btn_sale_insert);

        json = new JSONObject();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSId =  mId.getText().toString();
                mSProduct = mProduct.getText().toString();
                mSDate = mDate.getText().toString();
                mSSumTotal = mSumTotal.getText().toString();
                mSCustomerId = mCustomerId.getText().toString();
                mSShopId = mShopId.getText().toString();
                mSVendorId = mVendorId.getText().toString();

                createJSON();
            }
        });
    }

    private void createJSON() {

        try {
            json.put("id",mSId);
            json.put("products",mSProduct);
            json.put("date",mSDate);
            json.put("sum_total",mSSumTotal);
            json.put("customer_id",mSCustomerId);
            json.put("shop_id",mSShopId);
            json.put("vendor_id",mSVendorId);

            callBackend();

            System.out.println(json);

            Toast.makeText(SaleInsertActivity.this, "json ...  " +json ,
                    Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void callBackend() {
        String url = "http://192.168.1.101/palvision/sale/insert/" +json.toString();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(SaleInsertActivity.this, "Data sent to Server " + response,
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
