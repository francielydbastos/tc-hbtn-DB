package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.time.LocalDate;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {
        //Produto
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        Produto p2 = new Produto();
        p1.setNome("PC");
        p1.setPreco(900.0);
        p1.setQuantidade(200);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);
        produtoModel.create(p2);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        //3) Buscando produto por id
        p1.setId(1);
        Produto produto = produtoModel.findById(p1);
        System.out.println("Produto encontrado: " + produto.getNome());

        //4) Atualizando um produto
        p2.setPreco(600.0);
        produtoModel.update(p2);

        //5) Deletando um produto
        produtoModel.delete(p1);

        //Pessoa
        PessoaModel pessoaModel = new PessoaModel();

        Pessoa ps1 = new Pessoa();
        ps1.setNome("Kim Namjoon");
        ps1.setCpf("11122233325");
        ps1.setDataNascimento(LocalDate.of(1994, 9, 12));
        ps1.setIdade(27);
        ps1.setEmail("knj@mail.com");

        Pessoa ps2 = new Pessoa();
        ps2.setNome("Kim Seokjin");
        ps2.setCpf("15422233325");
        ps2.setDataNascimento(LocalDate.of(1992, 12, 4));
        ps2.setIdade(29);
        ps2.setEmail("ksj@mail.com");


        // 1) Criando uma pessoa
        pessoaModel.create(ps1);
        pessoaModel.create(ps2);

        //2) Buscando todas as pessoas na base de dados
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontrados : " + pessoas.size());

        //3) Buscando pessoa por id
        ps1.setId(1);
        Pessoa pessoa = pessoaModel.findById(ps1);
        System.out.println("Pessoa encontrada: " + pessoa.getNome());

        //4) Atualizando uma pessoa
        ps2.setEmail("rjdad@mail.com");
        pessoaModel.update(ps2);

        //5) Deletando uma pessoa
        pessoaModel.delete(ps1);
    }
}
