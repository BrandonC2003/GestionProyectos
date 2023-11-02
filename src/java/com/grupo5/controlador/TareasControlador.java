/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.grupo5.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.grupo5.Datos.TareasDAO;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Tareas;
import java.math.BigDecimal;
import java.sql.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author brand
 */
@WebServlet(name = "TareasControlador", urlPatterns = {"/TareasControlador"})
public class TareasControlador extends HttpServlet {

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
        Tareas tarea = new Tareas();
        Estados estado = new Estados();
        TareasDAO tareasDao = new TareasDAO();

        switch (accion) {
            case "insertar":

                tarea.setTarea(request.getParameter("tarea"));
                tarea.setDescripcion(request.getParameter("descripcion"));
                estado.setIdEstado(Integer.parseInt(request.getParameter("estado")));
                tarea.setEstado(estado);
                tarea.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
                tarea.setFechaFin(Date.valueOf(request.getParameter("fechaFin")));
                tarea.setUsuarioInserta("");

                tarea.setIdTarea(tareasDao.insertarTarea(tarea));

                if (tarea.getIdTarea() != 0) {
                    response.setStatus(HttpServletResponse.SC_CREATED);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                builder.add("idTarea", tarea.getIdTarea());
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
