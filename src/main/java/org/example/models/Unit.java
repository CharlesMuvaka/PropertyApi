package org.example.models;

import java.util.Objects;

public class Unit {


    private int id, property_id;
    private String unit_name,unit_rooms;

    public Unit(String unitName, int property_name, String unit_rooms) {
        this.unit_name = unitName;
        this.property_id = property_name;
        this.unit_rooms = unit_rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return getProperty_id() == unit.getProperty_id() && Objects.equals(getUnit_name(), unit.getUnit_name()) && Objects.equals(getUnit_rooms(), unit.getUnit_rooms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProperty_id(), getUnit_name(), getUnit_rooms());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitName() {
        return unit_name;
    }

    public void setUnitName(String unitName) {
        this.unit_name = unitName;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getUnit_rooms() {
        return unit_rooms;
    }

    public void setUnit_rooms(String unit_rooms) {
        this.unit_rooms = unit_rooms;
    }
}
