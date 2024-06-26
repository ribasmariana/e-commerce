package br.com.senac.projetoecommerce.useCases.clientes.domains;

import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;


public class ClienteRequestDom {

    private Long id;
    /*@NotNull(message = "O campo 'nome' não pode ser nulo")*/
    private String nome;
    /*@NotNull(message = "O campo 'sobrenome' não pode ser nulo")*/
    private String sobrenome;
    /*@NotNull(message = "O campo 'email' não pode ser nulo")*/
    private String email;
    /*@NotNull(message = "O campo 'telefone' não pode ser nulo")*/
    private String telefone;
    /*@NotNull(message = "O campo 'endereco' não pode ser nulo")*/
    private List<EnderecosRequestDom> enderecos;

    /*@NotNull(message = "O campo 'senha' não pode ser nulo")*/
    private String senha;
   /* @NotNull(message = "O campo 'cpf' não pode ser nulo")*/
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<EnderecosRequestDom> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<EnderecosRequestDom> enderecos) {
        this.enderecos = enderecos;
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
}
