package com.spotify.controllers;

import com.spotify.services.Table1Service;
import com.spotify.dbMapping.Table1Entity;
import com.spotify.dbMapping.Table1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DBController {

    @Autowired
    Table1Repository table1Repository;
    @Autowired
    Table1Service table1Service;

    @GetMapping("/sayhello")   //Define endpoint
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/db/check")   //Define endpoint
    public Optional<Table1Entity> check() {
        return table1Service.findById(1L);
    }

    @GetMapping("/db/getData")   //Define endpoint
    public List<Table1Entity> getData() {
        return table1Service.findAllPersons();
    }

    @GetMapping("/db/{id}")
    public Optional<Table1Entity> findById(@PathVariable("id") Long id) {
        return table1Service.findById(id);
    }

    @RequestMapping(value = "/db/savePerson", produces = "application/json", method = {RequestMethod.POST, RequestMethod.GET})
    public Table1Entity savePerson(@RequestBody Table1Entity table1Entity) {
        return table1Service.savePerson(table1Entity);
    }

    @PutMapping("/db/updatePerson")
    public Table1Entity updatePerson(@RequestBody Table1Entity table1Entity) {
        return table1Service.updatePerson(table1Entity);
    }

    @DeleteMapping("/db/deletePerson/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        table1Service.deletePerson(id);
    }
}
