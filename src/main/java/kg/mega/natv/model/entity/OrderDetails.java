package kg.mega.natv.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_order_details")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    boolean isActive;

    Double totalPrice;

    Double totalPriceWithDiscounts;

    @ManyToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    Channel channel;

    @ManyToOne
    @JoinColumn(name = "text_order_id", referencedColumnName = "id")
    TextOrder textOrder;

    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL)
    List<DatesOrderDetails> datesOrderDetails;
}