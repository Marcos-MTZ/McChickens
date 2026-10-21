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
import org.utl.ldsm504.mcchickens.controller.ControllerSucursal;
import org.utl.ldsm504.mcchickens.model.Sucursal;

/**
 *
 * @author uriel
 */
@Path("sucursal")
public class RESTSucursal {
    @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll(){
        String out = null;
        //Generar lista de objetos de sucursales
        List<Sucursal> sucursales = null;
        //Instanciar controlador de sucursal
        ControllerSucursal cs = new ControllerSucursal();
        //TryCatch para prevencion de errores
        try {
            sucursales = cs.getAll();
            out = new Gson().toJson(sucursales);
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
    public Response insert(@FormParam("datosSucursal")@DefaultValue("") String datosSucursal){
        String out = null;
        Sucursal sucursal = null;
        ControllerSucursal cs = null;
        Gson gson = new Gson(); // preparamos el objeto json que sera convertido a clase cliente
        
        try {                         // String, objeto
            sucursal = gson.fromJson(datosSucursal, Sucursal.class);
            cs = new ControllerSucursal();
            cs.agregarSucursal(sucursal);
            out = gson.toJson(sucursal);
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
    public Response delete(@FormParam("idSucursal") @DefaultValue("0")int id){
        ControllerSucursal cs = new ControllerSucursal();
        String out = null;
        try {
            cs.deleteSucursal(id);
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
    public Response update(@FormParam("datosSucursal") @DefaultValue("") String datosSucursal){
        String out = null;
        Sucursal sucursal = null;
        ControllerSucursal cs = new ControllerSucursal();
        Gson gson = new Gson();
        
        try {                         // String, objeto
            sucursal = gson.fromJson(datosSucursal, Sucursal.class);
            cs.updateSucursal(sucursal);
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
