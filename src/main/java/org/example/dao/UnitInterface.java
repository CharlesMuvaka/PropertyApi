package org.example.dao;

import org.example.models.Unit;

import java.util.List;

public interface UnitInterface {

    //CREATE
    void addUnit(Unit unit);

    //Read
    Unit getUnitById(int id);
    List<Unit> getAllUnits();
    List<Unit> getAllUnitsInSameProperty(String name);

    //Update
    void updateUnit(int id, Unit unit);

    //Delete
    void deleteUnit(int id);
}
