package com.mym.point.service;

import com.mym.point.dto.ClientDto;
import com.mym.point.entity.ClientEntity;
import com.mym.point.exception.EntityAlreadyExistsException;
import com.mym.point.exception.EntityNotFoundException;
import com.mym.point.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientDto> getClients(){
        List<ClientEntity> clientEntityList = clientRepository.findAll();

        List<ClientDto> clientDtoList = clientEntityList.stream().
                map(client -> ClientDto.builder()
                        .clientId(client.getClientId())
                        .name(client.getName())
                        .build()
                ).toList();
        return clientDtoList;
    }

    public List<ClientDto> getClient(String name){
        List<ClientEntity> clientEntityList = clientRepository.findAByName(name);

        List<ClientDto> clientDtoList = clientEntityList.stream().
                map(client -> ClientDto.builder()
                        .clientId(client.getClientId())
                        .name(client.getName())
                        .build()
                ).toList();
        return clientDtoList;
    }

    public ClientDto createClient(ClientDto newClient) throws EntityAlreadyExistsException {
        if (clientRepository.existsByName(newClient.getName())){
            throw new EntityAlreadyExistsException("Ya existe un cliente con el mismo nombre");
        }
        ClientEntity newClientEntity = ClientEntity.builder()
                .name(newClient.getName())
                .build();

        clientRepository.save(newClientEntity);

        ClientDto newClientDto = ClientDto.builder()
                .clientId(newClientEntity.getClientId())
                .name(newClientEntity.getName())
                .build();

        return newClientDto;
    }

    public void updateClient(ClientDto clientToUpdate ) throws EntityNotFoundException, EntityAlreadyExistsException {

        boolean clientExists =  clientRepository.existsById(clientToUpdate.getClientId());

        if (!clientExists) {
            throw new EntityNotFoundException("No existe este cliente");
        }

        ClientEntity newClient  = clientRepository.findById(clientToUpdate.getClientId()).get();
        newClient.setName(clientToUpdate.getName());

        try{
            clientRepository.save(newClient);
        }catch (DataIntegrityViolationException e){
            throw new EntityAlreadyExistsException("Ya existe un cliente con el mismo nombre");
        }

    }

    public void deleteClient(Long idClient) throws EntityNotFoundException {
        boolean clientExists =  clientRepository.existsById(idClient);

        if (!clientExists) {
            throw new EntityNotFoundException("No se encontro el cliente");
        }
        clientRepository.deleteById(idClient);

    }

}
