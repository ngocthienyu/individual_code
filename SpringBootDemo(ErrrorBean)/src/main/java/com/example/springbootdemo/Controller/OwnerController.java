package com.example.springbootdemo.Controller;

import com.example.springbootdemo.Entity.Owner;
import com.example.springbootdemo.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    OwnerRepository ownerRepository;

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getAllOwners()
    {
        List<Owner> listOwners = new ArrayList<>();
        ownerRepository.findAll().forEach(listOwners :: add);

        if(listOwners.isEmpty())
        {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(listOwners, HttpStatus.OK);
    }

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getOwnersContaining(@RequestParam String name )
    {
        List<Owner> listOwnersByName = new ArrayList<>();
        ownerRepository.findBynameContaining(name).forEach(listOwnersByName::add);
        if(listOwnersByName.isEmpty())
        {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(listOwnersByName, HttpStatus.OK);
    }


    @PostMapping("/owner")
    public ResponseEntity<Owner> createAnOwner(@RequestBody Owner owner)
    {
        try {
            Owner ownerTmp = ownerRepository.save(new Owner(owner.getName(), owner.getBirthdate(), owner.getAddress(), owner.getTelephone(), owner.getPets()));
            return new ResponseEntity<>(ownerTmp, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/owner/{id}")
    public ResponseEntity<Owner> updateAnOwner(@PathVariable long id, @RequestBody Owner owner)
    {
        Optional<Owner> ownerByID = ownerRepository.findById(id);
        if(ownerByID.isPresent())
        {
            Owner ownerTmp = ownerByID.get();
            ownerTmp.setName(owner.getName());
            ownerTmp.setBirthdate(owner.getBirthdate());
            ownerTmp.setAddress(owner.getAddress());
            ownerTmp.setTelephone(owner.getTelephone());
            ownerTmp.setPets(owner.getPets());
            return new ResponseEntity<>(ownerRepository.save(ownerTmp), HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/owners")
    public ResponseEntity<Owner> deleteAll()
    {
        try {
            ownerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/owner/{id}")
    public ResponseEntity<Owner> deleteAnOwner(@PathVariable long id)
    {
        Optional<Owner> ownerByID = ownerRepository.findById(id);
        if(ownerByID.isPresent())
        {
            ownerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
