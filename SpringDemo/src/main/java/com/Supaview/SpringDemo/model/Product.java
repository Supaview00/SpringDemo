package com.Supaview.SpringDemo.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "`product`")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = false, nullable = false)
	private String name;

	@Column(unique = false)
	private String image;

	@Column(unique = false, nullable = false)
	private int price;

	@Column(unique = false, nullable = false)
	private int stock;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Setter(AccessLevel.NONE)
	private Date updateDate;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Setter(AccessLevel.NONE)
	private Date CreateDate;

}
