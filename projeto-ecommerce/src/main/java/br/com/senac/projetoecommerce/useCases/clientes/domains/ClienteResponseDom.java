package br.com.senac.projetoecommerce.useCases.clientes.domains;

import br.com.senac.projetoecommerce.entitys.Enderecos;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosClienteResponseDom;

import java.util.List;

public class ClienteResponseDom {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String cpf;
    private String senha;

    private List<EnderecosClienteResponseDom> enderecos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<EnderecosClienteResponseDom> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecosClienteResponseDom> enderecos) {
        this.enderecos = enderecos;
    }
}
