package org.example.dao;

import org.example.models.MaintenanceQuest;
import org.example.models.PropertyManager;

import java.util.List;

public interface MaintenanceQuestInterface {

    //Create
    void addMaintenanceQuest(PropertyManager propertymanager);
    void addPropertyName(String propertyName);

    //Read
    MaintenanceQuest getMaintenanceQuestById(int id);
    List<PropertyManager> getAllMaintenanceQuests();
    List<String> MaintenanceQuestPropertyNames();

    //Update


    //Delete
}
