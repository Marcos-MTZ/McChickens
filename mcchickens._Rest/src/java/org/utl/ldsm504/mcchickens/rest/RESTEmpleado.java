/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm504.mcchickens.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.ldsm504.mcchickens.controller.ControllerEmpleado;
import org.utl.ldsm504.mcchickens.model.Empleado;

/**
 *
 * @author uriel
 */
@Path("empleado")
public class RESTEmpleado {
    @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll(){
        String out = null;
        //Generar lista de objetos empleados
        List<Empleado> empleados = null;
        //Instanciar controlador de empleado
        ControllerEmpleado ce = new ControllerEmpleado();
        //TryCatch para prevencion de errores
        try {
            empleados = ce.getAll();
            out = new Gson().toJson(empleados);
        } catch (Exception e) {
            out = """
                  {"error":"No se puede acceder a la coleccion"}
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response insert(@FormParam("datosEmpleado")@DefaultValue("") String datosEmpleado){
        String out = null;
        Empleado empleado = null;
        ControllerEmpleado ce = null;
        Gson gson = new Gson(); // preparamos el objeto json que sera convertido a clase cliente
        
        try {                         // String, objeto
            empleado = gson.fromJson(datosEmpleado, Empleado.class);
            ce = new ControllerEmpleado();
            ce.agregarEmpleado(empleado);
            out = gson.toJson(empleado);
        } catch (JsonParseException je) {
            out = """
                  {"error":"Formato de datos no valido"}
                  """;
            je.printStackTrace();
        } catch (Exception ep){
            out = """
                  {"error":"Error interno del sevidor"}
                  """;
            ep.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response delete(@FormParam("idEmpleado") @DefaultValue("0")int id){
        ControllerEmpleado ce = new ControllerEmpleado();
        String out = null;
        try {
            ce.deleteEmpleado(id);
            out = """
                  {"Resultado":"Se elimino correctamente"}
                  """;
        } catch (Exception e) {
            out = """
                  {"Error":"No se puede acceder al servidor"}
                  """;
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response update(@FormParam("datosEmpleado") @DefaultValue("") String datosEmpleado){
        String out = null;
        Empleado empleado = null;
        ControllerEmpleado ce = new ControllerEmpleado();
        Gson gson = new Gson();
        
        try {                         // String, objeto
            empleado = gson.fromJson(datosEmpleado, Empleado.class);
            ce.updateEmpleado(empleado);
            out = """
                  {"result":"actualizado"}
                  """;
        } catch (JsonParseException je) {
            out = """
                  {"error":"Formato de datos no valido"}
                  """;
            je.printStackTrace();
        } catch (Exception ep){
            out = """
                  {"error":"Error interno del sevidor"}
                  """;
            ep.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
