package kg.mega.natv.repository;

import kg.mega.natv.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

        @Query(value = "select tp.price_per_letter from tb_prices as tp " +
                        "join tb_channels as tch on tch.id = tp.channel_id " +
                        "where tp.channel_id = ?1 " +
                                "and tp.end_date = '2999-12-31 00:00:00' " +
                                "and tch.active = true ",
                nativeQuery = true)
        Optional<Double> findByChannelIdAndEndDate(Long id);
}
