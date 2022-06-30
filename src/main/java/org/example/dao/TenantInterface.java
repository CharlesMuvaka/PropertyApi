package org.example.dao;

import org.example.models.Tenant;

import java.util.List;

public interface TenantInterface {

    //Create
    void addTenant(Tenant tenant);
    void addPropertyName(String propertyName);

    //Read
    Tenant getTenantById(int id);
    List<Tenant> getAllTenants();
    List<Tenant> getTenantsInAProperty();

    //Update
    void updateTenant(Tenant tenant);


    //Delete
}
