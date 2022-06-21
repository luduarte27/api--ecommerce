package com.ecommerce.ecommerce.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import com.ecommerce.ecommerce.model.Cliente;
import com.ecommerce.ecommerce.model.MensagemEmail;
import com.ecommerce.ecommerce.model.exception.InvalidCpfException;
import com.ecommerce.ecommerce.model.exception.InvalidEmailException;
import com.ecommerce.ecommerce.model.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.repository.ClienteRepository;
import com.ecommerce.ecommerce.shared.ClienteDTO;
import com.ecommerce.ecommerce.util.CpfValidator;
import com.ecommerce.ecommerce.util.EmailValidation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmailService emailService;

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

        EmailValidation emailValidation = new EmailValidation();
        CpfValidator cpfValidator = new CpfValidator();

        if(!emailValidation.isValidEmailAddress(clienteDTO.getEmail())){
            throw new InvalidEmailException("O Email '" + clienteDTO.getEmail() + "' não é um email válido. Tente novamente.");
        }

        if(!cpfValidator.isCPF(clienteDTO.getCpf())){
            throw new InvalidCpfException("O CPF '" + clienteDTO.getCpf() + "' não é um cpf válido. Tente novamente.");
        }

        Cliente clienteEmail = clienteRepository.findByEmail(clienteDTO.getEmail());

        if(clienteEmail != null){
            throw new InvalidEmailException("O Email '" + clienteDTO.getEmail() + "' já foi cadastrado.");
        }

        ArrayList<String> emails = new ArrayList<String>();
        emails.add(clienteDTO.getEmail());

        // try {
        //     emailService.enviar(new MensagemEmail("teste", "<h1>Agora Vai</h1>", "lucianaduartefotografia@gmail.com", 
        //     emails));
        // } catch (MessagingException e) {
        //     e.printStackTrace();
        // }

        ModelMapper mapper = new ModelMapper();

        Cliente cliente = mapper.map(clienteDTO, Cliente.class);

        cliente = clienteRepository.save(cliente);

        clienteDTO.setId(cliente.getId());

        return clienteDTO;
    }

    public ClienteDTO login(String email, String senha){
        
        EmailValidation emailValidation = new EmailValidation();

        if(!emailValidation.isValidEmailAddress(email)){
            throw new InvalidEmailException("O Email '" + email + "' não é um email válido. Tente novamente.");
        }

        Cliente clienteEmail = clienteRepository.findByEmail(email);

        if(clienteEmail != null) {

            if(!clienteEmail.getSenha().equals(senha)) {
                throw new InputMismatchException("Senha incorreta");
            }

            ModelMapper mapper = new ModelMapper();

            ClienteDTO clienteDTO = mapper.map(clienteEmail, ClienteDTO.class);

            return clienteDTO;
        } else {
            throw new InvalidEmailException("O Email '" + email + "' não foi cadastrado.");
        }
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

        EmailValidation emailValidation = new EmailValidation();
        CpfValidator cpfValidator = new CpfValidator();

        if(!emailValidation.isValidEmailAddress(clienteDTO.getEmail())){
            throw new InvalidEmailException("O Email '" + clienteDTO.getEmail() + "' não é um email válido. Tente novamente.");
        }

        if(!cpfValidator.isCPF(clienteDTO.getCpf())){
            throw new InvalidCpfException("O CPF '" + clienteDTO.getCpf() + "' não é um cpf válido. Tente novamente.");
        }


        ModelMapper mapper = new ModelMapper();

        Cliente cliente = mapper.map(clienteDTO, Cliente.class);

        clienteRepository.save(cliente);

        return clienteDTO;
    }
    
}
