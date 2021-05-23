package com.example.demo.service;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import com.example.demo.dto.AdminDTO;
import com.example.demo.dto.AdminInsertDTO;
import com.example.demo.dto.AdminUpdateDTO;
import com.example.demo.entities.Admin;
import com.example.demo.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public  Page<AdminDTO> getAdmins(PageRequest pageRequest, String name) {
            Page<Admin> list = adminRepository.find(pageRequest, name);
            return list.map( a -> new AdminDTO(a) );
    }

    public  AdminDTO getAdminById(Long id) {
            Optional<Admin> op = adminRepository.findById(id);
            Admin administrator = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrator not found!"));
            return new AdminDTO(administrator);
    }

    public AdminDTO insert(AdminInsertDTO dto){
         if(dto.getName().isEmpty() || dto.getEmail().isEmpty() || dto.getPhonenumber().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fill in the items!");
        }
        Admin entity = new Admin(dto);
        entity = adminRepository.save(entity);
        return new AdminDTO(entity);
    }

    public void delete(Long id){
        try{
            adminRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrator not found!");
        }
    }

    public AdminDTO update(Long id, AdminUpdateDTO dto){
        try{
            if(dto.getEmail().isEmpty() || dto.getPhoneNumber().isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fill in the items!");
            }

            Admin entity = adminRepository.getOne(id);
            entity.setEmail(dto.getEmail());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity = adminRepository.save(entity);
            return new AdminDTO(entity);
        }
        catch(EntityNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrator not found!");
        }  
    }
    
}
