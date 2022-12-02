package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 0;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
          id++;
          book.setId(id);
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        return new ResponseEntity<>(bookList.get(id-1),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") int id){
        bookList.remove(id-1);
        return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookList,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-books")
    public ResponseEntity<String> deleteAllBooks(){
        bookList.clear();
        return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author){
        List<Book> book = new ArrayList<>();
        for(Book i : bookList){
            if(i.getAuthor().equals(author)){
                book.add(i);
            }
        }
        return new ResponseEntity<>(book,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") String genre){
        List<Book> book = new ArrayList<>();
        for(Book i : bookList){
            if(i.getGenre().equals(genre)){
                book.add(i);
            }
        }
        return new ResponseEntity<>(book,HttpStatus.ACCEPTED);
    }
}
