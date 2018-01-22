package com.palvision.androidpalvisionapilaravel.customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.palvision.androidpalvisionapilaravel.MySingleton;
import com.palvision.androidpalvisionapilaravel.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

import static com.palvision.androidpalvisionapilaravel.urls.URL.BASE_URL;
import static com.palvision.androidpalvisionapilaravel.urls.URL.PORT;

public class CustomerInsertActivity extends AppCompatActivity {

    EditText mId, mName, mAddress, mPhone;
    Button submit;
    String mIID, mIName, mIAddress, mIPhone;
    JSONObject json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_insert);

        initObjects();
    }
    private void initObjects() {
        mId = (EditText) findViewById(R.id.et_customer_insert_id);
        mName = (EditText) findViewById(R.id.et_customer_insert_name);
        mAddress =(EditText) findViewById(R.id.et_customer_insert_address);
        mPhone = (EditText) findViewById(R.id.et_customer_insert_phone);
        submit = (Button) findViewById(R.id.btn_customer_insert);

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

/*            Toast.makeText(CustomerInsertActivity.this, "json ...  " +json ,
                    Toast.LENGTH_LONG).show();*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void callBackend() {
        String url = BASE_URL +":"+ PORT + "/palvision/customer/insert/";

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("id", mIID);
        params.put("name", mIName);
        params.put("address", mIAddress);
        params.put("phone", mIPhone);
        client.post(getApplicationContext(), url,
                params, new JsonHttpResponseHandler() {

                   @Override
                   public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                       super.onSuccess(statusCode, headers, response);

                       Toast.makeText(CustomerInsertActivity.this, " " + response,
                               Toast.LENGTH_LONG).show();
                   }

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);

                        Toast.makeText(CustomerInsertActivity.this, "Error",
                                Toast.LENGTH_LONG).show();
                    }
                });

    }
}
