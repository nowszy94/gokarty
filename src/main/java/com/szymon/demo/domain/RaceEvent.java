package com.szymon.demo.domain;

import java.time.LocalDateTime;

public class RaceEvent {
    private final String name;
    private final String address;
    private final LocalDateTime date;


    public RaceEvent(String name, String address, LocalDateTime date) {
        this.name = name;
        this.address = address;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
