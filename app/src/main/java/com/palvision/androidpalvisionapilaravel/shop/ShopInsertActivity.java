package com.palvision.androidpalvisionapilaravel.shop;

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
import com.palvision.androidpalvisionapilaravel.product.ProductInsertActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ShopInsertActivity extends AppCompatActivity {
    JSONObject json;
    EditText mId, mName, mAddress;
    Button submit;
    String mSID, mSName, mSAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_insert);

        initObjects();
    }
    private void initObjects() {
        mId = (EditText) findViewById(R.id.et_shop_insert_id);
        mName = (EditText) findViewById(R.id.et_shop_insert_name);
        mAddress =(EditText) findViewById(R.id.et_shop_insert_address);

        submit = (Button) findViewById(R.id.btn_shop_insert);

        json = new JSONObject();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSID =  mId.getText().toString();
                mSName = mName.getText().toString();
                mSAddress = mAddress.getText().toString();

                createJSON();
            }
        });
    }

    private void createJSON() {

        try {
            json.put("id",mSID);
            json.put("name",mSName);
            json.put("address",mSAddress);

            callBackend();

            System.out.println(json);

            Toast.makeText(ShopInsertActivity.this, "json ...  " +json ,
                    Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void callBackend() {
        String url = "http://192.168.1.101/palvision/shop/insert/" +json.toString();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ShopInsertActivity.this, "Data sent to Server " + response,
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
