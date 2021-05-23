package com.example.demo.service;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import com.example.demo.dto.PlaceDTO;
import com.example.demo.dto.PlaceInsertDTO;
import com.example.demo.dto.PlaceUpdateDTO;
import com.example.demo.entities.Place;
import com.example.demo.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public  Page<PlaceDTO> getPlaces(PageRequest pageRequest, String name) {
            Page<Place>list = placeRepository.find(pageRequest, name);
            return list.map( p -> new PlaceDTO(p) );
    }

    public  PlaceDTO getPlaceById(Long id) {
            Optional<Place> op = placeRepository.findById(id);
            Place place = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found!"));
            return new PlaceDTO(place);
    }

    public PlaceDTO insert(PlaceInsertDTO dto){

        if(dto.getName().isEmpty() || dto.getAddress().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fill in all fields!");
        }

        Place entity = new Place(dto);
        entity = placeRepository.save(entity);
        return new PlaceDTO(entity);
    }

    public void delete(Long id){
        try{
            placeRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found!");
        }
    }
    public PlaceDTO update(Long id, PlaceUpdateDTO dto){

        try{
            if(dto.getName().isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fill in the items!");
            }

            Place entity = placeRepository.getOne(id);
            entity.setName(dto.getName());
            entity = placeRepository.save(entity);
            return new PlaceDTO(entity);
        }
        catch(EntityNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found!");
        }  
    }
    
}
