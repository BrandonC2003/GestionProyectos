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
import com.grupo5.Datos.TareasDAO;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Tareas;
import com.grupo5.Datos.EstadosDAO;
import com.grupo5.modelo.MisTareas;
import java.math.BigDecimal;
import java.sql.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.util.List;
import javax.json.JsonArrayBuilder;

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
        String accion = request.getParameter("accion");
        Tareas tarea;
        Estados estado;
        List<Estados> estados;
        TareasDAO tareaDao = new TareasDAO();
        EstadosDAO estadosDao = new EstadosDAO();
        JsonObject jsonObject;
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder builderArray = Json.createArrayBuilder();
        int idProyecto;
        int id;
        
        
        switch(accion){
            case "encontrar":
                id = Integer.parseInt(request.getParameter("idTarea"));
                idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
                
                tarea = tareaDao.buscarPorId(id);
                estado = tarea.getEstado();
                
                if(tarea.getIdTarea() > 0){
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);
                }else{
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                //Tengo que modificar la consulta para que me muestre todos los usuarios pertenecientes a la tarea. 
                builder.add("idTarea", tarea.getIdTarea());
                builder.add("tarea", tarea.getTarea());
                builder.add("descripcion", tarea.getDescripcion());
                builder.add("fechaInicio", tarea.getFechaInicio().toString());
                builder.add("fechaFin", tarea.getFechaFin().toString());
                builder.add("realizada", tarea.isRealizada());
                builder.add("idEstado",estado.getIdEstado());
                
                estados = estadosDao.buscarPorProyecto(idProyecto);
                
                for(Estados est : estados){
                    JsonObjectBuilder builder2 = Json.createObjectBuilder()
                            .add("idEstado",est.getIdEstado())
                            .add("estado",est.getEstado());
                    
                    builderArray.add(builder2);
                }
                
                builder.add("estados", builderArray);
                jsonObject = builder.build();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().write(jsonObject.toString());
                
                //Me falta obtener la lista de usuarios asociados a la tarea
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
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Tareas tarea = new Tareas();
        Estados estado = new Estados();
        TareasDAO tareasDao = new TareasDAO();
        JsonObject jsonObject;
        String message;
        int idUsuario;
        switch (accion) {
            case "insertar":

                tarea.setTarea(request.getParameter("tarea"));
                tarea.setDescripcion(request.getParameter("descripcion"));
                estado.setIdEstado(Integer.parseInt(request.getParameter("estado")));
                tarea.setEstado(estado);
                tarea.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
                tarea.setFechaFin(Date.valueOf(request.getParameter("fechaFin")));
                tarea.setUsuarioInserta("");
                idUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
                tarea.setIdTarea(tareasDao.insertarTarea(tarea,idUsuario));

                if (tarea.getIdTarea() != 0) {
                    response.setStatus(HttpServletResponse.SC_CREATED);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                builder.add("idTarea", tarea.getIdTarea());
                jsonObject = builder.build();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                response.getWriter().write(jsonObject.toString());
                break;
            case "actualizar":
                tarea.setIdTarea(Integer.parseInt(request.getParameter("idTarea")));
                tarea.setTarea(request.getParameter("tarea"));
                tarea.setDescripcion(request.getParameter("descripcion"));
                estado.setIdEstado(Integer.parseInt(request.getParameter("estado")));
                tarea.setEstado(estado);
                tarea.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
                tarea.setFechaFin(Date.valueOf(request.getParameter("fechaFin")));
                tarea.setUsuarioInserta("");
                idUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
                message = tareasDao.actualizarTarea(tarea, idUsuario);

                if (message == null) {
                    response.setStatus(HttpServletResponse.SC_CREATED);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                builder.add("idTarea", tarea.getIdTarea());
                jsonObject = builder.build();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                response.getWriter().write(jsonObject.toString());
                break;
            case "eliminar":
                tarea.setIdTarea(Integer.parseInt(request.getParameter("idTarea")));
                
                if(tareasDao.eliminarTarea(tarea.getIdTarea())){
                    response.setStatus(HttpServletResponse.SC_CREATED);
                }else{
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                builder.add("idTarea", tarea.getIdTarea());
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
