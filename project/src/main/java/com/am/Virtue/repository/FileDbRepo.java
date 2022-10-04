package com.am.Virtue.repository;

import com.am.Virtue.entities.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDbRepo extends JpaRepository<FileDb, String> {

}