package com.sprng.tech.dao.wrapper;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@SqlResultSetMapping(name = "PersonDetailMapping", entities = {
        @EntityResult(entityClass = PersonDetailRespDto.class, fields = {
        		 @FieldResult(name = "id", column = "id"),
        		 @FieldResult(name = "firstName", column = "firstName"),
       		 	 @FieldResult(name = "mobile", column = "mobile"),
	    		 @FieldResult(name = "nic", column = "nic")
        }),
})

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PersonDetailRespDto {
	
	@Id
	@JsonProperty("id")
	private Long id;
	
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("mobile")
    private String mobile;
    
    @JsonProperty("nic")
    private String nic;

}

