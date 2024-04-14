package com.example.Library_Management_System.Repository;

import com.example.Library_Management_System.Entity.Borrowing_Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<Borrowing_Record, Long> {
}
