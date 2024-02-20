package com.mym.point.controller;


import com.mym.point.dto.*;
import com.mym.point.entity.*;
import com.mym.point.exception.*;
import com.mym.point.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDto> getAllClients() {
        List<ClientDto> clientDtoList = clientService.getAllClients();
        return clientDtoList;
    }

    @GetMapping (value = "name/{nameClient}")
    public ClientDto getAClient(@PathVariable String nameClient){
        ClientDto clientDto = clientService.getAClient(nameClient);
        return clientDto;
    }

    @PostMapping
    public ClientDto createAClient(@RequestBody ClientDto clientDtoNew) throws EntityAlreadyExistsException {
        ClientEntity clientEntity = clientService.createAClient(clientDtoNew);
        ClientDto clientDto = ClientDto.builder()
                .clientId(clientEntity.getClientId())
                .name(clientEntity.getName())
                .build();
        return clientDto;
    }

    @PutMapping
    public ClientDto updateAClient(@RequestBody ClientDto clientDtoUpdate) throws EntityNotFoundException {
        ClientEntity clientEntity = clientService.updateAClient(clientDtoUpdate);

        ClientDto clientDto = ClientDto.builder()
                .clientId(clientEntity.getClientId())
                .name(clientEntity.getName())
                .build();

        return clientDto;

    }

}
