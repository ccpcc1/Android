package com.example.b09s208est.app;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //estas variables permitiran obtener los controles creados y asi poder manipularlos
    EditText txtIdentificacion;
    EditText txtNombres;
    EditText txtApellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapeamos las vars creadas con los controles, De esta manera podemos setear valores u obtenerlos.
        txtIdentificacion= (EditText) findViewById(R.id.txtIdentificacion);
        txtNombres=(EditText) findViewById(R.id.txtNombres);
        txtApellidos=(EditText)findViewById(R.id.txtApellidos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Estamos asignando el menu al activity
        getMenuInflater().inflate(R.menu.menu_clientes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //de acuerdo al icono seleccionado, se debe realizar una accion
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;


            case R.id.action_add:
                //Aca debemos trabajar con todos los controles que definen el cliente para ingresar para poder ingresar
               String ident=txtIdentificacion.getText().toString();
               String nombres=txtNombres.getText().toString();
               String apellidos=txtApellidos.getText().toString();
               if(ident.length()>0 && nombres.length()>0 && apellidos.length()>0)
               {
                   //abrimos la base de datos clientes
                   UsuarioSQLiteHelper usuario=new UsuarioSQLiteHelper(this,"DBClientes",null,1);
                   SQLiteDatabase db= usuario.getWritableDatabase();
                   db.execSQL("INSERT INTO Cliente (Identificacion,Nombre,Apellidos) VALUES("+ident+",'"+nombres+"','"+apellidos+")");
                   db.close();
                   Toast.makeText(this,"el usuario ha sido creado exitosamente",Toast.LENGTH_SHORT).show();
                   txtIdentificacion.setText("");
                   txtNombres.setText("");
                   txtApellidos.setText("");
               }
               else
               {
                   Toast.makeText(this,"Debe ingresar todos los datos asociados al usuario", Toast.LENGTH_SHORT).show();
               }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
