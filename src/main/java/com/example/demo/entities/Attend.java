package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.dto.AttendInsertDTO;

@Entity
@Table(name = "TABLE_ATTENDEES")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Attend extends BaseUSER {

    private Double balance;

    @OneToMany
    @JoinColumn(name = "ATTEND_USER_ID")
    private List<Ticket> tickets = new ArrayList<>();

    public Attend() {
	}

    public Attend(AttendInsertDTO dto) {
        this.setName(dto.getName());
        this.setEmail(dto.getEmail());
        this.setBalance(dto.getBalance()); 
	}

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
    
}
