package kg.mega.natv.repository;

import kg.mega.natv.model.dto.IDiscountDto;
import kg.mega.natv.model.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query(value = "select discount from " +
                    "(select from_days_count, discount_percent as discount " +
                    "from tb_discounts " +
                    "where channel_id = ?1 and end_date = '2999-12-31 00:00:00' " +
                    "order by from_days_count desc) as sub_tb " +
                    "where ?2 >= from_days_count " +
                    "limit 1",
            nativeQuery = true)
    Optional<Double> findDiscount(Long channelId, Integer countDays);

    @Query(value = "select from_days_count as fromDaysCount, " +
                            "discount_percent as discountPercent " +
                    "from tb_discounts " +
                    "where channel_id = ?1 and end_date = '2999-12-31 00:00:00' ",
            nativeQuery = true)
    List<IDiscountDto> findDiscounts(Long channelId);
}
