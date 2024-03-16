package haagahelia.fi.stockapp.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findByName(String name);
}
