package com.thereadingroom.model.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_sequence", allocationSize = 1)
    private Long id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String description;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Column
    private int stock;
    @Column(name = "cover_image_url")
    private String coverImageUrl;
}
