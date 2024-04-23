package com.esen.bookstore.shell;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.service.BookstoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@ShellComponent
@ShellCommandGroup("Bookstore related commands")
@RequiredArgsConstructor
public class BookstoreHandler {

    private final BookstoreService bookstoreService;

    @ShellMethod(value = "Create a book", key = "create a book")
    public void createBookstore(String location, Double priceModifier, Double moneyInCashRegister){
        bookstoreService.save(Bookstore.builder()
                .location(location)
                .priceModifier(priceModifier)
                .moneyInCashRegister(moneyInCashRegister)
                .build());
    }

    @ShellMethod(value = "List bookstores", key = "list bookstores")
    public String listBookstores(){
        return bookstoreService.findAll().stream()
                .map(bookstore -> "ID: %s, priceModifier: %f, moneyInCashRegister: %f, Location: %s".formatted(
                        bookstore.getId(), bookstore.getPriceModifier(), bookstore.getMoneyInCashRegister(), bookstore.getLocation())).collect(Collectors.joining(System.lineSeparator()));
    }

    @ShellMethod(value = "delete bookstore", key = "delete bookstore")
    public void deleteBook(Long id) {
        bookstoreService.deleteBookstore(id);
    }
}
