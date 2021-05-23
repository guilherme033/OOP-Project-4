package com.example.demo.service;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import com.example.demo.dto.EventDTO;
import com.example.demo.dto.EventInsertDTO;
import com.example.demo.dto.EventUpdateDTO;
import com.example.demo.entities.Event;
import com.example.demo.repositories.EventRepository;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }  
    }
    
}
