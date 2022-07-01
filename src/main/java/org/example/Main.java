package org.example;

import org.example.data.PropertyManagerDao;
import org.example.models.PropertyManager;

public class Main {
    public static void main(String[] args) {
        PropertyManagerDao propertyManagerDao = new PropertyManagerDao();
        PropertyManager manager = new PropertyManager("Charles", "0768761610", "muvaka@gmail.com","Nyambu plaza", "Based in katoyoyo");
//        propertyManagerDao.addPropertyManager(manager);

        System.out.println(propertyManagerDao.getPropertyManagerById(1));
    }
}
