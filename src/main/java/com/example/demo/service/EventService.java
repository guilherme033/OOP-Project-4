package com.example.demo.service;

import java.time.Instant;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import com.example.demo.dto.AttendticketDTO;
import com.example.demo.dto.EventDTO;
import com.example.demo.dto.EventInsertDTO;
import com.example.demo.dto.EventUpdateDTO;
import com.example.demo.entities.Attend;
import com.example.demo.entities.Event;
import com.example.demo.entities.Place;
import com.example.demo.entities.Ticket;
import com.example.demo.entities.Visualization;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.AttendRepository;
import com.example.demo.repositories.EventRepository;
import com.example.demo.repositories.PlaceRepository;
import com.example.demo.repositories.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AttendRepository attendRepository;

    @Autowired
    private TicketRepository ticketRepository;



    public  Page<EventDTO> getEvents(PageRequest pageRequest, String name, String description) {
            Page<Event> list = eventRepository.find(pageRequest, name, description);
            return list.map( e -> new EventDTO(e));
    }

    public  EventDTO getEventById(Long id) {
            Optional<Event> op = eventRepository.findById(id);
            Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));
            return new EventDTO(event);
    }

    public EventDTO insert(EventInsertDTO dto){

        if(dto.getName().isEmpty() || dto.getDescription().isEmpty() || dto.getEmailContact().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fill in the items!");
        }

        if(dto.getAmountFreeTickets() < 0 || dto.getAmountPayedTickets() < 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The number of tickets must be greater than or equal to ZERO!");
        }

        if(dto.getPriceTicket() < 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket price's must be greater than or equal to ZERO!");
        }

        if((dto.getStartDate().isAfter(dto.getEndDate()))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "End date must not coincide with start date!");
        }

        if(dto.getStartTime().isAfter(dto.getEndTime()) && dto.getStartDate().isEqual(dto.getEndDate())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The end time must not coincide with the start time!");
        }

        Event entity = new Event(dto);
        entity = eventRepository.save(entity);
        return new EventDTO(entity);
    }

    public void delete(Long id){
        try{
            eventRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!");
        }
    }

    public EventDTO update(Long id, EventUpdateDTO dto){
        try{
            if(dto.getDescription().isEmpty() || dto.getEmailContact().isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fill in the items!");
            }
    
            if(dto.getAmountFreeTickets() < 0 || dto.getAmountPayedTickets() < 0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket numbers must be greater than or equal to ZERO!");
            }
    
            if(dto.getPriceTicket() < 0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tickets price'S must be greater than or equal to ZERO!");
            }
    
            if(dto.getStartDate().isAfter(dto.getEndDate())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "End date must not coincide with start date!");
            }

            if(dto.getStartTime().isAfter(dto.getEndTime()) && dto.getStartDate().isEqual(dto.getEndDate())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "End date must not coincide with start date!");
            }

            Event entity = eventRepository.getOne(id);
            entity.setDescription(dto.getDescription());
            entity.setStartDate(dto.getStartDate());
            entity.setEndDate(dto.getEndDate());
            entity.setStartTime(dto.getStartTime());
            entity.setEndTime(dto.getEndTime());
            entity.setEmailContact(dto.getEmailContact());
            entity.setAmountFreeTickets(dto.getAmountFreeTickets());
            entity.setAmountPayedTickets(dto.getAmountPayedTickets());
            entity.setPriceTicket(dto.getPriceTicket());
            entity = eventRepository.save(entity);
            return new EventDTO(entity);
        }
        catch(EntityNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!");
        }  
    }

    public EventDTO addPlace(Long id, Long place) {
        Optional<Event> op = eventRepository.findById(id);
        Event eve = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));

        Optional<Place> op2 = placeRepository.findById(place);
        Place plc = op2.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found!"));

        eve.addPlace(plc);
        eve = eventRepository.save(eve);
        return new EventDTO(eve);
    }

    public void removePlace(Long id, Long place){
        try{
            Optional<Event> op = eventRepository.findById(id);
            Event eve = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));

            Optional<Place> op2 = placeRepository.findById(place);
            Place plc = op2.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found!"));

            for(Place event_place : eve.getPlaces()){
                if(event_place.getId() == plc.getId()){
                    eve.removePlace(plc);
                    eve = eventRepository.save(eve);
                    return;
                }
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "- Event not associated with this place! -");      
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "- 404! -");
        }
    }

    public Visualization getTickets(Long id){
        Optional<Event> op = eventRepository.findById(id);
        Event eve = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));

        Visualization view = new Visualization();

        view.setTotalfreetickets(eve.getAmountFreeTickets());
        view.setTotalpaidtickets(eve.getAmountPayedTickets());

        for(Ticket tickets : eve.getTickets()){        
            if( tickets.getType().equals("FREE_TICKET!")){
                view.setFreeticketoutput(view.getFreeticketoutput() + 1);
                view.addFreeticketlisting(tickets.getAttend().getName());
            }
            else{
                view.setPaidticketsoutput(view.getPaidticketsoutput() + 1);
                view.addPaidticketlisting(tickets.getAttend().getName());
            }
        }

        return view;
    }

    public Attend insertTicket(AttendticketDTO dto, Long id){
        Optional<Event> op = eventRepository.findById(id);
        Event eve = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));

        Optional<Attend> op2 = attendRepository.findById(dto.getId());
        Attend atd = op2.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found!"));

        if(!dto.getType().equals("FREE_TICKET!") && !dto.getType().equals("PAYED_TICKET!")){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "- Free or Payed! -");
        }

        if(eve.hasTicket(dto.getType()) != true){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " - Event with insufficient ticket! -" + dto.getType());
        }

        Ticket  ticket = new Ticket();
                ticket.setType(dto.getType());
                ticket.setAttend(atd);
                ticket.setEvent(eve);
                ticket.setPrice(eve.getPriceTicket());
                ticket.setDate(Instant.now());
                ticket = ticketRepository.save(ticket);

        return atd;
    }

    public void deleteTickets(Long id){
        Optional<Event> op = eventRepository.findById(id);
        Event eve = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));

        for(Ticket ticket : eve.getTickets()){
            eve.removeTicket(ticket);
            eve = eventRepository.save(eve);
        }
    }
}
