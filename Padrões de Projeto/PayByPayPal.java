/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.strategy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class PayByPayPal implements PayStrategy {
    
    // Simula um banco de dados de usuários (nome de usuário -> e-mail)
    
    private static final Map<String, String> DATA_BASE = new HashMap<>();
    
     // Buffer para ler entradas do console
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    
      // Variáveis para armazenar os dados do usuário
    private String email;
    private String password;
    private boolean signedIn;
    
     // Inicializa o "banco de dados" com alguns usuários fictícios
      static {
        DATA_BASE.put("victoraugusto", "victor@a.com");
        DATA_BASE.put("qwerty", "john@amazon.eu");
    }

   /**
     * Coleta os dados de pagamento do usuário.
     * Este método solicita que o usuário insira seu e-mail e senha até que a autenticação seja bem-sucedida.
     */
    @Override
    public void collectPaymentDetails() {
        try {
              // Continua pedindo os dados até o usuário estar autenticado
            while (!signedIn) {
                  // Solicita o e-mail do usuário
                System.out.print("Digite o e-mail do usuário: ");
                email = READER.readLine();
                 // Solicita a senha do usuário
                System.out.print("Digite a senha: ");
                password = READER.readLine();
                   // Verifica as credenciais fornecidas
                if (verify()) {
                    System.out.println("A verificação de dados foi bem-sucedida.");
                } else {
                    System.out.println("E-mail ou senha errada!");
                }
            }
        } catch (IOException ex) {
              // Se ocorrer um erro ao ler a entrada do usuário, ele é exibido no console
            ex.printStackTrace();
        }
    }
    
     /**
     * Verifica se o e-mail e a senha fornecidos são válidos.
     * @return true se a autenticação for bem-sucedida, false caso contrário
     */
    
    private boolean verify() {
           // Verifica se o nome de usuário (email) está no "banco de dados" e se a senha corresponde
        setSignedIn(email.equals(DATA_BASE.get(password)));  // Erro de lógica aqui: deveria verificar se a senha corresponde ao email, e não o contrário.
        return signedIn;
    }

    /**
     * Processa o pagamento do valor fornecido, se o usuário estiver autenticado.
     * @param paymentAmount valor do pagamento
     * @return true se o pagamento for realizado com sucesso, false caso contrário
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
              // Se o usuário estiver autenticado, realiza o pagamento
            System.out.println("Pagando " + paymentAmount + " usando o PayPal.");
            return true;
        } else {
              // Caso contrário, o pagamento não pode ser processado
            return false;
        }
    }
    
     /**
     * Define o estado de autenticação do usuário.
     * @param signedIn true se o usuário estiver autenticado, false caso contrário
     */
    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}

