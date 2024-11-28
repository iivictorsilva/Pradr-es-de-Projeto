
package paystrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Concrete strategy. Implements credit card payment method.
 */
public class PayByCreditCard implements PayStrategy {
   // Buffer para ler entradas do console
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
     // A variável 'card' vai armazenar os dados do cartão de crédito do cliente
    private CreditCard card;

    
    /**
     * Coleta os dados do cartão de crédito do cliente.
     * Solicita ao usuário o número do cartão, a data de validade e o código CVV.
     */
    @Override
    public void collectPaymentDetails() {
        try {
               // Solicita o número do cartão ao usuário
            System.out.print("Digite o número do cartão: ");
            String number = READER.readLine();
              // Solicita a data de validade do cartão (formato 'mm/aa')
            System.out.print("Insira a data de validade do cartão 'mm/aa': ");
            String date = READER.readLine();
             // Solicita o código CVV do cartão
            System.out.print("Digite o código CVV: ");
            String cvv = READER.readLine();
             // Cria uma nova instância de CreditCard com os dados informados
            card = new CreditCard(number, date, cvv);

           
            // Aqui poderia ocorrer uma validação mais profunda do número do cartão
            // Por exemplo, validar a conformidade do número com o algoritmo Luhn
            // E verificar se a data de validade é válida (não expirada)
            

        } catch (IOException ex) {
             // Em caso de erro na leitura da entrada do usuário, o erro é impresso no console
            ex.printStackTrace();
        }
    }

    /**
     * Realiza o pagamento, se o cartão for válido e tiver fundos suficientes.
     * Após a validação do cartão, o pagamento é realizado e o saldo do cartão é atualizado.
     * @param paymentAmount valor a ser pago
     * @return true se o pagamento for bem-sucedido, false caso contrário
     */
    
    @Override
    public boolean pay(int paymentAmount) {
        // Verifica se o cartão foi inserido corretamente   
        if (cardIsPresent()) {
             // Se o cartão estiver presente, realiza o pagamento
            System.out.println("Pagando " + paymentAmount + " usando cartão de crédito.");
            // Atualiza o saldo do cartão após o pagamento
            card.setAmount(card.getAmount() - paymentAmount);
             // Retorna true indicando que o pagamento foi realizado
            return true;
        } else {
              // Se o cartão não foi inserido, retorna false
            return false;
        }
    }
    
     /**
     * Verifica se o cartão foi inserido.
     * @return true se o cartão estiver presente (não nulo), false caso contrário
     */

    private boolean cardIsPresent() {
          // Verifica se o cartão foi previamente inserido (não é nulo)
        return card != null;
    }
}