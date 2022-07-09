package org.example.data;

import org.example.dao.UnitInterface;
import org.example.models.Unit;
import org.sql2o.Connection;

import java.util.List;

public class UnitDao implements UnitInterface {

    @Override
    public void addUnit(Unit unit) {
        String query = "INSERT INTO units(unit_name, property_id, unit_rooms) VALUES(:unit_name,:property_id,:unit_rooms)";
        try(Connection conn = DB.sql20.open()){
            int id = (int) conn.createQuery(query,true)
                    .addParameter("unit_name", unit.getUnitName())
                    .addParameter("property_id", unit.getProperty_id())
                    .addParameter("unit_rooms", unit.getUnit_rooms())
                    .executeUpdate()
                    .getKey();
            unit.setId(id);
        }
    }

    @Override
    public Unit getUnitById(int id) {
        String query = "SELECT * FROM units WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).throwOnMappingFailure(false).addParameter("id",id).executeAndFetchFirst(Unit.class);
        }

    }

    @Override
    public List<Unit> getAllUnits() {
        String query = "SELECT * FROM units";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).throwOnMappingFailure(false).executeAndFetch(Unit.class);
        }

    }

    @Override
    public List<Unit> getAllUnitsInSameProperty(int id) {
        String query = "SELECT * FROM units WHERE property_id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).throwOnMappingFailure(false).addParameter("id", id).executeAndFetch(Unit.class);
        }

    }

    @Override
    public void updateUnit(int id, Unit unit) {
        String query = "UPDATE units SET unit_name = :unit_name, property_id = :property_id, unit_rooms = :unit_rooms WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            conn.createQuery(query)
                    .addParameter("unitName", unit.getUnitName())
                    .addParameter("property_id", unit.getProperty_id())
                    .addParameter("unit_rooms", unit.getUnit_rooms())
                    .addParameter("id", id)
                    .executeUpdate();
        }

    }

    @Override
    public void deleteUnit(int id) {
        String query = "DELETE FROM units WHERE id = :id ";
        try(Connection conn = DB.sql20.open()){
            conn.createQuery(query).addParameter("id", id).executeUpdate();
        }

    }
}
