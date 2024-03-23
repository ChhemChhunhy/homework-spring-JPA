package co.istad.springhomeworkjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String uuid;
    @Column(unique = true,nullable = false,length = 40)
    private String name;
    private Double price;
    private Integer qty;
    private LocalDateTime importDate;
    private Boolean status;
}
