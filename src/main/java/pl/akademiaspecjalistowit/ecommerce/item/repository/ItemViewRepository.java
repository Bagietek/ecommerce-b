package pl.akademiaspecjalistowit.ecommerce.item.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemViewRepository extends JpaRepository<ItemView, Long> {
    List<ItemView> findByCategoryName(String category);
    @Query("SELECT new ItemView(name,description,price,categoryName,numberOfProducts) FROM ItemView " +
            "WHERE (:category IS NULL OR categoryName = :category) AND price BETWEEN :minPrice AND :maxPrice")
    List<ItemView> itemSearch(@Param("minPrice") BigDecimal minPrice,
                              @Param("maxPrice") BigDecimal maxPrice,
                              @Param("category") String category,
                              Pageable pageable);
}
