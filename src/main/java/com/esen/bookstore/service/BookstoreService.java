package com.esen.bookstore.service;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.repository.BookstoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookstoreService {

    private final BookstoreRepository bookstoreRepository;


    @Transactional
    public void removeBookFromInventories(Book book) {
        bookstoreRepository.findAll()
                .forEach(bookstore -> {
                    bookstore.getInventory().remove(book);
                    bookstoreRepository.save(bookstore);
                });
    }

    public List<Bookstore> findAll(){
        return bookstoreRepository.findAll();
    }

    public void save(Bookstore bookstore) {
        bookstoreRepository.save(bookstore);
    }

    public void deleteBookstore(Long id) {
        var bookstore = bookstoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find bookstore"));
    }
}