package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaController;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.ui.main.Interfaz.Usuarios;
import com.example.myapplication.ui.main.Models.DatosUsuariosP;
import com.example.myapplication.ui.main.Models.Posts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class Registrar extends AppCompatActivity {
    private EditText nom, cor, ced,pass;
    private Button regist, consul;
    private Spinner sp;

private Usuarios jsonPlaceHolderApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);


pass=findViewById(R.id.password);
nom=findViewById(R.id.nombre);
cor=findViewById(R.id.correo);
ced=findViewById(R.id.cedula);



regist=findViewById(R.id.registrar);
sp=findViewById(R.id.spinner);

regist.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        createUser();
    }
});
consul=findViewById(R.id.consultar);
consul.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent veruser= new Intent( Registrar.this, ver_usuario.class);
        startActivity(veruser);
        finish();

    }
});


    }


    public void createUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-server-mongodb.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(Usuarios.class);
        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String text = mySpinner.getSelectedItem().toString();
        Log.d("jjjjjjj",text);
        //Log.d("datos",ro.getText().toString()+nom.getText().toString()+cor.getText().toString()+ced.getText().toString()+pass.getText().toString());
        DatosUsuariosP user = new DatosUsuariosP( text, nom.getText().toString(),cor.getText().toString(),ced.getText().toString(),pass.getText().toString());
        //DatosUsuariosP user = new DatosUsuariosP("Estudiante", "thisismypassword","wonder16582@gmail.com","12323","bfgjdjg");
    Call<DatosUsuariosP> call =  jsonPlaceHolderApi.createUser(user);
    call.enqueue(new Callback<DatosUsuariosP>() {
        @Override
        public void onResponse(Call<DatosUsuariosP> call, Response<DatosUsuariosP> response) {


            //Log.d("estoy dentro del respo",ro.getText().toString());
            if(!response.isSuccessful()){
                Toast.makeText(getApplicationContext(),"No se pudo conectar"+response.code(),Toast.LENGTH_LONG).show();



                return;
            }

            DatosUsuariosP du=response.body();
            Toast.makeText(getApplicationContext(),"Datos ingresados correctamente",Toast.LENGTH_LONG).show();
           nom.setText(null);
           cor.setText(null);
           ced.setText(null);
           pass.setText(null);
        }


        @Override
        public void onFailure(Call<DatosUsuariosP> call, Throwable t) {

            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();


        }
    });





    }

}