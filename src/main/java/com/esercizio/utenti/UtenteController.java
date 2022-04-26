package com.esercizio.utenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    UtenteIterface utenteIterface;

    @GetMapping("/")
    public ResponseEntity<?> getAllUtenti(){
        List<Utente> utenti = utenteIterface.findAll();
        return ResponseEntity.ok(utenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUtenteById(@PathVariable Long id){
        Optional<Utente> utente = utenteIterface.findById(id);
        if(utente.isPresent()){
            return ResponseEntity.ok(utente);
        }
        else{
            return new ResponseEntity<>("id non trovato", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addNewUtente(@RequestBody Utente utente){
        utenteIterface.save(utente);
        return ResponseEntity.ok(utente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUtenteById(@RequestBody Utente utente, @PathVariable Long id){
        Optional<Utente> utenteOptional = utenteIterface.findById(id);
        if (utenteOptional.isPresent()){
            utenteIterface.save(utente);
            return ResponseEntity.ok(utente);
        }
        else{
            return new ResponseEntity<>("id non trovato", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAllUtenti(){
        utenteIterface.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUtenteById(@PathVariable Long id){
        Optional<Utente> utenteOptional = utenteIterface.findById(id);
        if (utenteOptional.isPresent()){
            utenteIterface.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("id non trovato", HttpStatus.NOT_FOUND);
        }
    }
}
