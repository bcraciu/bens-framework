package com.spotify.services;

import com.google.common.collect.Lists;
import com.spotify.dbMapping.Table1Entity;
import com.spotify.dbMapping.Table1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Table1Service implements PersonsInterfaceService {
    @Autowired Table1Repository table1Repository;

    public Table1Service(Table1Repository table1Repository) {
        this.table1Repository = table1Repository;
    }

    @Override
    public List<Table1Entity> findAllPersons() {
        return Lists.newArrayList(table1Repository.findAll());
    }

    @Override
    public Optional<Table1Entity> findById(Long id) {
        return table1Repository.findById(id);
    }

    @Override
    public Table1Entity savePerson(Table1Entity table1Entity) {
        return table1Repository.save(table1Entity);
    }

    @Override
    public Table1Entity updatePerson(Table1Entity table1Entity) {
        return table1Repository.save(table1Entity);
        //return table1Repository.updateIdBy(table1Entity);
    }

    @Override
    public void deletePerson(Long id) {
        table1Repository.deleteById(id);
    }
}
