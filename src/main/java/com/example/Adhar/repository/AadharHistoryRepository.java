package com.example.Adhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Adhar.modal.AadharHistory;

//import com.moe.sdmis.fileservice.modal.StudentBasicProfileTmp;

public interface AadharHistoryRepository extends JpaRepository<AadharHistory, Long>{

}
