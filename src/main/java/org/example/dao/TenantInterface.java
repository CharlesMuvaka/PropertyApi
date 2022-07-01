package org.example.dao;

import org.example.models.Tenant;

import java.util.List;

public interface TenantInterface {

    //Create
    void addTenant(Tenant tenant);

    //Read
    Tenant getTenantById(int id);
    List<Tenant> getAllTenants();
    List<Tenant> getTenantsInAProperty(String propertyNAme);

    //Update
    void updateTenant(Tenant tenant);


    //Delete
}
