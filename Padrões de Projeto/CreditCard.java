/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.strategy;


public class CreditCard {
      // A variável 'amount' armazena o saldo disponível no cartão
    private int amount;
    private String number;
    private String date;
    private String cvv;
    
    
     /**
     * Construtor da classe CreditCard.
     * Inicializa o cartão com um saldo fixo de 100.000 unidades.
     * @param number número do cartão
     * @param date data de validade do cartão (formato 'mm/aa')
     * @param cvv código CVV do cartão
     */

    CreditCard(String number, String date, String cvv) {
        this.amount = 100_000;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }
    
    /**
     * Define o novo saldo disponível no cartão.
     * @param amount o novo saldo do cartão
     */

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    /**
     * Retorna o saldo atual do cartão.
     * @return o saldo disponível no cartão
     */

    public int getAmount() {
        return amount;
    }
}