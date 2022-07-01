package org.example.data;

import org.example.dao.TenantInterface;
import org.example.models.Tenant;
import org.sql2o.Connection;

import java.util.List;

public class TenantDao implements TenantInterface {
    @Override
    public void addTenant(Tenant tenant) {
        String query = "INSERT INTO tenant(tenant_name, tenant_email, property_name) VALUES(:tenant_name,:tenant_email,:property_name)";
        try(Connection conn = DB.sql20.open()){
            tenant.id = (int) conn.createQuery(query)
                    .addParameter("tenant_name", tenant.tenant_name)
                    .addParameter("tenant_email", tenant.tenant_email)
                    .addParameter("property_name", tenant.property_name)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public Tenant getTenantById(int id) {
        String query = "SELECT * FROM tenant WHERE id = :id";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("id", id).executeAndFetchFirst(Tenant.class);
        }

    }

    @Override
    public List<Tenant> getAllTenants() {
        String query = "SELECT * FROM tenant";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).executeAndFetch(Tenant.class);
        }

    }

    @Override
    public List<Tenant> getTenantsInAProperty(String propertyNAme) {
        String query  = "SELECT * FROM tenant WHERE property_name = :propertyName";
        try(Connection conn = DB.sql20.open()){
            return conn.createQuery(query).addParameter("propertyName", propertyNAme).executeAndFetch(Tenant.class);
        }

    }

    @Override
    public void updateTenant(Tenant tenant) {

    }







}
