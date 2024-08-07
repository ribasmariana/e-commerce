package br.com.senac.projetoecommerce.entitys;

import br.com.senac.projetoecommerce.useCases.produtos.ProdutosPrecos;
import br.com.senac.projetoecommerce.utils.Categorias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double preco = 9.90;
    private int quantidade;
    private String imagemPequena;
    private String imagemGrande;


    @Enumerated(EnumType.STRING)
    private Categorias categoria;

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private List<ProdutosPrecos> precos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double valor) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


   /* public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }*/


    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProdutosPrecos> getPrecos() {
        return precos;
    }

    public void setPrecos(List<ProdutosPrecos> precos) {
        this.precos = precos;
    }

    public String getImagemPequena() {
        return imagemPequena;
    }

    public void setImagemPequena(String imagemPequena) {
        this.imagemPequena = imagemPequena;
    }

    public String getImagemGrande() {
        return imagemGrande;
    }

    public void setImagemGrande(String imagemGrande) {
        this.imagemGrande = imagemGrande;
    }


    public Optional<Produtos> findById(Long id) {
        return findById(id);
    }

    /*public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }*/



}



