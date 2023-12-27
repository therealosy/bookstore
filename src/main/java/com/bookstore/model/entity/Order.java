package com.bookstore.model.entity;

import com.bookstore.model.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @JsonIgnore
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
    private List<Book> books;

    @Column(nullable = false)
    private Long total;

    @Column(nullable = false)
    private Boolean hasPaid;

    @Column(nullable = false)
    private String orderReference;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date dateCheckedOut;

    @Override
    public String toString(){
        return "Order{"
                +"orderReference='" + this.orderReference + "', "
                +"total=" + this.total + ", "
                +"hasPaid=" +this.hasPaid + ", "
                +"dateCheckedOut=" + this.dateCheckedOut + ", "
                +"books=" + this.books
                +" }";
    }
}
