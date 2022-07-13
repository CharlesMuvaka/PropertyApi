package org.example.data;

import org.example.dao.DefectInterface;
import org.example.models.Defect;
import org.sql2o.Connection;

import java.util.List;

public class DefectDao implements DefectInterface {
    @Override
    public void addDefect(Defect defect) {
        String query = "INSERT INTO defects(description,tenant_id,string_uri,unit_name,manager_name,property_name) VALUES(:description,:tenant_id,:string_uri,:unit_name,:manager_name,:property_name)";
        try(Connection conn = DB.sql20.open()){
            int id = (int) conn.createQuery(query)
                    .addParameter("description", defect.getDescription())
                    .addParameter("tenant_id", defect.getTenant_id())
                    .addParameter("string_uri", defect.getString_uri())
                    .addParameter("unit_name", defect.getUnit_name())
                    .addParameter("manager_name", defect.getManager_name())
                    .addParameter("property_name", defect.getProperty_name())
                    .executeUpdate()
                    .getKey();
            defect.setId(id);
        }
    }

    @Override
    public Defect getDefectById(int id) {
        String query = "SELECT * FROM defects WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("id",id).throwOnMappingFailure(false).executeAndFetchFirst(Defect.class);
        }

    }

    @Override
    public List<Defect> getDefectsOfSameManager(String managerName) {
        String query = "SELECT * FROM defects WHERE manager_name = :managerName";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("managerName", managerName).throwOnMappingFailure(false).executeAndFetch(Defect.class);
        }

    }

    @Override
    public List<Defect> getDefectsOfSameProperty(String propertyName) {
        String query = "SELECT * FROM defects WHERE property_name = :propertyName";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("propertyName", propertyName).throwOnMappingFailure(false).executeAndFetch(Defect.class);
        }

    }

    @Override
    public List<Defect> getAllDefects() {
        String query = "SELECT * FROM defects";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).throwOnMappingFailure(false).executeAndFetch(Defect.class);
        }
    }

    @Override
    public List<Defect> getDefectsByTenantId(String id) {
        String query = "SELECT * FROM defects WHERE tenant_id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("id", id).throwOnMappingFailure(false).executeAndFetch(Defect.class);
        }
    }
}
