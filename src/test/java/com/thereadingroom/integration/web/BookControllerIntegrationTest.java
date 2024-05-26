package com.thereadingroom.integration.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thereadingroom.integration.IntegrationTestInit;
import com.thereadingroom.model.db.BookEntity;
import com.thereadingroom.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class BookControllerIntegrationTest extends IntegrationTestInit {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp() {
        cleanDatabase();
    }

    @Test
    public void testAddBook() throws Exception {
        insertDatabase("save_book.sql");

        BookEntity book = new BookEntity();
        book.setAuthor("Kent Beck");
        book.setTitle("Test-Driven Development By Example");
        book.setDescription("Clean code that works");
        book.setPrice(BigDecimal.valueOf(10.99));
        book.setStock(10);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());

        List<BookEntity> all = bookRepository.findAll();

        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    public void testAddBook2() throws Exception {
        BookEntity expected = new BookEntity();
        expected.setAuthor("Kent Beck");
        expected.setTitle("Test-Driven Development By Example");
        expected.setDescription("Clean code that works");
        expected.setPrice(BigDecimal.valueOf(10.99));
        expected.setStock(10);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)))
                .andExpect(status().isCreated());

        BookEntity actual = bookRepository.findById(1L).get();

        assertThat(actual.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(actual.getTitle()).isEqualTo(expected.getTitle());
        assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        assertThat(actual.getPrice()).isEqualTo(expected.getPrice());
        assertThat(actual.getStock()).isEqualTo(expected.getStock());
    }
}
