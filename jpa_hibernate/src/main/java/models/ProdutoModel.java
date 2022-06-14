package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProdutoModel {

    public void create(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {

            Produto produto = em.find(Produto.class, p.getId());

            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
            System.out.println("Produto removido com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto findById(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produtoId = null;

        try {
            produtoId = em.find(Produto.class, p.getId());
        } catch (Exception e) {
            em.close();
        } finally {
            em.close();
            emf.close();
        }

        return produtoId;
    }

    public List<Produto> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Produto> produtos = new ArrayList<>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            produtos = em.createQuery("SELECT p FROM Produto p").getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao encontrar produtos !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

        return produtos;
    }
}
