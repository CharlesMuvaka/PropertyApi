package org.example.data;

import org.example.dao.PropertyInterface;
import org.example.models.Property;
import org.example.models.PropertyManager;
import org.sql2o.Connection;

import java.util.List;

public class PropertyDao implements PropertyInterface {
    @Override
    public void addProperty(Property property) {
        String query = "INSERT INTO property(property_name,manager_name) VALUES(:property_name,:manager_name)";
        try(Connection conn = DB.sql20.open()){
            property.id = (int) conn.createQuery(query)
                    .addParameter("property_name", property.property_name)
                    .addParameter("manager_name", property.manager_name)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public Property getPropertyById(int id) {
        String query = "SELECT * FROM property WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Property.class);
        }

    }




}
