package org.example.dao;

import org.example.models.Property;
import org.example.models.PropertyManager;
import org.example.models.Tenant;

import java.util.List;

public interface PropertyManagerInterface {

    //Create
    void addPropertyManager(PropertyManager propertymanager);

    //Read
    PropertyManager getPropertyManagerById(int id);
    List<PropertyManager> getAllPropertyManagers();
    List<Property> propertyManagerProperties(String name);

    //Update
    void update(int id, PropertyManager manager);

    //Delete
    void deleteById(int id);
}
