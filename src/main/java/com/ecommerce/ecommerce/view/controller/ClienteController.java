package com.ecommerce.ecommerce.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.model.Endereco;
import com.ecommerce.ecommerce.services.ClienteService;
import com.ecommerce.ecommerce.shared.ClienteDTO;
import com.ecommerce.ecommerce.util.ViaCepWs;
import com.ecommerce.ecommerce.view.model.cliente.ClienteLogin;
import com.ecommerce.ecommerce.view.model.cliente.ClienteRequest;
import com.ecommerce.ecommerce.view.model.cliente.ClienteResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private  ViaCepWs viaCep;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obterTodos(){
        
        List<ClienteDTO> clientes = clienteService.obterTodos();
        List<ClienteResponse> response = clientes.stream()
        .map(cliente -> new ModelMapper().map(cliente, ClienteResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obterPorId(@PathVariable Integer id){
        Optional<ClienteDTO> dto =  clienteService.obterPorId(id);

        ClienteResponse cliente = new ModelMapper().map(dto.get(), ClienteResponse.class);
        
        return new ResponseEntity<>(cliente, HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<ClienteResponse> logar(@RequestBody ClienteLogin clienteLog) {
        ClienteDTO clienteDTO = clienteService.login(clienteLog.getEmail(), clienteLog.getSenha());

        ClienteResponse cliente = new ModelMapper().map(clienteDTO, ClienteResponse.class);

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> adicionar(@RequestBody ClienteRequest clienteReq){
        
        ModelMapper mapper = new ModelMapper();

        Endereco endereco = this.viaCep.consultarCep(clienteReq.getCep());

        endereco.setComplemento(clienteReq.getComplemento());
        endereco.setNumero(clienteReq.getNumero());

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNome(clienteReq.getNome());
        clienteDTO.setCpf(clienteReq.getCpf());
        clienteDTO.setEmail(clienteReq.getEmail());
        clienteDTO.setDataDeNascimento(clienteReq.getDataDeNascimento());
        clienteDTO.setEndereco(endereco);
        clienteDTO.setSenha(clienteReq.getSenha());



        clienteDTO = clienteService.adicionar(clienteDTO);

        return new ResponseEntity<>(mapper.map(clienteDTO, ClienteResponse.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@RequestBody ClienteRequest clienteReq, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();

        Endereco endereco = this.viaCep.consultarCep(clienteReq.getCep());

        endereco.setComplemento(clienteReq.getComplemento());
        endereco.setNumero(clienteReq.getNumero());

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNome(clienteReq.getNome());
        clienteDTO.setCpf(clienteReq.getCpf());
        clienteDTO.setEmail(clienteReq.getEmail());
        clienteDTO.setDataDeNascimento(clienteReq.getDataDeNascimento());
        clienteDTO.setEndereco(endereco);

        clienteDTO = clienteService.atualizar(id, clienteDTO);

        return new ResponseEntity<>(
            mapper.map(clienteDTO, ClienteResponse.class),
            HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
