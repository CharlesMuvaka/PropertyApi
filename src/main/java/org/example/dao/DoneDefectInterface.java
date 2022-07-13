package org.example.dao;

import org.example.models.DoneDefect;

import java.util.List;

public interface DoneDefectInterface {

    void addDoneDefect(DoneDefect defect);
    DoneDefect getDefectById(int id);
    List<DoneDefect> getAllDefects();
    List<DoneDefect> getTenantDoneDefects(String id);
    List<DoneDefect> getManagerDoneDefects(String name);
}
