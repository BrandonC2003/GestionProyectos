
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.grupo5.controlador;

import com.grupo5.Datos.UsuariosDAO;
import com.grupo5.modelo.Usuarios;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

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
        String accion = request.getParameter("accion");

        switch (accion) {
            case "ingresar":

                String user = request.getParameter("txtEmail");
                String pass = request.getParameter("txtPass");
                if (udao.validar(user, pass)) {
                    response.sendRedirect("PrincipalControlador?accion=login");
                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;

            /*case "modificar":
                UsuariosDAO modificar = new UsuariosDAO();

                String nombres = request.getParameter("txtNombre");
                String apellidos = request.getParameter("txtApellido");

                modificar.modificar(nombres, apellidos);

                response.sendRedirect("PrincipalControlador?accion=login");
                break;
            case "clave":
                UsuariosDAO contra = new UsuariosDAO();

                String clave = request.getParameter("txtClave");

                //contra.clave(clave, Integer.parseInt(request.getParameter("IdUsuario")));
                response.sendRedirect("PrincipalControlador?accion=login");
                break;*/
            case "insertar":
                UsuariosDAO dao = new UsuariosDAO();
                Usuarios usuario = new Usuarios();

                usuario.setNombre(request.getParameter("Name"));
                usuario.setApellido(request.getParameter("Apell"));
                usuario.setEmail(request.getParameter("Email"));
                usuario.setClave(request.getParameter("Password"));

                boolean resultado = dao.InsertarUsuario(usuario);
                //System.out.println("Resultado de la inserción: " + resultado);
                /* proyecto.setProyecto(request.getParameter("Proyecto"));
                proyecto.setDescripcion(request.getParameter("Descripcion"));
                proyecto.setGit(request.getParameter("Git"));
                proyecto.setUsuarioInserta("");//cuando se cree el login se colocara la sesion de usuario.
                
                int idGenerado = proyectoDao.insertarProyecto(proyecto);*/
                /*String name = request.getParameter("txtName");
                String surname = request.getParameter("txtApell");
                String corr = request.getParameter("txtEmail");
                String contrasena = request.getParameter("txtPassword");

                into.Insertar(name, surname, corr, contrasena);*/
                response.sendRedirect("PrincipalControlador?accion=login");
                //request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            default:
                request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
