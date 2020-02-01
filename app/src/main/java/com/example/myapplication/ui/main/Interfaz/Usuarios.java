package com.example.myapplication.ui.main.Interfaz;

import com.example.myapplication.ui.main.Models.ActualizarUsuario;
import com.example.myapplication.ui.main.Models.DatosUsuariosP;
import com.example.myapplication.ui.main.Models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Usuarios {
    @GET("obtener-usuario")
    Call<List<Posts>> getPost();

    @POST("insertar-usuario")
    Call<DatosUsuariosP> createUser(@Body DatosUsuariosP user);

    @PUT("actualizar-usuario/{id}")
    Call<ActualizarUsuario> updateUser(@Path("id") String id, @Body ActualizarUsuario us);

    @DELETE("eliminar-usuario/{id}")
    Call<Void> deleteUser(@Path("id")String id);
}


