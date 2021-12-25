package com.sprng.comn_comman.core.resource.response.sucess;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataResponseDto {

	//@ApiModelProperty(value = "Response code for API response.", example = "1000")
	private Integer code;
	
	//@ApiModelProperty(value = "Response message for API response.", example = "Request processed successfully")
	private String message;
}
