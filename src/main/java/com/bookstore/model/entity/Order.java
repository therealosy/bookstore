package com.bookstore.model.entity;

import com.bookstore.model.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="orderItems",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private Set<Book> books;

    @Column(nullable = false)
    private Long total;

    @Column(nullable = false)
    private Boolean hasPaid;

    @Column(nullable = false)
    private String orderReference;


    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date dateCheckedOut;
}
