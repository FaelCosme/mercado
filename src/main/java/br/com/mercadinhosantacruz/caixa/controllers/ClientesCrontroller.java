/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mercadinhosantacruz.caixa.controllers;

import br.com.mercadinhosantacruz.caixa.models.Cliente;
import br.com.mercadinhosantacruz.caixa.models.Produto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ClientesCrontroller {
    @GetMapping("/clientes")
    public String clientes(){
        return "clientes";
    }
    
    @Autowired JdbcTemplate jdbc;
    
    @PostMapping("/pagDesconto")
    public String adicionar(Model model, String nome, String cpf, String endereco, String telefone) {
        
        Cliente c = new Cliente(nome,cpf, endereco, telefone);
        c.cadastrar(jdbc);
        
        List<Produto> produtos =
                Produto.listar(jdbc);
        model.addAttribute("produtos",
                produtos);
        
        double total = Produto.Total(jdbc);
        double desconto = total - (total * 0.2);
        model.addAttribute("desconto", desconto);
        return "pagDesconto";
    }
     @PostMapping("/adicionarD")
    public String adicionar(Model model, String nome,
            int qtd, float preco) {
        Produto p = new Produto(nome, 
                qtd, preco);
        p.salvar(jdbc);
        
        List<Produto> produtos =
                Produto.listar(jdbc);
        model.addAttribute("produtos",
                produtos);
        
        double total = Produto.Total(jdbc);
        double desconto = total - (total * 0.2);
        model.addAttribute("desconto", desconto);
        
        return "pagDesconto";
    }
    
    @PostMapping("/atualizarD")
    public String atualizar(Model model, int id, String nome,
            int qtd, float preco){
        Produto p = Produto.buscar(id, jdbc);
        p.setNome(nome);
        p.setQtd(qtd);
        p.setPreco(preco);
        p.salvar(jdbc);
        
        List<Produto> produtos =
                Produto.listar(jdbc);
        model.addAttribute("produtos",
                produtos);
        double total = Produto.Total(jdbc);
        double desconto = total - (total * 0.2);
        model.addAttribute("desconto", desconto);
        
        return "pagDesconto";
    }
     @GetMapping("/excluirD")
    public String excluir(int id, Model model) {
        Produto.remover(id, jdbc);
        List<Produto> produtos =
                Produto.listar(jdbc);
        Produto p = Produto.buscar(id, jdbc);
        model.addAttribute("produtos",
                produtos);
        model.addAttribute("p", p);
        double total = Produto.Total(jdbc);
        double desconto = total - (total * 0.2);
        model.addAttribute("desconto", desconto);
        return "pagDesconto";
    }
    
    @GetMapping("/editarD")
    public String editar(int id, Model model) {
        List<Produto> produtos =
                Produto.listar(jdbc);
        Produto p = Produto.buscar(id, jdbc);
        model.addAttribute("produtos",
                produtos);
        model.addAttribute("p", p);
        double total = Produto.Total(jdbc);
        double desconto = total - (total * 0.2);
        model.addAttribute("desconto", desconto);
        return "pagDesconto";
    }
    
}

