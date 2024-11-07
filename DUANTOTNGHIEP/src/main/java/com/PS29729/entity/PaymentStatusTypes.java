package com.PS29729.entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PaymentStatusTypes")
public class PaymentStatusTypes implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "StatusTypes", columnDefinition = "nvarchar(max)", nullable = false)
    private String statusTypes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusTypes() {
		return statusTypes;
	}

	public void setStatusTypes(String statusTypes) {
		this.statusTypes = statusTypes;
	}
    

}
