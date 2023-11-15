
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.grupo5.controlador;

import com.grupo5.Datos.UsuariosDAO;
import com.grupo5.modelo.Usuarios;
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
@WebServlet(name = "UsuariosControlador", urlPatterns = {"/UsuariosControlador"})
public class UsuariosControlador extends HttpServlet {

    UsuariosDAO udao = new UsuariosDAO();
    Usuarios us = new Usuarios();

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
        String inicio = request.getParameter("inicio");
        if (inicio.equalsIgnoreCase("ingresar")) {
            String user = request.getParameter("txtEmail");
            String pass = request.getParameter("txtPass");
            if (udao.validar(user, pass)) {
                response.sendRedirect("PrincipalControlador?accion=login");
            }else{
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
            
        // Nueva Forma
        //int id = Integer.parseInt(request.getParameter("id"));
        /*String update = request.getParameter("modificar");
        
        if (update.equalsIgnoreCase("Guardar Cuenta")) {
            String nombres = request.getParameter("txtNombre");
            String apellidos = request.getParameter("txtApellido");
            String email = request.getParameter("txtEmail");
            String clave = request.getParameter("txtClave");
            String confirmacion = request.getParameter("txtConfirmacion");
            if (udao.modificar(nombres, apellidos, email, clave, confirmacion)) {
                response.sendRedirect("PrincipalControlador?accion=login");
            }else{
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }*/
        

       // UsuariosDAO usuari = new UsuariosDAO();
        //usuari.modificar(nombres, apellidos, email, clave, confirmacion);

        // Redirigir o mostrar la vista actualizada
        //response.sendRedirect("editarPerfil.jsp");
        
        
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
