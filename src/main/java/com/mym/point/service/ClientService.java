package com.mym.point.service;

import com.mym.point.dto.*;
import com.mym.point.entity.*;
import com.mym.point.exception.*;
import com.mym.point.repository.*;
//import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDto> getAllClients(){
        List<ClientEntity> clientEntityList = clientRepository.findAll();

        List<ClientDto> clientDtoList = clientEntityList.stream().
                map(client -> ClientDto.builder()
                        .clientId(client.getClientId())
                        .name(client.getName()).build()
                ).toList();
        return clientDtoList;
    }

    public ClientDto getAClient(String name){
        ClientEntity clientEntity = clientRepository.findAByName(name);

        ClientDto clientDto = ClientDto.builder()
                        .clientId(clientEntity.getClientId())
                        .name(clientEntity.getName()).build();
        return clientDto;

    }

    public ClientEntity createAClient(ClientDto clientDtoNew) throws EntityAlreadyExistsException {
        Boolean clientExistsByName = clientRepository.existsByName(clientDtoNew.getName());

        if (!clientExistsByName){
            ClientEntity clientEntityNew = ClientEntity.builder()
                    .clientId(clientDtoNew.getClientId())
                    .name(clientDtoNew.getName()).build();

            clientRepository.save(clientEntityNew);

            return clientEntityNew;
        }

        throw new EntityAlreadyExistsException("Ya existe un cliente con este nombre");
    }

    public ClientEntity updateAClient(ClientDto clientDtoUpdate ) throws EntityNotFoundException {

        Boolean clientExistsById =  clientRepository.existsById(clientDtoUpdate.getClientId());
        ClientEntity clientEntity  = clientRepository.findById(clientDtoUpdate.getClientId()).get();

        if (clientExistsById) {

            clientEntity.setName(clientDtoUpdate.getName());

            return clientRepository.save(clientEntity);
        }

        throw new EntityNotFoundException("No existe este producto");

    }
}
