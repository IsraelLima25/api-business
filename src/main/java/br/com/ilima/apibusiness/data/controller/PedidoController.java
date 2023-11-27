package br.com.ilima.apibusiness.data.controller;

import br.com.ilima.apibusiness.data.dto.PedidoFormDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<Void> enviarPedidos(@RequestBody List<PedidoFormDTO> pedidos){
        System.out.println(pedidos.get(0));
        System.out.println(pedidos.get(1));
        return ResponseEntity.ok().build();
    }
}
