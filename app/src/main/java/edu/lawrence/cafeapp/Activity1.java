package edu.lawrence.cafeapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;


public class Activity1 extends AppCompatActivity {
    //this is where you initialize your member variables
    public final static String eventID = "edu.lawrence.cafeapp.orderID";
    private String eventnumber="0";
    private String idproduct;
    private JSONArray handles = null;
    private int selected_handle = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this is where you initialize your activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        new getMenuTask().execute();
    }

    public void viewOrder(View view) {
        Intent intent = new Intent(this, Activity2.class);
        Log.d("webservicedata","eventnumber is"+eventnumber);
        intent.putExtra(eventID, eventnumber);
        startActivity(intent);
    }

    public void addToOrder(View view){

        if(selected_handle!=-1){
        if(eventnumber.equals("0")) {
            new newCustomerTask().execute();
            try {
                idproduct = handles.getJSONObject(selected_handle).getString("idproduct");
                /*ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new PostingItemDataTask().execute();

                }*/
            } catch (JSONException ex) {
                Log.d("CafeApp", "Exception in readMessage: " + ex.getMessage());
            }
        }
            else{
            try {
                idproduct = handles.getJSONObject(selected_handle).getString("idproduct");
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new PostingItemDataTask().execute();

                }
            } catch (JSONException ex) {
                Log.d("CafeApp", "Exception in readMessage: " + ex.getMessage());
            }

        }
        }


    }


    private class getMenuTask extends AsyncTask<String, Void, String> {
        private String uri;

        getMenuTask() {
            uri = "http://" + URIHandler.hostName + "/ProjectCafe/api/product" ;
        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                return URIHandler.doGet(uri, "");
            } catch (IOException e) {
                return "";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            handles(result);
        }
    }

    private class newCustomerTask extends AsyncTask<String, Void, String> {
        private String uri;

        newCustomerTask() {
            uri = "http://" + URIHandler.hostName + "/ProjectCafe/api/invoice";
        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                return URIHandler.doPost(uri, "{\"customer\":\"unknown\",\"phone\":\"unknown\"}");
            } catch (IOException e) {
                return "";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            eventnumber=result;
            new PostingItemDataTask().execute();
        }
    }

    private class PostingItemDataTask extends AsyncTask<String, Void, String> {
        private String uri;

        PostingItemDataTask() {
            uri = "http://" + URIHandler.hostName + "/ProjectCafe/api/item" ;
        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                return URIHandler.doPost(uri, "{\"product\":" + idproduct+
                        ",\"ordernumber\":" + eventnumber +
                        "}");
            } catch (IOException e) {
                return "";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Log.d("idproduct:"+ idproduct,"ordernumber:"+ eventnumber);
        }
    }

    private void handles(String json) {
        handles = null;
        String[] menuItem = null;

        ListView handlesList = (ListView) findViewById(R.id.eventlist);

        try {
            handles = new JSONArray(json);
            menuItem = new String[handles.length()];
            for(int n = 0;n < menuItem.length;n++) {
                JSONObject handle = handles.getJSONObject(n);
                menuItem[n] = handle.getString("name") + ":" + handle.getString("cost");
            }
        } catch (JSONException ex) {
            Log.d("webserviceData","Exception in menulist: "+ex.getMessage());
            menuItem = new String[0];
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menuItem);
        handlesList.setAdapter(adapter);

        handlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l) {
                // remember the selection
                selected_handle = i;
            }
        });
    }




}



