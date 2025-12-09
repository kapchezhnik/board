package com.example.project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/posts")

public class controller {

    private final List<messages> messageList = new ArrayList<>();

    @PostMapping("")
    public ResponseEntity<messages> createmessages(@RequestBody messages newmes) {
        newmes.setId(messageList.size());
        newmes.setCreatedAt(LocalDateTime.from(LocalDateTime.now()));
        messageList.add(newmes);
        return ResponseEntity.ok(newmes);
    }

    @GetMapping("")
    public ResponseEntity<List<messages>> getAllMessages(){
        return ResponseEntity.ok(messageList.reversed());
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<messages> getmessagesById(@PathVariable int messageId){
        return ResponseEntity.ok(messageList.get(messageId));
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<messages> updatemessage(@PathVariable int messageId, @RequestBody messages newmes){
        newmes.setId(messageId);
        messageList.set(messageId, newmes);
        return ResponseEntity.ok(newmes);
    }

    @DeleteMapping("/{messageId")
    public ResponseEntity<Void> deletemessagesById(@PathVariable int messageId){
        messageList.remove(messageId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllmessages(){
        messageList.clear();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countmessagesLength() {
        return ResponseEntity.ok(messageList.size());
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<messages>> getAuthorMessages(@PathVariable String author) {
        List<messages> authormessage = new ArrayList<>();
        for (messages newmes : messageList) {
            if (newmes.getAuthor().equals(author)) {
                authormessage.add(newmes);
            }
        }
        return ResponseEntity.ok(authormessage);
    }

    @GetMapping("/cheapest")
    public ResponseEntity<List<messages>> getCheapest() {
        List<messages> cheap = messageList.stream().sorted(Comparator.comparing(messages::getPrice)).toList();
        return ResponseEntity.ok(cheap);
    }

    @GetMapping("/expensive")
    public ResponseEntity<List<messages>> getExpensive() {
        List<messages> expensive = messageList.stream().sorted(Comparator.comparing(messages::getPrice)).toList().reversed();
        return ResponseEntity.ok(expensive);
    }
}