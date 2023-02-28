package kg.mega.natv.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_prices_text")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double pricePerSymbol;

    Date startDate;

    Date endDate;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    Channel channel;
}