package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestaoCursosMain {
    public static void main(String[] args) {
        //Instanciando os Models
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        //Endereços
        Endereco endereco1 = new Endereco("Rua", "José das Bicas", "111", "Barreiro", "BH", "MG", 11122222);
        Endereco endereco2 = new Endereco("Av", "Hilda Furacão", "561", "Barreiro", "BH", "MG", 22234561);
        Endereco endereco3 = new Endereco("Av", "Lua Azul", "1000", "Barreiro", "BH", "MG", 11123456);
        Endereco endereco4 = new Endereco("Av", "Sol Amarelo", "1234", "Barreiro", "BH", "MG", 12345678);

        //Alunos
        Aluno aluno1 = new Aluno();
        aluno1.setNomeCompleto("Joao dos Santos");
        aluno1.setMatricula("A123456");
        aluno1.setNascimento(LocalDate.of(1990, 2, 11));
        aluno1.setEmail("jsantos@gmail.com");
        aluno1.setTelefone(new ArrayList<>(List.of(new Telefone("31", "987341232"))));
        aluno1.setEndereco(new ArrayList<>(List.of(endereco1, endereco2)));

        Aluno aluno2 = new Aluno();
        aluno2.setNomeCompleto("Ana da Silva");
        aluno2.setMatricula("A654321");
        aluno2.setNascimento(LocalDate.of(1993, 8, 25));
        aluno2.setEmail("anasilva@gmail.com");
        aluno2.setTelefone(new ArrayList<>(List.of(new Telefone("31", "999594321"))));
        aluno2.setEndereco(new ArrayList<>(List.of(endereco3)));

        Aluno aluno3 = new Aluno();
        aluno3.setNomeCompleto("Joana Alves");
        aluno3.setMatricula("A162534");
        aluno3.setNascimento(LocalDate.of(1994, 3, 25));
        aluno3.setEmail("jalves@gmail.com");
        aluno3.setTelefone(new ArrayList<>(List.of(new Telefone("31", "923456542"))));
        aluno3.setEndereco(new ArrayList<>(List.of(endereco4)));

        //Professores
        Professor prof1 = new Professor();
        prof1.setNomeCompleto("Maria da Cruz");
        prof1.setMatricula("P123456");
        prof1.setEmail("mariaprof@gmail.com");

        //Materiais de Curso
        MaterialCurso material1 = new MaterialCurso();
        material1.setUrl("https://pt.wikipedia.org/wiki/JDBC");

        MaterialCurso material2 = new MaterialCurso();
        material2.setUrl("https://en.wikipedia.org/wiki/Microservices");

        //Cursos
        Curso curso1 = new Curso();
        curso1.setNome("Banco de dados com JDBC");
        curso1.setSigla("BD-JDBC");
        curso1.setProfessor(prof1);
        curso1.setMaterial(material1);
        curso1.setAluno(new ArrayList<>(List.of(aluno1, aluno2)));

        Curso curso2 = new Curso();
        curso2.setNome("Java Microsserviços");
        curso2.setSigla("JM");
        curso2.setProfessor(prof1);
        curso2.setMaterial(material2);
        curso2.setAluno(new ArrayList<>(List.of(aluno2, aluno3)));

        //Criando alunos no DB
        alunoModel.create(aluno1);
        alunoModel.create(aluno2);
        alunoModel.create(aluno3);

        //Criando cursos no DB
        cursoModel.create(curso1);
        cursoModel.create(curso2);

        //Atualizando aluno1
        aluno1.setEmail("jsantos@hotmail.com");
        alunoModel.update(aluno1);

        //Atualizando curso1
        curso1.setSigla("DB-JDBC");
        cursoModel.update(curso1);

        //Buscando todos os alunos
        List<Aluno> alunos = alunoModel.findAll();
        System.out.println("Os alunos encontrados foram: " + alunos);

        //Buscando todos os cursos
        List<Curso> cursos = cursoModel.findAll();
        System.out.println("Os cursos encontrados foram: " + cursos);

        //Buscando aluno de id 3
        Aluno alunoId = alunoModel.findById(3L);
        System.out.println("O aluno encontrado foi: " + alunoId);

        //Buscando curso de id 2
        Curso cursoId = cursoModel.findById(2L);
        System.out.println("O curso encontrado foi: " + cursoId);

        //Removendo aluno de id 3
        Aluno aluno = new Aluno();
        aluno.setId(3);
        alunoModel.delete(aluno);

        //Removendo curso de id 2
        Curso curso = new Curso();
        curso.setId(2);
        cursoModel.delete(curso);

        //Buscando todos os alunos
        List<Aluno> alunos2 = alunoModel.findAll();
        System.out.println("Os alunos encontrados foram: " + alunos2);

        //Buscando todos os cursos
        List<Curso> cursos2 = cursoModel.findAll();
        System.out.println("Os cursos encontrados foram: " + cursos2);

    }
}
