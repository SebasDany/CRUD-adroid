package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.ui.main.Interfaz.Usuarios;
import com.example.myapplication.ui.main.Models.Posts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.ui.main.SectionsPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private TextView mtitle;
    private Button ver;
    private  Button insertar;
    ArrayList<String> listado;
    ListView listView;
    public ArrayList<String> datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ver=findViewById(R.id.view);
        insertar = findViewById(R.id.insert);
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getPosts();
                Intent veruser= new Intent( MainActivity.this, ver_usuario.class);
                startActivity(veruser);
            }
        });
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerUser= new Intent(MainActivity.this,Registrar.class);
                startActivity(registerUser);
            }
        });
        mtitle = findViewById(R.id.title);
        listView=findViewById(R.id.list1);
       // listdoUser();
        //cargar();
            }
    private  void cargar(ArrayList<String> listado){
        //listado = listdoUser();
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);

        Log.d("estoy cargabdo",String.valueOf(listado));
        listView.setAdapter(adapter);

    }

    private ArrayList<String> listdoUser(){
        datos= new ArrayList<String>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-server-mongodb.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Usuarios jsonPlaceHolderApi = retrofit.create(Usuarios.class);

        Call<List<Posts>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if (!response.isSuccessful()) {
                    //listView.setAdapter("Codigo: " + response.code());


                    return;
                }

                List<Posts> postsList = response.body();


                System.out.print("estoy dentro" + postsList);
                int i = 0;
                for (Posts post : postsList) {
                    String content = "";

                    //error.setText(post.get_Id());
                    //nom.setText(post.getNombre());
                    //cedu.setText(post.getCedula());
                    //prof.setText(post.getRole());
                    //corr.setText(post.getEmail());
                    content += "Id :" + post.get_Id() + "\n";
                    content += "Nombre :" + post.getNombre() + "\n";
                    content += "Email :" + post.getEmail() + "\n";
                    content += "Cedula :" + post.getCedula() + "\n";
                    content += "Rol :" + post.getRole() + "\n\n";
                    datos.add(content);



                }
                cargar(datos);
                //Log.d("fytfy", String.valueOf(datos));

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                //error.setText(t.getMessage());

            }
        });

        Log.d("Listo para agrragar",String.valueOf( datos));

        return datos;

    }






}