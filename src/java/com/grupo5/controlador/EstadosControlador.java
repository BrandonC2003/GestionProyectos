/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.grupo5.controlador;

import com.grupo5.Datos.EstadosDAO;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Proyectos;
import java.io.IOException;
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
 * @author brand
 */
@WebServlet(name = "EstadosControlador", urlPatterns = {"/EstadosControlador"})
public class EstadosControlador extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Estados estado = new Estados();
        EstadosDAO estadosDao = new EstadosDAO();
        Proyectos proyecto = new Proyectos();

        switch (accion) {
            case "insertar":

                estado.setEstado(request.getParameter("estado"));
                estado.setColor(request.getParameter("color"));
                proyecto.setIdProyecto(Integer.parseInt(request.getParameter("idProyecto")));
                estado.setProyecto(proyecto);

                estado.setIdEstado(estadosDao.insertarEstado(estado));

                if (estado.getIdEstado() != 0) {
                    response.setStatus(HttpServletResponse.SC_CREATED);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                builder.add("idEstado", estado.getIdEstado());
                JsonObject jsonObject = builder.build();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                response.getWriter().write(jsonObject.toString());
                break;
            case "actualizar":
                break;
            case "eliminar":
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
