package com.example.Adhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.example.Adhar.modal.AadharHistory;
import com.example.Adhar.modal.AadharTxnHistory;

@Repository
public interface AadharTxnHistoryRepository extends JpaRepository<AadharTxnHistory, Long>{

}
