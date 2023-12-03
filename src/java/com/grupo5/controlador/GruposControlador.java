/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.grupo5.controlador;

import com.grupo5.Datos.GruposDAO;
import com.grupo5.Datos.ProyectosDAO;
import com.grupo5.modelo.Proyectos;
import com.grupo5.modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gerso
 */
@WebServlet(name = "GruposControlador", urlPatterns = {"/GruposControlador"})
public class GruposControlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        //Obtengo el parametro que contiene la accion.
        String accion = request.getParameter("accion");
        
        //Objetos y variables a utilizar.
        Proyectos proyecto = new Proyectos();
        GruposDAO grupoDao = new GruposDAO();
        Usuarios usuario = new Usuarios();
         JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject jsonObject;
        int idUsuario;
        int idProyecto;
        String mensaje;
        String rol;
        String email;
        
        switch(accion){
            case "insertar":
                email = request.getParameter("email");
                idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
                rol = request.getParameter("rol");
                rol = "Miembro";
                usuario = grupoDao.encontrarUsuario(email);
                
                if(usuario.getIdUsuario() != 0){
                    idUsuario = usuario.getIdUsuario();
                    
                    mensaje = grupoDao.AgregarGrupo(idUsuario, idProyecto, rol);
                }else{
                    mensaje = "El usuario que intentas agregar no está registrado en la base de datos.";
                }
                
                if(mensaje == null){
                    response.setStatus(HttpServletResponse.SC_OK);
                }else{
                    response.setStatus(200);
                }
                builder.add("mensaje", mensaje == null ? "" : mensaje);
                builder.add("Nombre", usuario.getIdUsuario() == 0 ? "" : usuario.getNombre()+" "+usuario.getApellido());
                builder.add("IdUsuario", usuario.getIdUsuario());
                jsonObject = builder.build();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                response.getWriter().write(jsonObject.toString());
                
                break;
            case "modificar":
                idUsuario= Integer.parseInt(request.getParameter("idUsuario"));
                idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
                rol = request.getParameter("rol");
                
                mensaje = grupoDao.ActualizarGrupo(idUsuario, idProyecto, rol);
                
                if(mensaje == null){
                    response.setStatus(HttpServletResponse.SC_OK);
                }else{
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                builder.add("mensaje", mensaje == null ? "" : mensaje);
                jsonObject = builder.build();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                response.getWriter().write(jsonObject.toString());
                
                break;
            case "eliminar":
                idUsuario= Integer.parseInt(request.getParameter("idUsuario"));
                idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
                
                if(grupoDao.EliminarGrupo(idUsuario, idProyecto)){
                    response.setStatus(HttpServletResponse.SC_OK);
                }else{
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                builder.add("mensaje", "No se pudo eliminar el usuario del grupo.");
                jsonObject = builder.build();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                response.getWriter().write(jsonObject.toString());
                
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
