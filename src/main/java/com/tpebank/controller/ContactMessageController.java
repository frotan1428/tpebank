package com.tpebank.controller;
import com.tpebank.domain.ContactMessage;
import com.tpebank.dto.ContactMessageDTO;
import com.tpebank.service.ContactMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/message")
//@AllArgsConstructor
public class ContactMessageController {
    private ContactMessageService contactMessageService;
    public ContactMessageController(ContactMessageService contactMessageService){
        this.contactMessageService=contactMessageService;
    }
    /*
       {
           "name":"Bruce",
               "email":"Bruce@email.com",
               "phoneNumber":"555-444-5555",
               "subject":"Hi, What is up",
               "body":"I have a problem about transaction"
       }
       localhost:8080/message/visitor
       */
    @PostMapping("/visitor")
    public ResponseEntity<Map<String,String>> createMessage(@Valid @RequestBody ContactMessageDTO contactMessageDTO){
        contactMessageService.saveMessage(contactMessageDTO);
        Map<String,String> map=new HashMap<>();
        map.put("message","Message Saved Successfully");
        map.put("success","true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    //localhost:8080/message
    @GetMapping
    public ResponseEntity<List<ContactMessageDTO>> getAllMessages(){
        List<ContactMessageDTO> messages= contactMessageService.getAll();
        return ResponseEntity.ok(messages);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ContactMessageDTO> getMessage(@PathVariable Long id){
        ContactMessageDTO messageDTO= contactMessageService.getMessage(id);
        return ResponseEntity.ok(messageDTO);
    }


}