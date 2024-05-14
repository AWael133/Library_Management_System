package com.example.Library_Management_System.Repository;

import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Entity.Borrowing_Record;
import com.example.Library_Management_System.Entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<Borrowing_Record, Long> {
    Optional<Borrowing_Record> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);

    boolean existsByBookAndReturnDateIsNull(Book book);
}
