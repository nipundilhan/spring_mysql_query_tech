package com.sprng.comn_comman.core.resource.response.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

	//@ApiModelProperty(value = "error status if available.", required=false, example= "FAILED")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String status;
	
	//@ApiModelProperty(value = "error code if available.", required=false, example= "300")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
    private String code;
	
	//@ApiModelProperty(value = "error source if available.", required=false, example= "generalServiceOperation.")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String source;
	
	//@ApiModelProperty(value = "error message title if available.", required=false, example= "Internal Server Error. Please contact Support.")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String title;
	
	//@ApiModelProperty(value = "error details if available.", required=false, example= "Internal Server Error. Please refer to the log file and trace the error via the request ID.")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String detail;
	
}
