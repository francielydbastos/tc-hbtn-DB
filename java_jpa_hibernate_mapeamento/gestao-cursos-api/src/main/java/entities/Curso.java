package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String sigla;
    @ManyToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "prof_id", referencedColumnName = "id")
    private Professor professor;
    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private MaterialCurso material;
    @ManyToMany
    @JoinTable(
        name = "ALunos_curso",
        joinColumns =  @JoinColumn(name = "curso_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    )
    private List<Aluno> aluno;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public MaterialCurso getMaterial() {
        return material;
    }

    public void setMaterial(MaterialCurso material) {
        this.material = material;
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'';
    }
}
