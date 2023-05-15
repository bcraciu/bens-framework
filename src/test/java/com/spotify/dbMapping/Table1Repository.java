package com.spotify.dbMapping;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Table1Repository extends CrudRepository<Table1Entity, Long> {
    @Transactional
    @Modifying
    @Query("update Table1Entity t set t.fullName = ?1")
    void updateFullNameBy(String fullName);
    @Transactional
    @Modifying
    @Query("update Table1Entity t set t.id = ?1")
    int updateIdBy(Table1Entity id);

//    @Transactional
//    @Modifying
//    @Query("delete Table1Entity t set t.fullName = ?1")
//    void deleteRecord();
}
