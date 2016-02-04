package com.example.tacademy.samplemelon1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<Song> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = (ListView)findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<Song>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        Button btn = (Button)findViewById(R.id.btn_json_melon);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyMelonJsonTask().execute();
            }
        });

    }


    String urlFormat = "http://apis.skplanetx.com/melon/charts/realtime?count=%s&page=%s&version=1";

    class MyMelonJsonTask extends AsyncTask<String, Integer, Melon>{

        @Override
        protected Melon doInBackground(String... params) {
            String urlText = String.format(urlFormat, 10, 1); // 위에 %s 에 넣기

            try {
                URL url = new URL(urlText);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("appKey", "743e78d8-09cc-399f-b87e-ae7d25295868");
                int code  = conn.getResponseCode();
                if(code >= HttpURLConnection.HTTP_OK && code < HttpURLConnection.HTTP_MULT_CHOICE){
                    InputStream is = conn.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while ((line = br.readLine())!= null){
                        sb.append(line).append("\n\r");

                    }
                    String text = sb.toString(); //전체 읽어들인 값

                    try {
                        JSONObject jsonObject = new JSONObject(text);
                        MelonData data = new MelonData();
                        data.setData(jsonObject);
                        return data.melon;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Melon melon) {
            super.onPostExecute(melon);
            if(melon!=null){
                for (Song s : melon.songs.song){
                    mAdapter.add(s);
                }
            }else {
                Toast.makeText(MainActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
