package org.example.data;

import org.example.dao.PropertyManagerInterface;
import org.example.models.Property;
import org.example.models.PropertyManager;
import org.sql2o.Connection;

import java.util.List;

public class PropertyManagerDao implements PropertyManagerInterface {

    @Override
    public void addPropertyManager(PropertyManager propertymanager) {
        String query = "INSERT INTO property_managers(manager_name,phone_number,email,property_name,property_description) VALUES(:manager_name,:phone_number,:email,:property_name,:property_description)";
        try(Connection connection = DB.sql20.open()){
             propertymanager.id = (int) connection.createQuery(query,true)
                    .addParameter("manager_name", propertymanager.manager_name)
                    .addParameter("phone_number", propertymanager.phone_number)
                    .addParameter("email", propertymanager.email)
                    .addParameter("property_name", propertymanager.property_name)
                    .addParameter("property_description", propertymanager.property_description)
                    .executeUpdate()
                    .getKey();
        }

    }
    @Override
    public PropertyManager getPropertyManagerById(int id) {
        String query = "SELECT * FROM property_managers WHERE id = :id";
        try(Connection conn = DB.sql20.open()){

            return conn.createQuery(query).addParameter("id", id).executeAndFetchFirst(PropertyManager.class);
        }

    }
    @Override
    public List<PropertyManager> getAllPropertyManagers() {
        String query = "SELECT * FROM property_managers";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).executeAndFetch(PropertyManager.class);
        }

    }

    @Override
    public List<Property> propertyManagerProperties(String managerName) {
        String query = "SELECT * FROM property WHERE manager_name = :managerName";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("managerName", managerName).executeAndFetch(Property.class);
        }

    }

    @Override
    public void update(int id, PropertyManager manager) {
        String query  = "UPDATE property_managers SET manager_name = :manager_name, phone_number = :phone_number, email = :email, ";
    }

    @Override
    public void deleteById(int id) {

    }


}
