package com.spotify.services;

import com.spotify.dbMapping.Table1Entity;

import java.util.List;
import java.util.Optional;

public interface PersonsInterfaceService {
    List<Table1Entity> findAllPersons();
    Optional<Table1Entity> findById(Long id);
    Table1Entity savePerson(Table1Entity table1Entity);
    Table1Entity updatePerson(Table1Entity table1Entity);
    void deletePerson(Long id);
}
