/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.grupo5.controlador;

import com.grupo5.Datos.ProyectosDAO;
import com.grupo5.modelo.Proyectos;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Tareas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        //Creacion de objetos y variables necesarias.
        Proyectos proyecto = new Proyectos();
        Estados estado = new Estados();
        Tareas tarea = new Tareas();
        
        ProyectosDAO proyectoDao = new ProyectosDAO();
        switch(accion){
            case "listar":
                proyecto = proyectoDao.obtenerProyecto(5);
                request.setAttribute("proyecto",proyecto);
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
        
        //Obtengo el parametro que contiene la accion.
        String accion = request.getParameter("accion");
        
        //Objetos y variables a utilizar.
        Proyectos proyecto = new Proyectos();
        ProyectosDAO proyectoDao = new ProyectosDAO();
        
        switch(accion){
            case "insertar":
                proyecto.setProyecto(request.getParameter("Proyecto"));
                proyecto.setDescripcion(request.getParameter("Descripcion"));
                proyecto.setGit(request.getParameter("Git"));
                proyecto.setUsuarioInserta("");//cuando se cree el login se colocara la sesion de usuario.
                
                proyectoDao.insertarProyecto(proyecto);
                response.sendRedirect("PrincipalControlador?accion=proyectos");
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
