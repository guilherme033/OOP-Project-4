package com.example.demo.dto;

import java.util.List;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Event;

public class AdminDTO {

    private Long        id;
    private String      name;
    private String      email;
    private String      phoneNumber;
    private List<Event> events;

    public AdminDTO(Long id, String name, String email, String phoneNumber, List<Event> events){
        this.id          =  id;
        this.name        =  name;
        this.email       =  email;
        this.phoneNumber =  phoneNumber;
        this.events      =  events;
    }

    public AdminDTO(Admin administrator){
        this.id          =   administrator.getId();
        this.name        =   administrator.getName();
        this.email       =   administrator.getEmail();
        this.phoneNumber =   administrator.getPhoneNumber();
        this.events      =   administrator.getEvents();
    }

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    
}
