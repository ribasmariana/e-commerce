package br.com.senac.projetoecommerce.useCases.clientes;

import br.com.senac.projetoecommerce.entitys.Clientes;
import br.com.senac.projetoecommerce.entitys.Enderecos;
import br.com.senac.projetoecommerce.useCases.clientes.domains.ClienteRequestDom;
import br.com.senac.projetoecommerce.useCases.clientes.domains.ClienteResponseDom;
import br.com.senac.projetoecommerce.useCases.clientes.implement.ClientesRepository;
import br.com.senac.projetoecommerce.useCases.clientes.implement.ValidacoesCliente;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosClienteResponseDom;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosResponseDom;
import br.com.senac.projetoecommerce.utils.SenacExceptions;
import br.com.senac.projetoecommerce.utils.SenhaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {
    @Autowired
    private ClientesRepository clientesRepository;


    //carregar clientes
    public List<ClienteResponseDom> carregarClientes() {
        List<Clientes> resultado = clientesRepository.findAll();
        List<ClienteResponseDom> out = new ArrayList<>();

        for (Clientes dado : resultado) {
            ClienteResponseDom aux = new ClienteResponseDom();
            aux.setId(dado.getId());
            aux.setNome(dado.getNome());
            aux.setSobrenome(dado.getSobrenome());
            aux.setEmail(dado.getEmail());
            aux.setTelefone(dado.getTelefone());
            aux.setCpf(dado.getCpf());

            out.add(aux);
        }

        return out;
    }

    //criar clientes
    public ClienteResponseDom criarClientes(ClienteRequestDom cliente) throws SenacExceptions {
        List<String> mensagens = ValidacoesCliente.validarCliente(cliente);
        if (!mensagens.isEmpty()){
            throw new SenacExceptions(mensagens);
        }

        Clientes clienteEntidade = new Clientes();
        clienteEntidade.setNome(cliente.getNome());
        clienteEntidade.setSobrenome(cliente.getSobrenome());
        clienteEntidade.setEmail(cliente.getEmail());
        clienteEntidade.setTelefone(cliente.getTelefone());
        clienteEntidade.setCpf(cliente.getCpf());
        clienteEntidade.setSenha(cliente.getSenha());

        Clientes resultado = clientesRepository.save(clienteEntidade);

        ClienteResponseDom responseDom = new ClienteResponseDom();
        responseDom.setId(resultado.getId());
        responseDom.setSobrenome(resultado.getSobrenome());
        responseDom.setNome(resultado.getNome());
        responseDom.setEmail(resultado.getEmail());
        responseDom.setTelefone(resultado.getTelefone());
        responseDom.setCpf(resultado.getCpf());
        responseDom.setSenha(resultado.getSenha());

        return responseDom;
    }

    //excluir cliente
    public void excluirCliente(Long id) {
        clientesRepository.deleteById(id);
    }

    //carregar um cliente específico
    public ClienteResponseDom carregarClienteById(Long id) {
        Optional<Clientes> resultado = clientesRepository.findById(id);
        if (resultado.isPresent()) {
            Clientes cliente = resultado.get();
            ClienteResponseDom response = new ClienteResponseDom();
            response.setId(cliente.getId());
            response.setTelefone(cliente.getTelefone());
            response.setNome(cliente.getNome());
            response.setSobrenome(cliente.getSobrenome());
            response.setEmail(cliente.getEmail());
            response.setCpf(cliente.getCpf());
           // response.setEnderecos(cliente.getEnderecos());

            List<EnderecosClienteResponseDom> listaEnderecos = new ArrayList<>();
            for (Enderecos enderecos : cliente.getEnderecos()){
                EnderecosClienteResponseDom clienteEnderecosResponseDom = new EnderecosClienteResponseDom();

                clienteEnderecosResponseDom.setId(enderecos.getId());
                clienteEnderecosResponseDom.setRua(enderecos.getRua());
                clienteEnderecosResponseDom.setBairro(enderecos.getBairro());
                clienteEnderecosResponseDom.setCidade(enderecos.getCidade());
                clienteEnderecosResponseDom.setEstado(enderecos.getEstado());

                listaEnderecos.add(clienteEnderecosResponseDom);

            }

            response.setEnderecos(listaEnderecos);
            return response;
        }
        return null;
    }

    //Lógica para atualizar cadastro do cliente
    public ClienteResponseDom atualizarCliente(Long id, ClienteRequestDom cliente) {

        Optional<Clientes> resultado = clientesRepository.findById(id).map(record -> {
            record.setNome(cliente.getNome());
            record.setSobrenome(cliente.getSobrenome());
            record.setEmail(cliente.getEmail());
            record.setTelefone(cliente.getTelefone());
            record.setCpf(cliente.getCpf());
            return clientesRepository.save(record);
        });
        if (resultado.isPresent()) {
            Clientes clientesEntidade = resultado.get();

            ClienteResponseDom responseDom = new ClienteResponseDom();

            responseDom.setId(clientesEntidade.getId());
            responseDom.setNome(clientesEntidade.getNome());
            responseDom.setSobrenome(clientesEntidade.getSobrenome());
            responseDom.setEmail(clientesEntidade.getEmail());
            responseDom.setTelefone(clientesEntidade.getTelefone());
            responseDom.setCpf(clientesEntidade.getCpf());
            return responseDom;
        }

        return null;
    }

    public ClienteResponseDom autenticarCliente(String email, String senha) throws SenacExceptions {
        Optional<Clientes> clienteResult = clientesRepository.findByEmail(email);
        if(clienteResult.isPresent()){
            if(SenhaUtil.validarSenha(senha, clienteResult.get().getSenha())){
                return ClientesMappers.clientesToClientesResponseDom(clienteResult.get());
            }

            throw new SenacExceptions("Senha invalida!");
        }

        throw new SenacExceptions("Cliente não encontrado!");
    }
}
