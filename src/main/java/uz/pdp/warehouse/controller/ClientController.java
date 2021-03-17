package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.ClientDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    //Create
    @PostMapping
    public Result addClient(@RequestBody ClientDto clientDto){
        Result result = clientService.add(clientDto);
        return result;
    }

    //Read
    @GetMapping
    public List<Client> getClients(){
        List<Client> clients = clientService.getAll();
        return clients;
    }

    //Read One
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Integer id){
        Client client = clientService.getOne(id);
        return client;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto){
        Result result = clientService.update(id, clientDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable Integer id){
        Result result = clientService.delete(id);
        return result;
    }

}
