package haagahelia.fi.stockapp.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class StockCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockcategoryid;
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockCategory")
    private List<Stock> stocks;

    public StockCategory() {
    }

    public StockCategory(String name) {
        super();
        this.name = name;
    }

    public Long getStockCategoryid() {
        return stockcategoryid;
    }

    public void setStockCategoryid(Long stockcategoryid) {
        this.stockcategoryid = stockcategoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "StockCategory [stockcategoryid=" + stockcategoryid + ", name=" + name + "]";
    }
}
