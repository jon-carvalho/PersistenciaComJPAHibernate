package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1L);
//        System.out.println("+++++++++++++++++++++++++++++++++"+ p.getPreco());

//        List<Produto> todos = produtoDao.buscarTodos();
//        List<Produto> todos = produtoDao.buscarPorNome("Xiaomi Redmi");
        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("celulares");
        todos.forEach(p2 -> System.out.println(p.getNome()));


//        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
//        System.out.println("Preco do Produto " + precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xaiomi Redmi", "Muito Legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();

//        ProdutoDao produtoDao = new ProdutoDao(em);
//        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

//        categoriaDao.cadastrar(celulares);
//        produtoDao.cadastrar(celular);

        em.persist(celulares);
        celulares.setNome("XPTO");

//        em.getTransaction().commit();
//        em.close();

        em.flush();
        em.clear();

        celulares = em.merge(celulares);
        celulares.setNome("1234");
        em.flush();
        em.remove(celulares);
        em.flush();
    }
}
