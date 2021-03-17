package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result add(Currency currency) {
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName){
            return new Result("Cuurency name have in db", false);
        }
        currencyRepository.save(currency);
        return new Result("Currency saved", true);
    }

    public List<Currency> getAll() {
        List<Currency> currencies = currencyRepository.findAll();
        return currencies;
    }

    public Currency getOne(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()){
            return new Currency();
        }
        Currency currency = optionalCurrency.get();
        return currency;
    }

    public Result update(Integer id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()){
            return new Result("Currency id not found", false);
        }
        Currency currencyDb = optionalCurrency.get();
        currencyDb.setName(currency.getName());

        currencyRepository.save(currencyDb);
        return new Result("Currency updated", true);
    }

    public Result delete(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()){
            return new Result("Currency id not found", false);
        }
        currencyRepository.deleteById(id);
        return new Result("Currency deleted", true);
    }
}
