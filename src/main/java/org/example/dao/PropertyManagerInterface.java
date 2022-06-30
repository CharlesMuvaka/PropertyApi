package org.example.dao;

import org.example.models.PropertyManager;
import org.example.models.Tenant;

import java.util.List;

public interface PropertyManagerInterface {

    //Create
    void addPropertyManager(PropertyManager propertymanager);
    void addPropertyName(String propertyName);

    //Read
    PropertyManager getPropertyManagerById(int id);
    List<PropertyManager> getAllPropertyManagers();
    List<String> propertyManagerPropertyNames();

    //Update


    //Delete
}
