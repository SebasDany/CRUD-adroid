package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.main.Interfaz.Usuarios;
import com.example.myapplication.ui.main.Models.ActualizarUsuario;
import com.example.myapplication.ui.main.Models.DatosUsuariosP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Modificar extends AppCompatActivity {
    private EditText nomm, corm, cedm, rom,idem;
    private TextView tid;
    private Button act,elim;
    private Usuarios jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b=getIntent().getExtras();


        nomm=findViewById(R.id.nombrem);
        corm=findViewById(R.id.correom);
        cedm=findViewById(R.id.cedulam);
        rom=findViewById(R.id.rolm);

        act=findViewById(R.id.actualizar);
        elim=findViewById(R.id.eliminar);
        tid=findViewById(R.id.textid);

        nomm.setText(b.getString("nombre"));
        corm.setText(b.getString("correo"));
        rom.setText(b.getString("rol"));
        cedm.setText(b.getString("cedula"));
        //idem.setText(b.getString("clave"));i
         tid.append(b.getString("clave"));



        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
                Intent veruser= new Intent( Modificar.this, ver_usuario.class);
                startActivity(veruser);
                finish();

            }
        });
        elim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
                Intent veruser= new Intent( Modificar.this, ver_usuario.class);
                startActivity(veruser);
                finish();
            }
        });


    }

    public void updateUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-server-mongodb.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(Usuarios.class);

        Log.d("clave del ", tid.getText().toString());
        //Log.d("datos",ro.getText().toString()+nom.getText().toString()+cor.getText().toString()+ced.getText().toString()+pass.getText().toString());
        ActualizarUsuario user = new ActualizarUsuario( rom.getText().toString(), nomm.getText().toString(),corm.getText().toString(),cedm.getText().toString());

        // DatosUsuariosP user = new DatosUsuariosP("Estudiante", "thisismypassword","wonder1652@gmail.com","12323","bfgjdjg");
        Call<ActualizarUsuario> call =  jsonPlaceHolderApi.updateUser(tid.getText().toString(),user);
        call.enqueue(new Callback<ActualizarUsuario>() {
            @Override
            public void onResponse(Call<ActualizarUsuario> call, Response<ActualizarUsuario> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"No se pudo conectar"+response.code(),Toast.LENGTH_LONG).show();



                    return;
                }

                ActualizarUsuario du =response.body();
                Toast.makeText(getApplicationContext(),"Datos actualizados correctamente",Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<ActualizarUsuario> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

           }
    public void deleteUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-server-mongodb.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(Usuarios.class);
        //Log.d("datos",ro.getText().toString()+nom.getText().toString()+cor.getText().toString()+ced.getText().toString()+pass.getText().toString());
        //ActualizarUsuario user = new ActualizarUsuario( rom.getText().toString(), nomm.getText().toString(),corm.getText().toString(),cedm.getText().toString());

        // DatosUsuariosP user = new DatosUsuariosP("Estudiante", "thisismypassword","wonder1652@gmail.com","12323","bfgjdjg");
        Call<Void> call =  jsonPlaceHolderApi.deleteUser(tid.getText().toString());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"No se pudo conectar"+response.code(),Toast.LENGTH_LONG).show();



                    return;
                }


                Toast.makeText(getApplicationContext(),"Dato eliminado correctamente",Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

    }

}
