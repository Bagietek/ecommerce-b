package pl.akademiaspecjalistowit.ecommerce.client.currency;

import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.currency.exception.CurrencyAmountException;
import pl.akademiaspecjalistowit.ecommerce.client.currency.model.AccountCurrency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyExchange {
    private final String MAXIMUM_SINGULAR_FOUNDS_INCREASE = "500";
    private final String EUR_EXCHANGE_RATE_INTO_PLN = "4.32";
    private final String USD_EXCHANGE_RATE_INTO_PLN = "4.00";
    private final String PLN_EXCHANGE_RATE_INTO_USD = "0.25";
    private final String PLN_EXCHANGE_RATE_INTO_EUR = "0.23";
    private Map<AccountCurrency, BigDecimal> currencyExchangeRatesIntoPln;
    private Map<AccountCurrency, BigDecimal> currencyExchangeFromPln;
    public CurrencyExchange() {
        this.currencyExchangeRatesIntoPln = new HashMap<>();
        this.currencyExchangeFromPln = new HashMap<>();
        currencyExchangeRatesIntoPln.put(AccountCurrency.EUR, new BigDecimal(EUR_EXCHANGE_RATE_INTO_PLN));
        currencyExchangeRatesIntoPln.put(AccountCurrency.USD, new BigDecimal(USD_EXCHANGE_RATE_INTO_PLN));
        currencyExchangeRatesIntoPln.put(AccountCurrency.PLN, BigDecimal.ONE);
        currencyExchangeFromPln.put(AccountCurrency.USD,new BigDecimal(PLN_EXCHANGE_RATE_INTO_USD));
        currencyExchangeFromPln.put(AccountCurrency.EUR, new BigDecimal(PLN_EXCHANGE_RATE_INTO_EUR));
        currencyExchangeFromPln.put(AccountCurrency.PLN, BigDecimal.ONE);
    }

    public BigDecimal exchangeIntoAccountsSelected(BigDecimal amount, AccountCurrency accountSelectedCurrency, AccountCurrency incomingFoundsCurrency){
        if(accountSelectedCurrency.equals(incomingFoundsCurrency)){
            return amount;
        }
        // exchange into pln -> exchange into what clients wants
        BigDecimal exchangedAmount = exchangeIntoPln(amount,incomingFoundsCurrency);
        if(exchangedAmount.compareTo(new BigDecimal(MAXIMUM_SINGULAR_FOUNDS_INCREASE)) > 0){
            throw new CurrencyAmountException();
        }
        return exchangeFromPln(exchangedAmount,accountSelectedCurrency);
    }

    public BigDecimal exchangeIntoPln(BigDecimal amount, AccountCurrency accountCurrency){
        return amount.multiply(currencyExchangeRatesIntoPln.get(accountCurrency));
    }

    public BigDecimal exchangeFromPln(BigDecimal amount, AccountCurrency accountCurrency){
        return amount.multiply(currencyExchangeFromPln.get(accountCurrency));
    }
}
