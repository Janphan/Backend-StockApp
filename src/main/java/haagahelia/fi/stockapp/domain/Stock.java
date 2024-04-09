package haagahelia.fi.stockapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String expiredDate;
    private int quantity;
    private String description;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public StockCategory getStockCategory() {
        return stockCategory;
    }

    public void setStockCategory(StockCategory stockCategory) {
        this.stockCategory = stockCategory;
    }

    @ManyToOne
    @JoinColumn(name = "stockCategoryid")
    private StockCategory stockCategory;

    public Stock() {
    }

    public Stock(String name, String expiredDate, int quantity, String description, double price,
            StockCategory stockCategory) {
        super();
        this.name = name;
        this.expiredDate = expiredDate;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.stockCategory = stockCategory;
    }

}
