package marcelobaldi.callbackbase01.model;
import android.os.AsyncTask;

import java.io.Serializable;

public class ClienteModel implements Serializable {
    //Atributos
    private Integer id;                 //String,       Se API's e Firebase
    private String  nome;               //Manipular,    Indexação
    private String  email;              //Manipular,    Impedir Duplo Cadastro
    private Integer idade;              //Manipular,    Números

    //Construtor
    public ClienteModel() { }
    public ClienteModel(String nome, String email, Integer idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }
    public ClienteModel(Integer id, String nome, String email, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    //GetterSetter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    //ToString
    @Override public String toString() {
        return "ClienteModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                '}';
    }
}

