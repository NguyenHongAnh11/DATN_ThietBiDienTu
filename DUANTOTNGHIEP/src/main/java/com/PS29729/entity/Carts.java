package com.PS29729.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Cart")
public class Carts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Username", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @Column(name = "AddedDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

}
