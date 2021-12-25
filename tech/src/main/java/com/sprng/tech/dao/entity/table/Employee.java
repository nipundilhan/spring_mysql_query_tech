package com.sprng.tech.dao.entity.table;

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
import com.sprng.tech.dao.entity.table.meta.MstEmployeePosition;
import com.sprng.tech_comman.enums.CustomerTypeEnum;
import com.sprng.tech_comman.enums.EmployeeStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee")
@Entity
public class Employee {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "employee_no", nullable=true)
	private String employeeNo;

	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "person_id", nullable=true)
	private Person person;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "position_id", nullable=true)
	private MstEmployeePosition mstEmployeePosition;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "employee_status", length=30, nullable=false)
	private EmployeeStatusEnum EmployeeStatus;

}
