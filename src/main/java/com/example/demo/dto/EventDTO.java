package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Event;
import com.example.demo.entities.Place;

public class EventDTO {

    private Long        id;
    private String      name;
    private String      description;
    private LocalDate   startDate;
    private LocalDate   endDate;
    private LocalTime   startTime;
    private LocalTime   endTime;
    private String      emailContact;
    private Long        amountFreeTickets;
    private Long        amountPayedTickets;
    private Double      priceTicket;
    private Admin       admin;
    private List<Place> places;
 
    public EventDTO(Long id, String name, String description, LocalDate startDate, LocalDate endDate,
            LocalTime startTime, LocalTime endTime, String emailContact, Long amountFreeTickets,
            Long amountPayedTickets, Double priceTicket, Admin admin, List<Place> places) {
        this.id                 =   id;
        this.name               =   name;
        this.description        =   description;
        this.startDate          =   startDate;
        this.endDate            =   endDate;
        this.startTime          =   startTime;
        this.endTime            =   endTime;
        this.emailContact       =   emailContact;
        this.amountFreeTickets  =   amountFreeTickets;
        this.amountPayedTickets =   amountPayedTickets;
        this.priceTicket        =   priceTicket;
        this.admin              =   admin;
        this.places             =   places;
    }
    public EventDTO(Event event){
        this.id                 =    event.getId();
        this.name               =    event.getName();
        this.description        =    event.getDescription();
        this.startDate          =    event.getStartDate();
        this.endDate            =    event.getEndDate();
        this.startTime          =    event.getStartTime();
        this.endTime            =    event.getEndTime();
        this.emailContact       =    event.getEmailContact();
        this.amountFreeTickets  =    event.getAmountFreeTickets();
        this.amountPayedTickets =    event.getAmountPayedTickets();
        this.priceTicket        =    event.getPriceTicket();
        this.admin              =    event.getAdmin();
        this.places             =    event.getPlaces();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public Long getAmountFreeTickets() {
        return amountFreeTickets;
    }

    public void setAmountFreeTickets(Long amountFreeTickets) {
        this.amountFreeTickets = amountFreeTickets;
    }

    public Long getAmountPayedTickets() {
        return amountPayedTickets;
    }

    public void setAmountPayedTickets(Long amountPayedTickets) {
        this.amountPayedTickets = amountPayedTickets;
    }

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
