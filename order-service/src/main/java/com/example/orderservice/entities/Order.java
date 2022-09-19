package com.example.orderservice.entities;

import com.example.orderservice.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column
    private String accountId;
    @Enumerated
    private PaymentType paymentType;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderLine> productList;
}
