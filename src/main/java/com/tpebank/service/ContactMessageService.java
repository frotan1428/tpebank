package com.tpebank.service;
import com.tpebank.domain.ContactMessage;
import com.tpebank.dto.ContactMessageDTO;
import com.tpebank.exception.ResourceNotFoundException;
import com.tpebank.exception.message.ExceptionMessages;
import com.tpebank.repository.ContactMessageRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class ContactMessageService {
    private ContactMessageRepository contactMessageRepository;
    private ModelMapper modelMapper;
    private ContactMessage convertTo(ContactMessageDTO contactMessageDTO){
        ContactMessage contactMessage= modelMapper.map(contactMessageDTO,ContactMessage.class);
        return contactMessage;
    }
    private ContactMessageDTO convertToDTO(ContactMessage contactMessage){
        ContactMessageDTO contactMessageDTO= modelMapper.map(contactMessage,ContactMessageDTO.class);
        return contactMessageDTO;
    }
    public void saveMessage(ContactMessageDTO contactMessageDTO){
        ContactMessage contactMessage= convertTo(contactMessageDTO);
        contactMessageRepository.save(contactMessage);
    }
    public List<ContactMessageDTO> getAll(){
        List<ContactMessage> messages=contactMessageRepository.findAll();
        return messages.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ContactMessageDTO getMessage(Long id){
        ContactMessage contactMessage= contactMessageRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException(String.format(ExceptionMessages.CONTACTMESSAGE_NOT_FOUND_MESSAGE,id)));
        return convertToDTO(contactMessage);
    }

}