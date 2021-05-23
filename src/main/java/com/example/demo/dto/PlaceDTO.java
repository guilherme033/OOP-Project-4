package com.example.demo.dto;

import java.util.List;

import com.example.demo.entities.Event;
import com.example.demo.entities.Place;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlaceDTO {
    private Long id;
    private String name;
    private String address;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonIgnore
    private List <Event> events;

    public PlaceDTO(Long id, String name, String address, List<Event> events){
        this.id      = id;
        this.name    = name;
        this.address = address;
        this.events  = events;
    }

    public PlaceDTO(Place place){
        this.id      =    place.getId();
        this.name    =    place.getName();
        this.address =    place.getAddress();
        this.events  =    place.getEvents();
    }
    
}
