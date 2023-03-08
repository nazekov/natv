package kg.mega.natv.repository;

import kg.mega.natv.model.entity.DiscountText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DiscountTextRepository extends JpaRepository<DiscountText, Long> {

    @Query(value = "select discount from " +
                    "(select from_days_count, discount_percent as discount " +
                    "from tb_discounts_text " +
                    "where channel_id = ?1 and end_date = ?2 " +
                    "order by from_days_count desc) as sub_tb " +
                    "where ?3 >= from_days_count " +
                    "limit 1",
            nativeQuery = true)
    Optional<Double> findDiscount(Long channelId, Date date, Integer countDays);
}
