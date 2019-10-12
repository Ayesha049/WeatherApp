package com.example.weatherprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class ShowCity extends AppCompatActivity {

    ArrayList<Model> mdata;
    Adapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_city);
        //Toast.makeText(ShowCity.this,"hello",Toast.LENGTH_LONG).show();
        mdata = new ArrayList<Model>();


        madapter = new Adapter(this, mdata);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(madapter);


        load_data();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Model model = mdata.get(position);

                MainActivity.cityname = model.city;
                MainActivity.countrynamename = model.country;

                Intent familyIntent = new Intent(ShowCity.this, MainActivity.class);
                startActivity(familyIntent);
            }
        });

    }

    public void load_data()
    {
        String json;
        try{

            InputStream is = getAssets().open("test.json");
            int size = is.available();
            String sz = Integer.toString(size);
            //Toast.makeText(ShowCity.this,sz,Toast.LENGTH_SHORT).show();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonarray = new JSONArray(json);

            for(int i=0;i<jsonarray.length();i++)
            {
                JSONObject obj = jsonarray.getJSONObject(i);
                String cityy = obj.getString("name");

                String coun = obj.getString("country");

                madapter.add(new Model(cityy,coun));

                //Log.d("Details-->", obj.getString("formule"));
                //Toast.makeText(ShowCity.this,cityy,Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
