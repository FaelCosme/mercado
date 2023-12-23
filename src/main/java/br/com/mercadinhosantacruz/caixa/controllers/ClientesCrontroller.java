/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mercadinhosantacruz.caixa.controllers;

import br.com.mercadinhosantacruz.caixa.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ClientesCrontroller {
    @GetMapping("/clientes")
    public String clientes(){
        return "clientes";
    }
    
    @Autowired JdbcTemplate jdbc;
    
    @PostMapping("/cadastrar")
    public String adicionar(String nome, String cpf, String endereco, String telefone) {
        Cliente c = new Cliente(nome,cpf, endereco, telefone);
        c.cadastrar(jdbc);
        return "/pagDesconto";
    }
    
}


    

