package kg.mega.natv.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_dates_order_details")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DatesOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Date date;

    @ManyToOne
    @JoinColumn(name = "order_details_Id", referencedColumnName = "id")
    OrderDetails orderDetails;
}
