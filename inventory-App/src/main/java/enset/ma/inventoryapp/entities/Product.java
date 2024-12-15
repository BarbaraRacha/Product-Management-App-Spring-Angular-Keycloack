package enset.ma.inventoryapp.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder @ToString
public class Product {
    @Id
    private  String id;
    private  String name;
    private  double price;
    private  int quatity;

}
