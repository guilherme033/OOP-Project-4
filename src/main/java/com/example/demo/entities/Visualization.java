package com.example.demo.entities;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


public class Visualization implements Serializable{

    private List<String>    freeticketlisting = new ArrayList<>();
    private List<String>    paidticketlisting = new ArrayList<>();
    private Long            totalfreetickets;
    private Long            totalpaidtickets;
    private int             freeticketoutput;
    private int             paidticketsoutput;
    
    public List<String> getFreeticketlisting() {
        return freeticketlisting;
    }
    public void setFreeticketlisting(List<String> freeticketlisting) {
        this.freeticketlisting = freeticketlisting;
    }
    public void addFreeticketlisting(String nameFreeTicket) {
        this.freeticketlisting.add(nameFreeTicket);
    }
    public List<String> getPaidticketlisting() {
        return paidticketlisting;
    }
    public void setPaidticketlisting(List<String> paidticketlisting) {
        this.paidticketlisting = paidticketlisting;
    }
    public void addPaidticketlisting(String namePayedTicket) {
        this.paidticketlisting.add(namePayedTicket);
    }
    public Long getTotalfreetickets() {
        return totalfreetickets;
    }
    public void setTotalfreetickets(Long totalfreetickets) {
        this.totalfreetickets = totalfreetickets;
    }
    public Long getTotalpaidtickets() {
        return totalpaidtickets;
    }
    public void setTotalpaidtickets(Long totalpaidtickets) {
        this.totalpaidtickets = totalpaidtickets;
    }
    public int getFreeticketoutput() {
        return freeticketoutput;
    }
    public void setFreeticketoutput(int freeticketoutput) {
        this.freeticketoutput = freeticketoutput;
    }
    public int getPaidticketsoutput() {
        return paidticketsoutput;
    }
    public void setPaidticketsoutput(int paidticketsoutput) {
        this.paidticketsoutput = paidticketsoutput;
    }

    
    
}
