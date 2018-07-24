package edu.lawrence.cafeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;



public class Activity2 extends AppCompatActivity {

    private String eventID;
    private int selected_handle;
    private JSONArray handles = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        eventID = intent.getStringExtra(Activity1.eventID);
        Log.d("webservicedata", "Order id is " + eventID);

        new getMenuTask().execute();

    }

    public void deleteorder(View view) {
        new DeleteTask(eventID).execute();
        new getMenuTask().execute();
    }

    public void submitorder(View view) {

        EditText userText = (EditText) findViewById(R.id.name);
        String name = userText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.phonenumber);
        String phonenumber = passwordText.getText().toString();
        new CustomerInfoTask(name, phonenumber).execute();
        Intent intent = new Intent(this, Activity1.class);
        startActivity(intent);
    }

    private class DeleteTask extends AsyncTask<String, Void, Void> {
            private String uri;

            DeleteTask(String id) {
                try{
                    JSONObject h= handles.getJSONObject(selected_handle);
                    int deleteID = h.getInt("iditem");
                    uri = "http://" + URIHandler.hostName + "/ProjectCafe/api/item/" + deleteID;//item is deleted where iditem = deleteID
                }
                catch(JSONException ex){

                }

            }

            @Override
            protected Void doInBackground(String... urls) {
                try {
                    URIHandler.doDelete(uri);
                } catch (IOException e) {
                }
                return null;
            }

            // onPostExecute displays the results of the AsyncTask.
            @Override
            protected void onPostExecute(Void result) {

            }
        }

    private class CustomerInfoTask extends AsyncTask<String, Void, String> {
            private String uri;
            private String name1;
            private String phonenumber1;
            CustomerInfoTask(String name,String phonenumber) {
                uri="http://"+URIHandler.hostName+"/ProjectCafe/api/invoice/"+ eventID;
                name1= name;
                phonenumber1= phonenumber;
            }

            @Override
            protected String doInBackground(String... urls) {

                try {
                    return URIHandler.doPut(uri,"{\"idorder\":"+ eventID + ",\"customer\":\""+ name1 +"\",\"phone\":\"" + phonenumber1 +"\"}");//look at later
                } catch (IOException e) {
                    return "";
                }

            }
            // onPostExecute displays the results of the AsyncTask.
            @Override
            protected void onPostExecute(String result) {

            }
        }

    private class getMenuTask extends AsyncTask<String, Void, String> {
        private String uri;

        getMenuTask() {
            uri = "http://" + URIHandler.hostName + "/ProjectCafe/api/detail?ordernumber=" + eventID;
        }

        @Override
        protected String doInBackground(String... urls) {
            //contains the coding instruction which should be performed in a background thread. This method runs automatically in a separate Thread.

            try {
                return URIHandler.doGet(uri, "");
            } catch (IOException e) {
                return "";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Handles(result);
        }
    }




    private void Handles(String json) {
        handles = null;
        String[] selectedmenulist = null;

        ListView handlesList = (ListView) findViewById(R.id.selectedmenulist);

        try {
            handles = new JSONArray(json);

            selectedmenulist = new String[handles.length()];
            for(int n = 0;n < selectedmenulist.length;n++) {
                JSONObject handle = handles.getJSONObject(n);
                selectedmenulist[n] = handle.getString("name") ;
            }
        } catch (JSONException ex) {
            Log.d("webserviceData","Exception in loadHandles: "+ex.getMessage());
            selectedmenulist = new String[0];
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, selectedmenulist);
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