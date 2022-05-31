package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.model.Cliente;
import com.ecommerce.ecommerce.model.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.repository.ClienteRepository;
import com.ecommerce.ecommerce.shared.ClienteDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> obterTodos(){
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
        .map(cliente -> new ModelMapper().map(cliente, ClienteDTO.class))
        .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> obterPorId(Integer id){

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isEmpty()){
            throw new ResourceNotFoundException("Cliente com id " + id + " não encontrado.");
        }

        ClienteDTO dto = new ModelMapper().map(cliente.get(), ClienteDTO.class);

        return Optional.of(dto);
    }

    public ClienteDTO adicionar(ClienteDTO clienteDTO){
        clienteDTO.setId(null);

        ModelMapper mapper = new ModelMapper();

        Cliente cliente = mapper.map(clienteDTO, Cliente.class);

        cliente = clienteRepository.save(cliente);

        clienteDTO.setId(cliente.getId());

        return clienteDTO;
    }

    public void deletar(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o cliente com o id: " + id);
        }

        clienteRepository.deleteById(id);
    }

    public ClienteDTO atualizar(Integer id, ClienteDTO clienteDTO){
        clienteDTO.setId(id);

        ModelMapper mapper = new ModelMapper();

        Cliente cliente = mapper.map(clienteDTO, Cliente.class);

        clienteRepository.save(cliente);

        return clienteDTO;
    }
    
}
