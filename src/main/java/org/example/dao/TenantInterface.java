package org.example.dao;

import org.example.models.Tenant;

import java.util.List;

public interface TenantInterface {

    //Create
    void addTenant(Tenant tenant);

    //Read
    Tenant getTenantById(int id);
    List<Tenant> getAllTenants();
    List<Tenant> getTenantsInAProperty(String name);
    List<Tenant> getTenantsWIthSameManager(String name);

    //Update
    void updateTenant(int id, Tenant tenant);


    //Delete
    void delete(int id);
}
