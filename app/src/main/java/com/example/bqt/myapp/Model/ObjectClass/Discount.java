package com.example.bqt.myapp.Model.ObjectClass;

/**
 * Created by BQT on 6/12/2017.
 */

public class Discount {
    int ID;
    String Name;
    String Description;
    int Percent;
    String Status;
    String Code;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPercent() {
        return Percent;
    }

    public void setPercent(int percent) {
        Percent = percent;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
