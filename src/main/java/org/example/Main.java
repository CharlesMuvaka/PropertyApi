package org.example;

import com.google.gson.Gson;
import org.example.data.PropertyDao;
import org.example.data.PropertyManagerDao;
import org.example.data.TenantDao;
import org.example.models.Property;
import org.example.models.PropertyManager;
import org.example.models.Tenant;

import static spark.Spark.post;

public class Main {

    public static void main(String[] args) {
        PropertyManagerDao propertyManagerDao = new PropertyManagerDao();
        TenantDao tenantDao = new TenantDao();
        PropertyDao propertyDao = new PropertyDao();
        Gson gson = new Gson();


        //add a property manager
        post("/propertymanager","application/json ",(request, response) -> {
            PropertyManager propertyManager = gson.fromJson(request.body(), PropertyManager.class);
            propertyManagerDao.addPropertyManager(propertyManager);
            response.status(201);
            return gson.toJson(propertyManager);
        });


        //add a tenant
        post("/tenant","application/json ",(request, response) -> {
            Tenant tenant = gson.fromJson(request.body(), Tenant.class);
            tenantDao.addTenant(tenant);
            response.status(201);
            return gson.toJson(tenant);
        });

        //add a property
        post("/property","application/json ",(request, response) -> {
            Property property = gson.fromJson(request.body(), Property.class);
            propertyDao.addProperty(property);
            response.status(201);
            return gson.toJson(property);
        });


    }

}
