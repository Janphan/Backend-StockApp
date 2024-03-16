package haagahelia.fi.stockapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.ui.Model;
// import haagahelia.fi.stockapp.domain.CategoryRepository;
import haagahelia.fi.stockapp.domain.Stock;
import haagahelia.fi.stockapp.domain.StockRepository;

@Controller
public class StockController {
    @Autowired
    private StockRepository repository;
    // @Autowired
    // private CategoryRepository cRepository;

    // show all stocks
    @RequestMapping(value = "/stocklist")
    public String stockList(Model model) {
        model.addAttribute("stocks", repository.findAll());
        return "stocklist";
    }

    // RESTful service to get all stocks
    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    public @ResponseBody List<Stock> stocktListRest() {
        return (List<Stock>) repository.findAll();
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
}
