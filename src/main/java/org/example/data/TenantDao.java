package org.example.data;

import org.example.dao.TenantInterface;
import org.example.models.Tenant;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class TenantDao implements TenantInterface {
    @Override
    public void addTenant(Tenant tenant) {
        String query = "INSERT INTO tenant(tenant_name, tenant_email,tenant_phone,tenant_id, property_name,unit_name,manager_name, joined) VALUES(:tenant_name,:tenant_email,:tenant_phone,:tenant_id,:property_name,:unit_name,:manager_name now())";
        try(Connection conn = DB.sql20.open()){
            int id = (int) conn.createQuery(query,true)
                    .addParameter("tenant_name", tenant.getTenant_name())
                    .addParameter("tenant_email", tenant.getTenant_email())
                    .addParameter("tenant_phone", tenant.getTenant_phone())
                    .addParameter("tenant_id", tenant.getTenant_id())
                    .addParameter("property_name", tenant.getProperty_name())
                    .addParameter("unit_name", tenant.getUnit_name())
                    .addParameter("manager_name", tenant.getManager_name())
                    .executeUpdate()
                    .getKey();
            tenant.setId(id);
        }
    }

    @Override
    public Tenant getTenantById(int id) {
        String query = "SELECT * FROM tenant WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Tenant.class);
        }

    }

    @Override
    public List<Tenant> getAllTenants() {
        String query = "SELECT * FROM tenant";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).executeAndFetch(Tenant.class);
        }catch (Sql2oException ex){
            System.out.println(ex.fillInStackTrace().getMessage());
        }
        return null;
    }

    @Override
    public List<Tenant> getTenantsInAProperty(String name) {
        String query  = "SELECT * FROM tenant WHERE property_name = :name";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("name", name).throwOnMappingFailure(false).executeAndFetch(Tenant.class);
        }

    }

    @Override
    public List<Tenant> getTenantsWIthSameManager(String name) {
        String query  = "SELECT * FROM tenant WHERE manager_name = :name";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("name", name).throwOnMappingFailure(false).executeAndFetch(Tenant.class);
        }
    }

    @Override
    public void updateTenant(int id,Tenant tenant) {
        String query = "UPDATE tenant SET tenant_name = :tenant_name, tenant_email = :tenant_email, tenant_phone = :tenant_phone, tenant_id = :tenant_id, property_name = :property_name, unit_name= :unit_name, manager_name = :manager_name WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            conn.createQuery(query)
                    .addParameter("id", id)
                    .addParameter("tenant_name",tenant.getTenant_name() )
                    .addParameter("tenant_email", tenant.getTenant_email())
                    .addParameter("tenant_phone", tenant.getTenant_phone())
                    .addParameter("tenant_id", tenant.getTenant_id())
                    .addParameter("property_name", tenant.getProperty_name())
                    .addParameter("unit_name", tenant.getUnit_name())
                    .addParameter("manager_name", tenant.getManager_name())
                    .executeUpdate();
        }

    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM tenant WHERE id = :id";

        try(Connection conn = DB.sql20.open()){
            conn.createQuery(query)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }


}
