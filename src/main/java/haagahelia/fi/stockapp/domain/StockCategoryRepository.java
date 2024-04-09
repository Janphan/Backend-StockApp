package haagahelia.fi.stockapp.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StockCategoryRepository extends CrudRepository<StockCategory, Long> {

    List<StockCategory> findByName(String name);

}