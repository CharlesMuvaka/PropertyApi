package org.example.data;

import org.example.dao.PropertyManagerInterface;
import org.example.models.Property;
import org.example.models.PropertyManager;
import org.sql2o.Connection;

import java.util.List;

public class PropertyManagerDao implements PropertyManagerInterface {

    @Override
    public void addPropertyManager(PropertyManager propertymanager) {
        String query = "INSERT INTO property_managers(manager_name,phone_number,email) VALUES(:manager_name,:phone_number,:email)";
        try(Connection connection = DB.sql20.open()){
             int id = (int) connection.createQuery(query,true)
                    .addParameter("manager_name", propertymanager.getManager_name())
                    .addParameter("phone_number", propertymanager.getPhone_number())
                    .addParameter("email", propertymanager.getEmail())
                    .executeUpdate()
                    .getKey();
             propertymanager.setId(id);
        }

    }
    @Override
    public PropertyManager getPropertyManagerById(int id) {
        String query = "SELECT * FROM property_managers WHERE id = :id";
        try(Connection conn = DB.sql20.open()){

            return conn.createQuery(query).addParameter("id", id).throwOnMappingFailure(false).executeAndFetchFirst(PropertyManager.class);
        }

    }
    @Override
    public List<PropertyManager> getAllPropertyManagers() {
        String query = "SELECT * FROM property_managers";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).throwOnMappingFailure(false).executeAndFetch(PropertyManager.class);
        }

    }

    @Override
    public List<Property> propertyManagerProperties(int id) {
        String query = "SELECT * FROM property WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("id", id).throwOnMappingFailure(false).executeAndFetch(Property.class);
        }

    }

    @Override
    public void update(int id, PropertyManager manager) {
        String query  = "UPDATE property_managers SET manager_name = :manager_name, phone_number = :phone_number, email = :email WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            conn.createQuery(query)
                    .addParameter("id", id)
                    .addParameter("manager_name", manager.getManager_name())
                    .addParameter("phone_number", manager.getPhone_number())
                    .addParameter("email", manager.getEmail())
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM property_managers WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            conn.createQuery(query)
                    .addParameter("id", id)
                    .executeUpdate();
        }

    }


}
