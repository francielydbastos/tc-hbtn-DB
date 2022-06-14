package models;

import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            Pessoa pessoa = em.find(Pessoa.class, p.getId());
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.remove(pessoa);
            em.getTransaction().commit();
            System.out.println("Pessoa removida com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa findById(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoaId = null;

        try {
            pessoaId = em.find(Pessoa.class, p.getId());
        } catch (Exception e) {
            em.close();
        } finally {
            em.close();
            emf.close();
        }

        return pessoaId;
    }

    public List<Pessoa> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Pessoa> pessoas = new ArrayList<>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            pessoas = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

        return pessoas;
    }

}
