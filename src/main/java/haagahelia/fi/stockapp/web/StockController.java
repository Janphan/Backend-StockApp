package haagahelia.fi.stockapp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.ui.Model;
import haagahelia.fi.stockapp.domain.StockCategoryRepository;
import haagahelia.fi.stockapp.domain.Stock;
import haagahelia.fi.stockapp.domain.StockRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StockController {
    @Autowired
    private StockRepository repository;
    @Autowired
    private StockCategoryRepository screpository;

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

    // RESTful service to get stock by id
    @RequestMapping(value = "/stock/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Stock> findStockRest(@PathVariable("id") Long stockId) {
        return repository.findById(stockId);
    }

    // add new stock
    @RequestMapping(value = "/add")
    public String addStock(Model model) {
        model.addAttribute("stock", new Stock());
        model.addAttribute("stockCategories", screpository.findAll());
        return "addstock";
    }

    // save new stock
    @PostMapping(value = "/save")
    public String save(Stock stock) {
        repository.save(stock);
        return "redirect:stocklist";
    }

    // edit stock information
    @GetMapping(value = "/edit/{id}")
    public String editStock(@PathVariable("id") Long stockId, Model model) {
        model.addAttribute("stock", repository.findById(stockId));
        model.addAttribute("stockCategories", screpository.findAll());
        return "updatestock";
    }

    // delete stock
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/delete/{id}")
    public String deleteStock(@PathVariable("id") Long stockId) {
        repository.deleteById(stockId);
        return "redirect:../stocklist";
    }

    // log in
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    // search stock

    @GetMapping("/search")
    public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Stock> searchResults = null;

        if (keyword != null && !keyword.isEmpty()) {
            // Perform search operation using the findByName method
            searchResults = repository.findByKeyword(keyword);
        }
        model.addAttribute("searchResults", searchResults);

        return "searchform"; // Return the name of the HTML/Thymeleaf template
    }

}
