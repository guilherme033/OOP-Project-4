package com.example.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import com.example.demo.dto.EventInsertDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TABLE_EVENTS")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "STARTDATE")
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "ENDDATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "STARTTIME")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @Column(name = "ENDTIME")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @Column(name = "EMAILCONTACT")
    private String emailContact;

    @Column(name = "AMOUNTFREETICKETS")
    private Long amountFreeTickets;

    @Column(name = "AMOUNTPAYEDTICKETS")
    private Long amountPayedTickets;

    @Column(name = "PRICETICKET")
    private Double priceTicket;

    @Column(name = "ADMIN_USER_ID")
    private Long admin;

    @ManyToMany
    @JoinTable(
        name                =   "TB_EVENTS_PLACES",
        joinColumns         =   @JoinColumn(name = "EVENT_ID"),
        inverseJoinColumns  =   @JoinColumn(name = "PLACE_ID")
    )
    private List<Place> places = new ArrayList<>() ;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets = new ArrayList<>();

    public Event(){
    }

    public Event(EventInsertDTO dto){

        this.name               =   dto.getName();
        this.description        =   dto.getDescription();
        this.startDate          =   dto.getStartDate();
        this.endDate            =   dto.getEndDate();
        this.startTime          =   dto.getStartTime();
        this.endTime            =   dto.getEndTime();
        this.emailContact       =   dto.getEmailContact();
        this.amountFreeTickets  =   dto.getAmountFreeTickets();
        this.amountPayedTickets =   dto.getAmountPayedTickets();
        this.priceTicket        =   dto.getPriceTicket();
        this.admin              =   dto.getAdmin();
    }

    public Boolean hasTicket(String type){
        long count = 0;

        for(Ticket tickets : this.getTickets()){
            if(tickets.getType().equals(type)){
                count++;
            }
        }

        if(type.equals("FREE_TICKET!")){
            if(count < this.getAmountFreeTickets()){
                return true;
            }
        }
        else{
            if(count < this.getAmountPayedTickets()){
                return true;
            }
        }

        return false;
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

    public Long getAdmin() {
        return admin;
    }

    public void setAdmin(Long admin) {
        this.admin = admin;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void addPlace(Place place) {
        this.places.add(place);
    }

    public void removePlace(Place place){
        this.places.remove(place);
    }
    
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket){
        this.tickets.remove(ticket);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
            return true;
    }
}
