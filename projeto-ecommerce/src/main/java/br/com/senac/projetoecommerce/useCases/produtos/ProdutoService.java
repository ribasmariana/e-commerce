package br.com.senac.projetoecommerce.useCases.produtos;

import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.utils.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produtos> listarProdutosPorCategoria(Categorias categoria) {
        return produtoRepository.findByCategoria(String.valueOf(categoria));
    }

    public Produtos buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }
}