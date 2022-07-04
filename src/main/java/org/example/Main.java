package org.example;

import com.google.gson.Gson;
import org.example.data.PropertyDao;
import org.example.data.PropertyManagerDao;
import org.example.data.TenantDao;
import org.example.exceptions.ApiException;
import org.example.models.Property;
import org.example.models.PropertyManager;
import org.example.models.Tenant;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        PropertyManagerDao propertyManagerDao = new PropertyManagerDao();
        TenantDao tenantDao = new TenantDao();
        PropertyDao propertyDao = new PropertyDao();
        Gson gson = new Gson();


        //add a property manager
        post("/propertymanager","application/json ",(request, response) -> {

            PropertyManager propertyManager = gson.fromJson(request.body(), PropertyManager.class);

             if (propertyManager.manager_name == null){
                throw new ApiException(404, "Please enter the name of the property manager");
            }else if (propertyManager.phone_number == null){
                throw new ApiException(404, "Please enter the phone number of the property manager");
            }else if (propertyManager.email == null){
                throw new ApiException(404, "Please enter the email address of the property manager");
            }else if (propertyManager.property_name == null){
                throw new ApiException(404, "Please enter the property name of the property manager");
            }else if (propertyManager.property_description == null){
                throw new ApiException(404, "Please enter the property description of the property");
            }else{
                propertyManagerDao.addPropertyManager(propertyManager);
                response.status(201);
                return gson.toJson(propertyManager);
            }
        });

        //get all property managers
        get("/propertymanagers", "application/json", (req, res) -> { //accept a request in format JSON from an app
            if(propertyManagerDao.getAllPropertyManagers() == null){
                throw new ApiException(404, "Oops, there are no property managers currently");
            }
            return gson.toJson(propertyManagerDao.getAllPropertyManagers());//send it back to be displayed
        });

        //get property manager by id
        get("/propertymanager/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app

            try {
                int id = Integer.parseInt(req.params(":id"));
                PropertyManager manager = propertyManagerDao.getPropertyManagerById(id);

                if(manager == null){
                    throw new ApiException(404, "The property manager with the given id doesn't exist");
                }
                return gson.toJson(propertyManagerDao.getPropertyManagerById(id));//send it back to be displayed

            } catch (NumberFormatException nfe) {
                System.out.println("NumberFormat Exception: invalid input string");
                return nfe.getMessage();
            }

        });

        //get all managers properties
        get("/managerProperties/:managerName", "application/json", (req,res)->{
            String managerName = req.params(":managerName");

            if (managerName != null){
                if (propertyManagerDao.propertyManagerProperties(managerName) !=null){
                    return gson.toJson(propertyManagerDao.propertyManagerProperties(managerName));

                }else{
                  return new ApiException(404, "The given manager is not available");
                }
            }else{
                return  new ApiException(404, "Please Enter the name of the manager");
            }

        });


        //add a tenant
        post("/tenant","application/json ",(request, response) -> {
            Tenant tenant = gson.fromJson(request.body(), Tenant.class);

            if (tenant.getTenant_name() == null){
                throw new ApiException(404, "Please enter the name of the tenant");
            }else if (tenant.getTenant_email() == null){
                throw new ApiException(404, "Please enter the email address of the tenant");
            }else if (tenant.getProperty_name() == null){
                throw new ApiException(404, "Please enter the property name  of the tenant");
            }else{
                tenantDao.addTenant(tenant);
                response.status(201);
                return gson.toJson(tenant);

            }
        });

        //get tenant by id
        get("/tenant/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app

            try {
                int id = Integer.parseInt(req.params(":id"));
                Tenant tenant = tenantDao.getTenantById(id);

                if(tenant == null){
                    throw new ApiException(404, "The tenant with the given id doesn't exist");
                }
                return gson.toJson(tenantDao.getTenantById(id));//send it back to be displayed

            } catch (NumberFormatException nfe) {
                System.out.println("NumberFormat Exception: invalid input string");
                return nfe.getMessage();
            }
        });

        //get all tenants
        get("/tenants", "application/json", (req, res) -> { //accept a request in format JSON from an app
            if (tenantDao.getAllTenants() != null) {
                return gson.toJson(tenantDao.getAllTenants());//send it back to be displayed
            }else {
                return new ApiException(404, "Oops there are no tenants available");
            }
        });

        //get all tenants in the same Property
        get("/tenants/:propertyName", "application/json", (req, res)->{
            String propertyName = req.params(":propertyName");

            if(tenantDao.getTenantsInAProperty(propertyName) != null){
                return gson.toJson(tenantDao.getTenantsInAProperty(propertyName));
            }else {
                return new ApiException(404, "There are no tenants in the available property");
            }

        });

        //add a property
        post("/property","application/json ",(request, response) -> {
            Property property = gson.fromJson(request.body(), Property.class);

            if (property.property_name == null){
                throw new ApiException(404, "Please enter the name of the property");
            }else if (property.manager_name == null){
                throw new ApiException(404, "Please enter the name of the property manager");
            }else {
                propertyDao.addProperty(property);
                response.status(201);
                return gson.toJson(property);
            }
        });

        //get property by id
        get("/property/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app

            try{
                int id = Integer.parseInt(req.params(":id"));
                Property property = propertyDao.getPropertyById(id);
                if (property == null){
                    throw new ApiException(404, "The property with the given id doesn't exist");
                }
                return gson.toJson(propertyDao.getPropertyById(id));//send it back to be displayed

            }catch (NumberFormatException nt){

                return nt.getMessage();
            }
        });

        //get all properties
        get("/property", "application/json", (req, res) -> { //accept a request in format JSON from an app
            if (propertyDao.getAllProperties() != null){
                return gson.toJson(propertyDao.getAllProperties());//send it back to be displayed
            }else{
                return new ApiException(404, "Oops there are no properties available");
            }
        });


        exception(ApiException.class, (exc, req, res) -> {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", exc.getStatusCode());
            jsonMap.put("errorMessage", exc.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(exc.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });

        after((req,res)-> res.type("application/json"));
    }

}
