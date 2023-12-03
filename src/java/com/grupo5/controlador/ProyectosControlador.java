/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.grupo5.controlador;

import com.grupo5.Datos.GruposDAO;
import com.grupo5.Datos.ProyectosDAO;
import com.grupo5.modelo.Proyectos;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Tareas;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author brand
 */
@WebServlet(name = "ProyectosControlador", urlPatterns = {"/ProyectosControlador"})
public class ProyectosControlador extends HttpServlet {

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

        //Obtengo el parametro que contiene la accion.
        String accion = request.getParameter("accion");
        int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));

        //Creacion de objetos y variables necesarias.
        Proyectos proyecto = new Proyectos();
        Estados estado = new Estados();
        Tareas tarea = new Tareas();

        ProyectosDAO proyectoDao = new ProyectosDAO();
        GruposDAO grupoDao = new GruposDAO();
        switch (accion) {
            case "listar":
                proyecto = proyectoDao.obtenerProyecto(idProyecto);
                request.setAttribute("proyecto", proyecto);

                //Este objeto sirve para obtener todos los usuarios asociados al proyecto
                Proyectos grupo = new Proyectos();
                grupo = grupoDao.ObtenerGrupos(idProyecto);
                request.setAttribute("grupo", grupo);

                request.getRequestDispatcher("com.grupo5.vistas/proyectos.jsp").forward(request, response);
                break;
        }
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
        HttpSession session = request.getSession(false);
        //Obtengo el parametro que contiene la accion.
        String accion = request.getParameter("accion");

        //Objetos y variables a utilizar.
        Proyectos proyecto = new Proyectos();
        ProyectosDAO proyectoDao = new ProyectosDAO();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject jsonObject;
        GruposDAO grupoDao = new GruposDAO();
        switch (accion) {
            case "insertar":
                proyecto.setProyecto(request.getParameter("Proyecto"));
                proyecto.setDescripcion(request.getParameter("Descripcion"));
                proyecto.setGit(request.getParameter("Git"));
                proyecto.setUsuarioInserta("");//cuando se cree el login se colocara la sesion de usuario.

                int idGenerado = proyectoDao.insertarProyecto(proyecto);
                
                if(session != null){
                    int idUsuario = (int) session.getAttribute("idUsuario");
                    grupoDao.AgregarGrupo(idUsuario, idGenerado,"Administrador");
                }
                response.sendRedirect("PrincipalControlador?accion=login");
                break;
            case "modificar":
                proyecto.setIdProyecto(Integer.parseInt(request.getParameter("idProyecto")));
                proyecto.setProyecto(request.getParameter("proyecto"));
                proyecto.setDescripcion(request.getParameter("descripcion"));
                proyecto.setGit(request.getParameter("git"));

                String resp = proyectoDao.actualizarProyecto(proyecto);

                if (resp == null) {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                builder.add("resp", resp == null ? "" : resp);
                jsonObject = builder.build();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().write(jsonObject.toString());
                break;
            case "eliminar":
                proyecto.setIdProyecto(Integer.parseInt(request.getParameter("idProyecto")));
                
                if (proyectoDao.eliminarProyecto(proyecto.getIdProyecto())) {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                builder.add("resp", "");
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
