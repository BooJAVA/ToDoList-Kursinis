package com.example.bnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bnd.ds.*;
import com.example.bnd.helpers.TinkloKontroleris;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProjectListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        Intent dabar = this.getIntent();
        User prisijunges = (User)dabar.getSerializableExtra("user");
        Toast.makeText(ProjectListActivity.this, "Dabar prisijunges "+prisijunges.getLogin(), Toast.LENGTH_LONG).show();
        GetProjectList prisijungti = new GetProjectList();
        prisijungti.execute();
    }

    private final class GetProjectList extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            String url ="http://192.168.1.159:8080/lab5JAVA/projectlist.htm";
            try {
                return TinkloKontroleris.sendGet(url);
            } catch (Exception e) {
                e.printStackTrace();
                return "nepavyko gauti duomenu is web";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("GAUTA: " + result);
            if (result != null) {
                Gson parseris = new Gson();
                try {
                    Type listType = new TypeToken<ArrayList<User>>(){}.getType();
                    List<Project> projektai = new Gson().fromJson(result, listType);
                    ListView sar = findViewById(R.id.sarasui);
                    ArrayAdapter<Project> arrayAdapter = new ArrayAdapter<Project>
                            (ProjectListActivity.this, android.R.layout.simple_list_item_1, projektai);
                    sar.setAdapter(arrayAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ProjectListActivity.this, "Neteisingi duomenys", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(ProjectListActivity.this, "Neteisingi duomenys", Toast.LENGTH_LONG).show();
            }
        }
    }
}
