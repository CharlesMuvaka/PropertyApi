package org.example;

import org.example.data.PropertyDao;
import org.example.data.PropertyManagerDao;
import org.example.data.TenantDao;
import org.example.models.Property;
import org.example.models.PropertyManager;
import org.example.models.Tenant;

public class Main {
    public static void main(String[] args) {


        PropertyManagerDao propertyManagerDao = new PropertyManagerDao();
        TenantDao tenantDao = new TenantDao();
        PropertyDao propertyDao = new PropertyDao();
        PropertyManager manager = new PropertyManager("Charles", "0768761610", "muvaka@gmail.com","Nyambu plaza", "Based in katoyoyo");
//        propertyManagerDao.addPropertyManager(manager);
        Tenant collo = new Tenant("Collins", "collin@gmail.com", "Nyambu Plaza");
        Property house = new Property("Nyambu Plaza", "Collins");
//        tenantDao.addTenant(collo);
//        propertyDao.addProperty(house);

        System.out.println(propertyManagerDao.getPropertyManagerById(1));
        System.out.println(tenantDao.getAllTenants());
//        System.out.println(propertyDao.getAllProperties());
        System.out.println(propertyManagerDao.propertyManagerProperties(house.manager_name));

        

    }
}
