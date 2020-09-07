package com.szymon.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private String firstname;
    private String lastname;
    private Integer kartNo;
    private List<String> times;

    public Driver(String firstname, String lastname, Integer kartNo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.kartNo = kartNo;
        this.times = new ArrayList<>();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", kartNo=" + kartNo +
                '}';
    }
}
