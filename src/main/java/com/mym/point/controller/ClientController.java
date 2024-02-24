package com.mym.point.controller;


import com.mym.point.dto.ClientDto;
import com.mym.point.exception.EntityAlreadyExistsException;
import com.mym.point.exception.EntityNotFoundException;
import com.mym.point.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDto> getClients() {
        return clientService.getClients();
    }

    @GetMapping (value = "/{nameClient}")
    public List<ClientDto> getClient(@PathVariable String nameClient){
        return clientService.getClient(nameClient);
    }

    @PostMapping
    public ClientDto createClient(@RequestBody ClientDto newClient) throws EntityAlreadyExistsException {
        return clientService.createClient(newClient);

    }

    @PutMapping
    public void updateClient(@RequestBody ClientDto clientToUpdate) throws EntityNotFoundException, EntityAlreadyExistsException {
         clientService.updateClient(clientToUpdate);
    }
    @DeleteMapping(value = "{id}")
    public void deleteProduct(@PathVariable Long id) throws EntityNotFoundException {
        clientService.deleteClient(id);
    }

}
