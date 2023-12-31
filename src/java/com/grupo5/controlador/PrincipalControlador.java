/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.grupo5.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.grupo5.Datos.ProyectosDAO;
import com.grupo5.Datos.TareasDAO;
import com.grupo5.modelo.MisTareas;
import com.grupo5.modelo.Proyectos;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
/**
 *
 * @author brand
 */
@WebServlet(name = "PrincipalControlador", urlPatterns = {"/PrincipalControlador"})
public class PrincipalControlador extends HttpServlet {

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
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession(false);
        String mail = (String) session.getAttribute("mail");
        request.setAttribute("mail",mail);
        TareasDAO tareasDao = new TareasDAO();
        List<MisTareas> listMisTareas = new ArrayList<>();
        
        switch(accion){
            case "proyectos":
                int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
                response.sendRedirect("ProyectosControlador?accion=listar&idProyecto="+idProyecto);
                break;
            case "login":
                int idUsuario2 = (int) session.getAttribute("idUsuario");
                List<Proyectos> proyectos = new ProyectosDAO().listarProyectos(idUsuario2);
                
                request.setAttribute("proyectos",proyectos);
                
                request.getRequestDispatcher("principal.jsp").forward(request,response);
                break;
            case "obtenerTareas":
               if(session != null){
                  int idUsuario = (int) session.getAttribute("idUsuario");
                listMisTareas = tareasDao.obtenerMisTareas(idUsuario);
               //establecer los atributos
                request.setAttribute("listTareas", listMisTareas);
                // Redirigir a la página de edición de perfil
                request.getRequestDispatcher("com.grupo5.vistas/misTareas.jsp").forward(request, response);
               }
                break;
            case "inicio":
                int idUsuario3 = (int) session.getAttribute("idUsuario");
                List<Proyectos> proyects = new ProyectosDAO().listarProyectos(idUsuario3);
                request.setAttribute("proyectos",proyects);
                request.getRequestDispatcher("com.grupo5.vistas/inicio.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("com.grupo5.vistas/"+accion+".jsp").forward(request, response);
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
