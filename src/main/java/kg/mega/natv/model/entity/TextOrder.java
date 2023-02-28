package kg.mega.natv.model.entity;

import kg.mega.natv.enums.StatusOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_text_orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TextOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double totalSumPrice;

    @Enumerated(EnumType.STRING)
    StatusOrder status;

    Date startDate;

    Date endDate;

    @ManyToOne
    @JoinColumn(name = "text_advs_id")
    TextAdvertisement textAdvertisement;

    @OneToOne
    @JoinColumn(name = "client_id")
    Client client;
}
