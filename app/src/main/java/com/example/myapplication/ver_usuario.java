package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.ui.main.Interfaz.Usuarios;
import com.example.myapplication.ui.main.Models.Posts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ver_usuario extends AppCompatActivity {

private ListView listView;
ArrayList<String> listado;
ArrayAdapter<String> s1;
static ArrayList<String> datos;

    private EditText nomm, corm, cedm, rom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);

        listView=findViewById(R.id.list);
        //getPosts();
       // s1= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,content);
        listdoUser();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Intent intent= new Intent( ver_usuario.this, Modificar.class);
                //startActivity(intent);
                Toast.makeText(getApplicationContext(),""+listView.getItemAtPosition(position),Toast.LENGTH_LONG).show();
                String ide1 = String.valueOf(listView.getItemAtPosition(position));
                String ide[] =ide1.split("\n");

                String n1[]=ide[0].split(":");
                String n2[]=ide[1].split(":");
                String n3[]=ide[2].split(":");
                String n4[]=ide[3].split(":");
                String n5[]=ide[4].split(":");

               String email,cedula,rol,clave,nombre;
                clave=n1[1];
              nombre=n2[1];
                email=n3[1];
                cedula=n4[1];
                rol=n5[1];

                Log.d("clave",clave+nombre+email+cedula+rol);


                Intent intent= new Intent( ver_usuario.this, Modificar.class);
                intent.putExtra("clave",clave);
                intent.putExtra("nombre",nombre);
                intent.putExtra("correo",email);
                intent.putExtra("cedula",cedula);
                intent.putExtra("rol",rol);


                startActivity(intent);
                finish();


            }
        });


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
                    Toast.makeText(getApplicationContext(),"No se pudo conectar"+response.code(),Toast.LENGTH_LONG).show();


                    return;
                }

                List<Posts> postsList = response.body();
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
                Toast.makeText(getApplicationContext(),"Listado completo! ",Toast.LENGTH_LONG).show();
                //Log.d("fytfy", String.valueOf(datos));

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        Log.d("Listo para agrragar",String.valueOf( datos));

        return datos;

    }

    private void getPosts() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-server-mongodb.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Usuarios jsonPlaceHolderApi = retrofit.create(Usuarios.class);

        Call<List<Posts>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if(!response.isSuccessful()){
                    //error.setText("Codigo: "+response.code());



                    return;
                }

                List<Posts> postsList = response.body();

                System.out.print("estoy dentro"+postsList);
                int i=0;

                for(Posts post: postsList){
                    String content = "";



                   /* error.setText(post.get_Id());
                    nom.setText(post.getNombre());
                    cedu.setText(post.getCedula());
                    prof.setText(post.getRole());
                    corr.setText(post.getEmail());

                    */
                    content+= "Id :"+ post.get_Id() + "\n";
                    content+="Nombre :"+ post.getNombre() + "\n";
                    content+= "Email :"+ post.getEmail() + "\n";
                    content+= "Cedula :"+ post.getCedula()+ "\n";
                    content+= "Rol :"+ post.getRole()+ "\n\n";
                   // textv.append(content);


                }


            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

               // error.setText(t.getMessage());

            }
        });


    }



}
