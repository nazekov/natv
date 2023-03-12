package kg.mega.natv.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.natv.enums.StatusOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_text_orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String clientFIO;

    String clientEmail;

    String clientPhone;

    String text;

    Double totalPrice;

    int countSymbols;

    @Enumerated(EnumType.STRING)
    StatusOrder status;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    Date createdDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderDetails> orderDetailsList;
}
