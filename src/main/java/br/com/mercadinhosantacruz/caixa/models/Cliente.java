package br.com.mercadinhosantacruz.caixa.models;

import org.springframework.jdbc.core.JdbcTemplate;

public class Cliente {

    private int id;
    private String nome, cpf, endereco, telefone;

    public Cliente(String nome, String cpf, String endereco, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void cadastrar (JdbcTemplate jdbc) {
        if (id > 0) {
            jdbc.update("UPDATE clientes SET nome= ?, " + "preco = ?, endereco = ?, telefone = ? " + "WHERE cpf = ?; ", (ps) -> {

                ps.setString(1, nome);
                ps.setString(2, cpf);
                ps.setString(3, endereco);
                ps.setString(4, telefone);

            });
        } else {

            jdbc.update("insert into clientes (nome, cpf, endereco, telefone) " + "values (?, ?, ?,?);", (ps) -> {

                ps.setString(1, nome);
                ps.setString(2, cpf);
                ps.setString(3, endereco);
                ps.setString(4, telefone);

            });
        }
    }
}


