package enset.ma.orderservice.entities;

import enset.ma.orderservice.enums.OrderState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Table(name = "ORDERS")
public class Order {
    @Id
    private String orderId;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
}
