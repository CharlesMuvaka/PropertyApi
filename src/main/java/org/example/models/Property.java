package org.example.models;

import java.util.Objects;

public class Property {
    private int id, manager_id;
    private String property_name, property_location;

    public Property(String property_name, int manager_name, String location) {
        this.property_name = property_name;
        this.manager_id = manager_name;
        this.property_location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return getManager_id() == property.getManager_id() && Objects.equals(getProperty_name(), property.getProperty_name()) && Objects.equals(getProperty_location(), property.getProperty_location());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManager_id(), getProperty_name(), getProperty_location());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getProperty_location() {
        return property_location;
    }

    public void setProperty_location(String property_location) {
        this.property_location = property_location;
    }
}
