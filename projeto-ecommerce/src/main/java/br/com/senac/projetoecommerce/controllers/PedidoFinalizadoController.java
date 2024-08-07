package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.PedidoFinalizado;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.PedidoFinalizadoService;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.domains.PedidoFinalizadoRequestDom;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.domains.PedidoFinalizadoResponseDom;
import br.com.senac.projetoecommerce.utils.SenacExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin
public class PedidoFinalizadoController {

    @Autowired
    private PedidoFinalizadoService pedidoFinalizadoService;

    @PostMapping("/criar")
    public ResponseEntity<PedidoFinalizadoResponseDom> criarPedido(@RequestBody PedidoFinalizadoRequestDom pedido) {
        try {
            PedidoFinalizadoResponseDom novoPedido = pedidoFinalizadoService.criarPedido(0l, pedido);
            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        } catch (SenacExceptions e) {
            // Retorna BAD_REQUEST (400) com a mensagem de erro específica do negócio
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Retorna INTERNAL_SERVER_ERROR (500) para outros erros inesperados
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ultimos/{clienteId}")
    public ResponseEntity<List<PedidoFinalizadoResponseDom>> listarUltimosPedidos(@PathVariable Long clienteId) {
        try {
            // Chama o serviço que retorna uma lista de PedidoFinalizadoResponseDom
            List<PedidoFinalizadoResponseDom> pedidos = pedidoFinalizadoService.listarUltimosPedidosPorCliente(clienteId);
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (SenacExceptions e) {
            // Retorna NOT_FOUND (404) caso não encontre pedidos para o cliente
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Retorna INTERNAL_SERVER_ERROR (500) para outros erros inesperados
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
