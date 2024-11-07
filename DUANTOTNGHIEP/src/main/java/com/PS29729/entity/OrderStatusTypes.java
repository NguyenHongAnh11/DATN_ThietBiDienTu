package com.PS29729.entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OrderStatusTypes")
public class OrderStatusTypes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "StatusTypes", columnDefinition = "nvarchar(max)", nullable = false)
    private String statusTypes;

}
