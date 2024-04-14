package com.example.Library_Management_System.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Borrowing_Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Book book;
    @ManyToOne
    Patron patron;
    LocalDateTime borrowDate;
    LocalDateTime returnDate;
}
