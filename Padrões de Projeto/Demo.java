    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.strategy;

/**
 *
 * @author ALUNO
 */
import com.mycompany.strategy.Order;
import com.mycompany.strategy.PayByCreditCard;
import com.mycompany.strategy.PayByPayPal;
import com.mycompany.strategy.PayStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * World first console e-commerce application.
 */
public class Demo {
    
    // Mapa que associa os produtos a seus preços
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    
    // BufferedReader para ler a entrada do usuário
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    // Instância de Order para processar a compra
    private static Order order = new Order();
    
    // Instância de PayStrategy, que será definida dependendo do método de pagamento escolhido
    private static PayStrategy strategy;

    // Bloco estático que inicializa o preço dos produtos
    static {
        priceOnProducts.put(1, 2200);  // Produto 1 - Placa mãe
        priceOnProducts.put(2, 1850);  // Produto 2 - CPU
        priceOnProducts.put(3, 1100);  // Produto 3 - HDD
        priceOnProducts.put(4, 890);   // Produto 4 - Memória
    }

    /**
     * Método principal da aplicação. Simula o processo de compras e pagamento.
     */
    public static void main(String[] args) throws IOException {
        
        // Enquanto o pedido não for fechado, o processo de compra continua
        while (!order.isClosed()) {
            int cost;
            String continueChoice;

            // Loop para o cliente selecionar produtos
            do {
                System.out.print("Por favor, selecione um produto:" + "\n" +
                        "1 - Placa mãe" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memória" + "\n");
                
                // Lê a escolha do cliente para o produto
                int choice = Integer.parseInt(reader.readLine());
                
                // Recupera o preço do produto baseado na escolha
                cost = priceOnProducts.get(choice);
                
                // Pergunta a quantidade desejada do produto
                System.out.print("Quantas unidades? ");
                int count = Integer.parseInt(reader.readLine());
                
                // Atualiza o custo total com base na quantidade de produtos selecionados
                order.setTotalCost(cost * count);
                
                // Pergunta se o cliente deseja continuar comprando
                System.out.print("Deseja continuar selecionando produtos? S/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("S"));

            // Se o método de pagamento ainda não foi escolhido, solicita que o cliente escolha
            if (strategy == null) {
                System.out.println("Por favor, selecione uma forma de pagamento:" + "\n" +
                        "1 - PayPal" + "\n" +
                        "2 - Cartão de crédito");
                
                // Lê a escolha do método de pagamento
                String paymentMethod = reader.readLine();

                // Cria a estratégia de pagamento com base na escolha do cliente
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();  // Seleciona PayPal
                } else {
                    strategy = new PayByCreditCard();  // Seleciona Cartão de Crédito
                }
            }

            // O objeto Order delega a coleta de dados de pagamento para o objeto Strategy
            // Isso permite que a coleta de dados de pagamento seja diferente para cada estratégia
            order.processOrder(strategy);

            // Pergunta ao cliente se ele deseja pagar ou continuar comprando
            System.out.print("Pagar " + order.getTotalCost() + " unidades ou continuar comprando? P/C: ");
            String proceed = reader.readLine();
            
            if (proceed.equalsIgnoreCase("P")) {
                // Se o cliente escolher pagar, a estratégia de pagamento é utilizada para processar o pagamento
                if (strategy.pay(order.getTotalCost())) {
                    System.out.println("O pagamento foi bem-sucedido.");
                } else {
                    System.out.println("FALHAR! Por favor, verifique seus dados.");
                }
                // Marca o pedido como fechado após o pagamento
                order.setClosed();
            }
        }
    }
}