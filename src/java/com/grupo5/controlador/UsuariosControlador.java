
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.grupo5.controlador;

import com.grupo5.Datos.TareasDAO;
import com.grupo5.Datos.UsuariosDAO;
import com.grupo5.modelo.MisTareas;
import com.grupo5.modelo.Usuarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        
        
        String accion = request.getParameter("accion");
         HttpSession session = request.getSession(false);
        
        Usuarios usuario = new Usuarios();
        UsuariosDAO usuarioDao = new UsuariosDAO();
        
        switch(accion){
            case "modificar":
               if(session != null){
                  int idUsuario = (int) session.getAttribute("idUsuario");
                usuario = usuarioDao.obtenerUsuario(idUsuario);
               //establecer los atributos
                request.setAttribute("usuarios", usuario);
                // Redirigir a la página de edición de perfil
                request.getRequestDispatcher("com.grupo5.vistas/opcionesRegistro/editarPerfil.jsp").forward(request, response);
               }
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
        String accion = request.getParameter("accion");
        Usuarios us = new Usuarios();
        
         HttpSession sesion = request.getSession(true);

        switch (accion) {
            case "ingresar":

                String user = request.getParameter("txtEmail");
                String pass = request.getParameter("txtPass");
                us = udao.validar(user, pass);
                if (us.getIdUsuario() != 0) {
                    response.sendRedirect("PrincipalControlador?accion=login");
                    
                    sesion.setAttribute("idUsuario", us.getIdUsuario());
                    sesion.setAttribute("mail",us.getEmail());
                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;

            case "modificar":
                UsuariosDAO modificar = new UsuariosDAO();
                
                UsuariosDAO da = new UsuariosDAO();
                Usuarios usuari = new Usuarios();
                
                usuari.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario"))); //request.getParameter("IdUsuario")
                usuari.setNombre(request.getParameter("Nombre"));
                usuari.setApellido(request.getParameter("Apellido"));
                
                String resultad = da.modificar(usuari);

                response.sendRedirect("PrincipalControlador?accion=login");
                break;
            case "clave":
                UsuariosDAO contra = new UsuariosDAO();

                String clave = request.getParameter("txtClave");

                contra.clave(clave, Integer.parseInt(request.getParameter("IdUsuario")));
                response.sendRedirect("PrincipalControlador?accion=login");
                break;
            case "insertar":
                UsuariosDAO dao = new UsuariosDAO();
                Usuarios usuario = new Usuarios();

                usuario.setNombre(request.getParameter("Name"));
                usuario.setApellido(request.getParameter("Apell"));
                usuario.setEmail(request.getParameter("Email"));
                usuario.setClave(request.getParameter("Password"));

                boolean resultado = dao.InsertarUsuario(usuario);
                
                response.sendRedirect("PrincipalControlador?accion=login");
                
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
