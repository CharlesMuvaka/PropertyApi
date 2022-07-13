package org.example.data;

import org.example.dao.DoneDefectInterface;
import org.example.models.DoneDefect;
import org.sql2o.Connection;

import java.util.List;

public class DoneDefectDao implements DoneDefectInterface {
    @Override
    public void addDoneDefect(DoneDefect defect) {
        String query = "INSERT INTO done_defects(name,uri,contactor_name,contractor_phone, contractor_location, manager_name, tenant_id,created_at) VALUES(:name,:uri,:contactor_name,:contractor_phone, :contractor_location, :manager_name, :tenant_id, now())";
        try(Connection conn = DB.sql20.open()){
            int id = (int) conn.createQuery(query)
                    .addParameter("name", defect.getName())
                    .addParameter("uri", defect.getUri())
                    .addParameter("contactor_name", defect.getContactor_name())
                    .addParameter("contractor_phone", defect.getContractor_phone())
                    .addParameter("contractor_location", defect.getContractor_location())
                    .addParameter("manager_name", defect.getManager_name())
                    .addParameter("tenant_id,", defect.getTenant_id())
                    .executeUpdate().getKey();
            defect.setId(id);
        }
    }

    @Override
    public DoneDefect getDefectById(int id) {
        String q = "SELECT * FROM done_defects WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(q).addParameter("id",id).throwOnMappingFailure(false).executeAndFetchFirst(DoneDefect.class);
        }

    }

    @Override
    public List<DoneDefect> getAllDefects() {
        String q = "SELECT * FROM done_defects";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(q).throwOnMappingFailure(false).executeAndFetch(DoneDefect.class);
        }
    }

    @Override
    public List<DoneDefect> getTenantDoneDefects(String id) {
        String q = "SELECT * FROM done_defects WHERE tenant_id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(q).addParameter("id",id).throwOnMappingFailure(false).executeAndFetch(DoneDefect.class);
        }
    }

    @Override
    public List<DoneDefect> getManagerDoneDefects(String name) {
        String q = "SELECT * FROM done_defects WHERE manager_name = :name";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(q).addParameter("name",name).throwOnMappingFailure(false).executeAndFetch(DoneDefect.class);
        }
    }
}
