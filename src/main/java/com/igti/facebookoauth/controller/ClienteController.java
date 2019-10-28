package com.igti.facebookoauth.controller;

import com.igti.facebookoauth.entity.Cliente;
import com.igti.facebookoauth.helpers.ApplicationProperties;
import com.igti.facebookoauth.helpers.Helpers;
import com.igti.facebookoauth.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    Helpers helper = new Helpers();

    @GetMapping("/conta/{id}")
    public ResponseEntity<Cliente> recuperaConta(@PathVariable("id") String id) {

        if(clienteRepository.existsById(id)) {

            Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                    String.format("Não foi possível encontrar a conta com id %d", id)));
            return new ResponseEntity(cliente, HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "https://www.facebook.com/v4.0/dialog/oauth?client_id="
                    + ApplicationProperties.INSTANCE.getClientId() +
                    "&redirect_uri=http://localhost:8080/conta/cadastro/" + id + "&state=state");

            return new ResponseEntity(headers,HttpStatus.TEMPORARY_REDIRECT);
        }
    }

    @GetMapping("/conta/cadastro/{id}")
    public ResponseEntity<Cliente> cadastrarConta(@PathVariable("id") String id, @PathParam("code") String code,
                                                  @PathParam("state") String state) {

        String accessToken = helper.getAccessToken(id, state, code);
        String userId = helper.getUserId(accessToken);
        String userName = helper.getUserName(accessToken, userId);

        Cliente cliente = new Cliente();

        cliente.setFacebookId(userId);
        cliente.setName(userName);

        Cliente novoRecurso = clienteRepository.save(cliente);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:8080/conta/" +
                novoRecurso.getId());
        return new ResponseEntity(headers, HttpStatus.TEMPORARY_REDIRECT);
    }
}
