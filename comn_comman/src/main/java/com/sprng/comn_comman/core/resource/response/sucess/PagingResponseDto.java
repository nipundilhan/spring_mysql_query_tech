package com.sprng.comn_comman.core.resource.response.sucess;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class PagingResponseDto {
	

	@JsonProperty("page_number")
	private int pageNumber;
	
	@JsonProperty("page_size")
	private int pageSize;
	
	@JsonProperty("total_records")
	private long totalRecords;
	
	@JsonProperty("total_pages")
	private long totalPages;

}
