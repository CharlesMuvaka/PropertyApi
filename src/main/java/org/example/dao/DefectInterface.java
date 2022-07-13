package org.example.dao;

import org.example.models.Defect;
import org.example.models.MaintenanceQuest;
import org.example.models.PropertyManager;

import java.util.List;

public interface DefectInterface {

    //Create
    void addDefect(Defect defect);;

    //Read
    Defect getDefectById(int id);
    List<Defect> getDefectsOfSameManager(String managerName);
    List<Defect> getDefectsOfSameProperty(String propertyName);
    List<Defect> getAllDefects();
    List<Defect> getDefectsByTenantId(String id);

    //Update


    //Delete
}
