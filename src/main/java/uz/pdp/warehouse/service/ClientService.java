package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.ClientDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result add(ClientDto clientDto) {
        boolean exists = clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber());
        if (exists){
            return new Result("Client phone number have in db", false);
        }
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());

        clientRepository.save(client);

        return new Result("Client saved", true);
    }

    public List<Client> getAll() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    public Client getOne(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            return new Client();
        }
        Client client = optionalClient.get();
        return client;
    }

    public Result update(Integer id, ClientDto clientDto) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            return new Result("Client id not found", false);
        }
        Client client = optionalClient.get();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());

        clientRepository.save(client);

        return new Result("Client updated", true);
    }

    public Result delete(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            return new Result("Client id not found", false);
        }
        clientRepository.deleteById(id);
        return new Result("Client deleted", true);
    }
}
