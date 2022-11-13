package com._2dmes_.collectingdonations.controllers;

import com._2dmes_.collectingdonations.components.DataBaseComponent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Plugin {

    @Autowired
    private DataBaseComponent db;

    @RequestMapping(value = "/api/donates/{id}/{idTransaction}", method = GET)
    @ResponseBody
    ResponseEntity<TextMessage> sendNewTransactions(@PathVariable("id") long id, @PathVariable("idTransaction") long idTransaction ) throws JsonProcessingException {
        TextMessage text = db.getNotDisplayedTransactions(id, idTransaction);
        return ResponseEntity.ok(text);
    }
}
