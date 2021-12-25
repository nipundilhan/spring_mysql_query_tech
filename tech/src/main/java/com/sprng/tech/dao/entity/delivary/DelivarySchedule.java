package com.sprng.tech.dao.entity.delivary;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sprng.tech.dao.entity.order.Orders;
import com.sprng.tech.dao.entity.table.Address;
import com.sprng.tech.dao.entity.table.meta.MstArea;
import com.sprng.tech_comman.enums.DelivaryStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "delivary_schedule")
@Entity
public class DelivarySchedule {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "order_id", nullable=false)
	private Orders order;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "address_id", nullable=false)
	private Address address;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "delivary_team_id", nullable=false)
	private DelivaryTeam delivaryTeam;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status", length=30, nullable=false)
	private DelivaryStatusEnum status;
		
	@Column(name = "delivary_date")
	private Date delivaryDate;
	
	
}
