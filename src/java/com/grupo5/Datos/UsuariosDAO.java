
package com.grupo5.Datos;
package com.grupo5.modelo;

import com.grupo5.config.Conexion;
import com.grupo5.modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuariosDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
   /* public Usuarios validar(){
       Usuarios us = new Usuarios();
       String sql="select * from usuario where Email=? and Clave=?";
       try{
                     
           ps=con.prepareStatement(sql);
       
       }
       catch(Exception e)
       {
       }
     
        return us;
    }*/
}
