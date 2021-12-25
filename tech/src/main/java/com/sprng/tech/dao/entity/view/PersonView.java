package com.sprng.tech.dao.entity.view;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "person_view")
@Entity
public class PersonView {

	@Id
	@Column(name = "row_num")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rowNum;

	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "bmi" , nullable=true)
	private BigDecimal bmi;
	
	@Column(name = "nic")
	private String nic;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "contact_value")
	private String contactValue;
	
	@Column(name = "profession_id")
	private Long professionId;

	@Column(name = "profession_name")
	private String professionName;
	
	@Column(name = "profession_area_id")
	private Long professionAreaId;

	@Column(name = "profession_area_name")
	private String professionAreaName;
	
	@Column(name = "civil_status_code")
	private String civilStatusCode;

	@Column(name = "civil_status_name")
	private String civilStatusName;	
	
	@Column(name = "area_id")
	private Long areaId;

	@Column(name = "area_name")
	private String areaName;
}
