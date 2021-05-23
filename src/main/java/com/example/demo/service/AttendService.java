package com.example.demo.service;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import com.example.demo.dto.AttendDTO;
import com.example.demo.dto.AttendInsertDTO;
import com.example.demo.dto.AttendUpdateDTO;
import com.example.demo.entities.Attend;
import com.example.demo.repositories.AttendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttendService {

    @Autowired
    private AttendRepository attendRepository;

    public  Page<AttendDTO> getAttendees(PageRequest pageRequest, String name) {
            Page<Attend> list = attendRepository.find(pageRequest, name);
            return list.map( at -> new AttendDTO(at) );
    }

    public  AttendDTO getAttendById(Long id) {
            Optional<Attend> op = attendRepository.findById(id);
            Attend atd = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found!"));
            return new AttendDTO(atd);
    }

    public AttendDTO insert(AttendInsertDTO dto){

        if(dto.getName().isEmpty() || dto.getEmail().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fill in the items!");
        }
        if(dto.getBalance() < 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The attendee's balance must be equal to or greater than ZERO!");
        }

        Attend entity = new Attend(dto);
        entity = attendRepository.save(entity);
        return new AttendDTO(entity);
    }

    public void delete(Long id){
        try{
            attendRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found!");
        }
    }

    public AttendDTO update(Long id, AttendUpdateDTO dto){

        try{
            if(dto.getEmail().isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fill in the items!");
            }

            Attend entity = attendRepository.getOne(id);
            
            if(entity.getBalance() < (dto.getBalance() * -1)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The attendee has a" + entity.getBalance() + "balance!");
            }
            
            entity.setBalance((entity.getBalance() + dto.getBalance()));
            entity.setEmail(dto.getEmail());
            entity = attendRepository.save(entity);
            return new AttendDTO(entity);
        }
        catch(EntityNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found!");
        }  
    }
    
}
