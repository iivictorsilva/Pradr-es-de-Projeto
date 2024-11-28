/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.strategy;

/**
 *
 * @author ALUNO
 */
import com.mycompany.strategy.PayStrategy;
/**
 * Order class. Doesn't know the concrete payment method (strategy) user has
 * picked. It uses common strategy interface to delegate collecting payment data
 * to strategy object. It can be used to save order to database.
 */
public class Order {
    // Variável que armazena o custo total do pedido, inicializada como 0
    private int totalCost = 0;
    // Variável que indica se o pedido está fechado, inicializada como falso
    private boolean isClosed = false;

    // Método que processa o pedido utilizando uma estratégia de pagamento
    public void processOrder(PayStrategy strategy) {
        // Coleta os detalhes do pagamento através da estratégia fornecida
        strategy.collectPaymentDetails();
        // Aqui poderíamos coletar e armazenar os dados de pagamento da estratégia.
    }

    // Método para definir o custo total do pedido, somando o novo custo ao existente
    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    // Método que retorna o custo total do pedido
    public int getTotalCost() {
        return totalCost;
    }

    // Método que verifica se o pedido está fechado
    public boolean isClosed() {
        return isClosed;
    }

    // Método que define o pedido como fechado
    public void setClosed() {
        isClosed = true;
    }
}

