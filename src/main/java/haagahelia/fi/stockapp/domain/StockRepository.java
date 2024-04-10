package haagahelia.fi.stockapp.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findByName(String name);

    @Query("SELECT s FROM Stock s WHERE LOWER(s.name) LIKE %:keyword%")
    List<Stock> findByKeyword(@Param("keyword") String keyword);

}
