package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    //Create
    @PostMapping
    public Result addCurrency(@RequestBody Currency currency){
        Result result = currencyService.add(currency);
        return result;
    }

    //Read List
    @GetMapping
    public List<Currency> getCurrencies(){
        List<Currency> currencies = currencyService.getAll();
        return currencies;
    }

    //Read One
    @GetMapping("/{id}")
    public Currency getCurrency(@PathVariable Integer id){
        Currency currency = currencyService.getOne(id);
        return currency;
    }

    //Update
    @PutMapping("/{id}")
    public Result getCurrency(@PathVariable Integer id, @RequestBody Currency currency){
        Result result = currencyService.update(id, currency);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteCurremcy(@PathVariable Integer id){
        Result result = currencyService.delete(id);
        return result;
    }


}
