package com.palvision.androidpalvisionapilaravel.customer;

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

public class CustomerUpdateActivity extends AppCompatActivity {
    EditText mId, mName, mAddress, mPhone;
    Button submit;
    String mIID, mIName, mIAddress, mIPhone;
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update);

        initObjects();
    }
    private void initObjects() {
        mId = (EditText) findViewById(R.id.et_customer_update_id);
        mName = (EditText) findViewById(R.id.et_customer_update_name);
        mAddress =(EditText) findViewById(R.id.et_customer_update_address);
        mPhone = (EditText) findViewById(R.id.et_customer_update_phone);
        submit = (Button) findViewById(R.id.btn_customer_update);

        json = new JSONObject();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIID =  mId.getText().toString();
                mIName = mName.getText().toString();
                mIAddress = mAddress.getText().toString();
                mIPhone = mPhone.getText().toString();

                createJSON();
            }
        });
    }

    private void createJSON() {

        try {
            json.put("id",mIID);
            json.put("name",mIName);
            json.put("address",mIAddress);
            json.put("phone",mIPhone);

            callBackend();

            System.out.println(json);

            Toast.makeText(CustomerUpdateActivity.this, "json ...  " +json ,
                    Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void callBackend() {
        String url = "http://192.168.1.101:8080/palvision/customer/update/" + json.toString();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(CustomerUpdateActivity.this, "Data sent to Server " + response,
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
