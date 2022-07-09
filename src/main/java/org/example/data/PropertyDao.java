package org.example.data;

import org.example.dao.PropertyInterface;
import org.example.models.Property;
import org.sql2o.Connection;

import java.util.List;

public class PropertyDao implements PropertyInterface {
    @Override
    public void addProperty(Property property) {
        String query = "INSERT INTO property(property_name, property_location,manager_name) VALUES(:property_name, :property_location,:manager_name)";
        try(Connection conn = DB.sql20.open()){
            int id = (int) conn.createQuery(query,true)
                    .addParameter("property_name", property.getProperty_name())
                    .addParameter("property_location", property.getProperty_location())
                    .addParameter("manager_name", property.getManager_name())
                    .executeUpdate()
                    .getKey();
            property.setId(id);
        }
    }

    @Override
    public Property getPropertyById(int id) {
        String query = "SELECT * FROM property WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("id",id).throwOnMappingFailure(false).executeAndFetchFirst(Property.class);
        }

    }

    @Override
    public List<Property> getAllProperties() {
        String query = " SELECT * FROM property";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).throwOnMappingFailure(false).executeAndFetch(Property.class);
        }

    }

    @Override
    public void updateProperty(int id, Property property) {
        String query = "UPDATE property SET property_name = :property_name, property_location = :property_location, manager_name = :manager_name WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            conn.createQuery(query)
                    .addParameter("id", id)
                    .addParameter("property_name", property.getProperty_name())
                    .addParameter("property_location", property.getProperty_location())
                    .addParameter("manager_name", property.getManager_name())
                    .executeUpdate();
        }

    }

    @Override
    public void deleteProperty(int id) {
        String query = "DELETE FROM property WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            conn.createQuery(query)
                    .addParameter("id", id)
                    .executeUpdate();
        }

    }


}
