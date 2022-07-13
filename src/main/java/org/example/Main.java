package org.example;

import com.google.gson.Gson;
import org.example.data.*;
import org.example.exceptions.ApiException;
import org.example.models.*;

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
        UnitDao unitDao = new UnitDao();
        DefectDao dao = new DefectDao();

        Gson gson = new Gson();


        //add a property manager
        post("/propertyManager","application/json ",(request, response) -> {

            PropertyManager propertyManager = gson.fromJson(request.body(), PropertyManager.class);

             if (propertyManager.getManager_name() == null){
                throw new ApiException(401, "Please enter the name of the property manager");
            }else if (propertyManager.getPhone_number() == null){
                throw new ApiException(402, "Please enter the phone number of the property manager");
            }else if (propertyManager.getEmail() == null){
                throw new ApiException(403, "Please enter the email address of the property manager");
            }else{
                propertyManagerDao.addPropertyManager(propertyManager);
                response.status(201);
                return gson.toJson(propertyManager);
            }
        });

        //get all property managers
        get("/propertymanagers", "application/json", (req, res) -> { //accept a request in format JSON from an app
            if(propertyManagerDao.getAllPropertyManagers() == null){
                throw new ApiException(403, "Oops, there are no property managers currently");
            }
            return gson.toJson(propertyManagerDao.getAllPropertyManagers());//send it back to be displayed
        });

        //get property manager by id
        get("/propertyManager/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app

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
        get("/managerProperties/:name", "application/json", (req,res)->{
            String name = req.params(":name");

                if (propertyManagerDao.propertyManagerProperties(name) !=null){
                    return gson.toJson(propertyManagerDao.propertyManagerProperties(name));

                }else{
                  return new ApiException(403, "The given manager is not available");
                }

        });

        //updating a propertyManager
        patch("/updatePropertyManager/:id", (req, res) -> {
            PropertyManager manager = gson.fromJson(req.body(), PropertyManager.class);
            int id = Integer.parseInt(req.params(":id"));
            if (propertyManagerDao.getPropertyManagerById(id) == null) {
                throw new ApiException(403, String.format("The article with id %s does not exist thus can't update", id));

            } else {
                manager.setId(id);
                propertyManagerDao.update(id,manager);
                return gson.toJson(manager);
            }
        });

        //delete a property manager based on his id
        delete("/propertyManager/:id", (req, res) -> {

            int propertyManagerId = Integer.parseInt(req.params(":id"));
            if (propertyManagerDao.getPropertyManagerById(propertyManagerId) == null) {
                throw new ApiException(404, String.format("The Article with an ID of %s does not exist you cant delete it", propertyManagerId));
            } else {
                propertyManagerDao.deleteById(propertyManagerId);
                return gson.toJson(propertyManagerDao.getAllPropertyManagers());
            }
        });


        //add a tenant
        post("/tenant","application/json ",(request, response) -> {
            Tenant tenant = gson.fromJson(request.body(), Tenant.class);

            if (tenant.getTenant_name() == null){
                throw new ApiException(400, "Please enter the name of the tenant");
            }else if (tenant.getTenant_email() == null){
                throw new ApiException(401, "Please enter the email address of the tenant");
            }else if (tenant.getTenant_phone() == null){
                throw new ApiException(403, "Please enter the phone number  of the tenant");
            }else if (tenant.getTenant_id() == null){
                throw new ApiException(403, "Please enter the id number of the tenant");
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
        //get tenant in a unit
        get("/singleTenant/:name", "application/json", (req, res)->{
            String name = req.params(":name");

            if(tenantDao.getTenantByUnitName(name) != null){
                return gson.toJson(tenantDao.getTenantByUnitName(name));
            }else {
                return new ApiException(404, "There are no tenants in the available property");
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
        get("/tenants/:name", "application/json", (req, res)->{
            String name = req.params(":name");

            if(tenantDao.getTenantsInAProperty(name) != null){
                return gson.toJson(tenantDao.getTenantsInAProperty(name));
            }else {
                return new ApiException(404, "There are no tenants in the available property");
            }

        });

        //get all tenants of the same manager
        get("/Tenants/:name", "application/json", (req, res)->{
            String name = req.params(":name");

            if(tenantDao.getTenantsWIthSameManager(name) != null){
                return gson.toJson(tenantDao.getTenantsWIthSameManager(name));
            }else {
                return new ApiException(404, "There are no tenants in the available property");
            }

        });

        //updating a tenant
        patch("/updateTenant/:id", (req, res) -> {
            Tenant tenant = gson.fromJson(req.body(), Tenant.class);
            int id = Integer.parseInt(req.params(":id"));
            if (tenantDao.getTenantById(id) == null) {
                throw new ApiException(403, String.format("The article with id %s does not exist thus can't update", id));

            } else {
                tenant.setId(id);
                tenantDao.updateTenant(id,tenant);
                return gson.toJson(tenant);
            }
        });

        //delete a tenant based on his id
        delete("/tenant/:id", (req, res) -> {

            int tenantId = Integer.parseInt(req.params(":id"));
            if (tenantDao.getTenantById(tenantId) == null) {
                throw new ApiException(404, String.format("The Article with an ID of %s does not exist you cant delete it", tenantId));
            } else {
                tenantDao.delete(tenantId);
                return gson.toJson(tenantDao.getAllTenants());
            }
        });

        //add a property
        post("/property","application/json ",(request, response) -> {
            Property property = gson.fromJson(request.body(), Property.class);

            if (property.getProperty_name() == null){
                throw new ApiException(401, "Please enter the name of the property");
            }else if (property.getProperty_location() == null){
                throw new ApiException(403, "Please enter the location of the property");
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

        //updating a property
        patch("/updateProperty/:id", (req, res) -> {
            Property property = gson.fromJson(req.body(), Property.class);
            int id = Integer.parseInt(req.params(":id"));
            if (propertyDao.getPropertyById(id) == null) {
                throw new ApiException(403, String.format("The article with id %s does not exist thus can't update", id));

            } else {
                property.setId(id);
                propertyDao.updateProperty(id,property);
                return gson.toJson(property);
            }
        });

        //delete a property based on his id
        delete("/property/:id", (req, res) -> {

            int propertyId = Integer.parseInt(req.params(":id"));
            if (propertyDao.getPropertyById(propertyId) == null) {
                throw new ApiException(404, String.format("The Article with an ID of %s does not exist you cant delete it", propertyId));
            } else {
                propertyDao.deleteProperty(propertyId);
                return gson.toJson(propertyDao.getAllProperties());
            }
        });

        //add a unit
        post("/unit","application/json ",(request, response) -> {
            Unit unit = gson.fromJson(request.body(), Unit.class);

            if (unit.getUnitName() == null){
                throw new ApiException(401, "Please enter the name of the unit");
            }else if (unit.getUnit_rooms() == null){
                throw new ApiException(403, "Please enter the number of the rooms of the unit");
            }else {
                unitDao.addUnit(unit);
                response.status(201);
                return gson.toJson(unit);
            }
        });

        //get unit by id
        get("/unit/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app

            try{
                int id = Integer.parseInt(req.params(":id"));
                Unit unit = unitDao.getUnitById(id);
                if (unit == null){
                    throw new ApiException(402, "The unit with the given id doesn't exist");
                }
                return gson.toJson(unitDao.getUnitById(id));//send it back to be displayed

            }catch (NumberFormatException nt){

                return nt.getMessage();
            }
        });

        //get all units
        get("/units", "application/json", (req, res) -> { //accept a request in format JSON from an app
            if (unitDao.getAllUnits() != null){
                return gson.toJson(unitDao.getAllUnits());//send it back to be displayed
            }else{
                return new ApiException(404, "Oops there are no properties available");
            }
        });

        //get units in the same property
        get("/units/:name", "application/json", (req, res) -> { //accept a request in format JSON from an app

            String name = req.params(":name");

                if (unitDao.getAllUnitsInSameProperty(name) !=null){
                    return gson.toJson(unitDao.getAllUnitsInSameProperty(name));

                }else{
                    return new ApiException(401, "Oops there are no units available");
                }

        });

        //updating a unit
        patch("/updateUnit/:id", (req, res) -> {
            Unit unit = gson.fromJson(req.body(), Unit.class);
            int id = Integer.parseInt(req.params(":id"));
            if (unitDao.getUnitById(id) == null) {
                throw new ApiException(403, String.format("The article with id %s does not exist thus can't update", id));

            } else {
                unit.setId(id);
                unitDao.updateUnit(id,unit);
                return gson.toJson(unit);
            }
        });

        //delete a unit based on his id
        delete("/unit/:id", (req, res) -> {

            int unitId = Integer.parseInt(req.params(":id"));
            if (unitDao.getUnitById(unitId) == null) {
                throw new ApiException(404, String.format("The Article with an ID of %s does not exist you cant delete it", unitId));
            } else {
                unitDao.deleteUnit(unitId);
                return gson.toJson(unitDao.getAllUnits());
            }
        });

        //add a defect
        post("/defect","application/json ",(request, response) -> {
            Defect defect = gson.fromJson(request.body(), Defect.class);

            if (defect.getDescription() == null){
                throw new ApiException(401, "Please enter the description of the defect");
            }else if (defect.getUnit_name() == null){
                throw new ApiException(403, "Please enter the name of the unit the defect is associated with");
            }else if (defect.getTenant_id() == null){
                throw new ApiException(403, "Please enter the person associated with the defect");
            }else if (defect.getManager_name() == null){
                throw new ApiException(403, "Please enter the person associated with the defect");
            }else if (defect.getProperty_name() == null){
                throw new ApiException(403, "Please enter the property associated with the defect");
            }else if (defect.getString_uri() == null){
                throw new ApiException(403, "Please enter the image associated with the defect");
            }else {
                dao.addDefect(defect);
                response.status(201);
                return gson.toJson(defect);
            }
        });

        //get defect by id
        get("/defect/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app

            try{
                int id = Integer.parseInt(req.params(":id"));
                Defect defect = dao.getDefectById(id);
                if (defect == null){
                    throw new ApiException(404, "The property with the given id doesn't exist");
                }
                return gson.toJson(dao.getDefectById(id));//send it back to be displayed

            }catch (NumberFormatException nt){

                return nt.getMessage();
            }
        });

        //get all defects
        get("/defects", "application/json", (req, res) -> { //accept a request in format JSON from an app
            if (dao.getAllDefects() != null){
                return gson.toJson(dao.getAllDefects());//send it back to be displayed
            }else{
                return new ApiException(404, "Oops there are no properties available");
            }
        });

        //get all defects of the same manager
        get("/defects/:managerName", "application/json", (req, res)->{
            String name = req.params(":managerName");

            if(dao.getDefectsOfSameManager(name) != null){
                return gson.toJson(dao.getDefectsOfSameManager(name));
            }else {
                return new ApiException(404, "There are no tenants in the available property");
            }

        });

        //get all defects of the same property
        get("/Defects/:propertyName", "application/json", (req, res)->{
            String name = req.params(":propertyName");

            if(dao.getDefectsOfSameProperty(name) == null){
                return new ApiException(404, "There are no tenants in the available property");

            }else {
                return gson.toJson(dao.getDefectsOfSameProperty(name));
            }
        });

        //get all defects of the same tenant
        get("/tenantDefects/:tenantId", "application/json", (req, res)->{
            String id = req.params(":tenantId");

            if(dao.getDefectsByTenantId(id) == null){
                return new ApiException(404, "There are no tenants in the available property");

            }else {
                return gson.toJson(dao.getDefectsByTenantId(id));
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
