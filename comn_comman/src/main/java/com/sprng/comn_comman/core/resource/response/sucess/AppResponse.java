package com.sprng.comn_comman.core.resource.response.sucess;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.sprng.comn_comman.core.resource.response.error.ErrorResponseDto;

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
public class AppResponse<T> {

	private T data;
	
	//@ApiModelProperty(value = "Metadata Object.")
	private MetaDataResponseDto meta;
	
	//@ApiModelProperty(value = "Error Object.")
	private List<ErrorResponseDto> errors;
	
	//@ApiModelProperty(value = "Pagination Object.")
	private PagingResponseDto paging;
	
	private static final Integer STATUS_CODE_SUCCESS = 200;
	private static final String STATUS_SUCCESS = "Request Processed Successfully with Response.";
	private static final Integer STATUS_CODE_ERROR = 999;
	private static final String STATUS_ERROR = "Request contains Error Response.";
	private static final Integer STATUS_CODE_SUCCESS_EMPTY_DATA = 100;
	private static final String STATUS_SUCCESS_EMPTY_DATA = "Request Processed Successfully but response contains no data.";
	
	
	public static <T> AppResponse<T> accepted() {
		MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(HttpStatus.ACCEPTED.value()).message(HttpStatus.ACCEPTED.name())
				.build();
		return AppResponse.<T>builder().meta(metaDto).build();
	}
	
	public static <T> AppResponse<T> ok(T data) {
		
		MetaDataResponseDto metaDto = null;
		
		if (data != null) {
			metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_SUCCESS).message(STATUS_SUCCESS)
					.build();
		} else {
			metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_SUCCESS_EMPTY_DATA).message(STATUS_SUCCESS_EMPTY_DATA)
					.build();
		}
		
		return AppResponse.<T>builder().data(data).meta(metaDto).build();
	}
	
	public static <T> AppResponse<T> ok(T data, MetaDataResponseDto metaDto) {
				
		return AppResponse.<T>builder().data(data).meta(metaDto).build();
	}

	public static <T> AppResponse<T> okList(T dataList, int pageSize, int pageNumber, long totalRecords) {
		
		MetaDataResponseDto metaDto = null;
		
		if (dataList != null && totalRecords > 0) {
			metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_SUCCESS).message(STATUS_SUCCESS)
					.build();
		} else {
			metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_SUCCESS_EMPTY_DATA).message(STATUS_SUCCESS_EMPTY_DATA)
					.build();
		}
		
		PagingResponseDto pagingDto = PagingResponseDto.builder().pageNumber(pageNumber).pageSize(pageSize)
				.totalRecords(totalRecords).build();

		return AppResponse.<T>builder().data(dataList).meta(metaDto).paging(pagingDto).build();
	}

	/** Builds and returns the error response using the provided error details
	 * @param <T> the type of date element in this the returning AppResponse object
	 * @param status short message to identify the status of the request. (Ex: 400 Bad request etc.)
	 * @param title a title message for the error message
	 * @param detail detailed message about the error (Ex: A user with the given id does not exist)
	 * @return AppResponse which contains the error information
	 */
	public static <T> AppResponse<T> error(String status, String title, String detail) {
		MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
				.build();

		ErrorResponseDto errorDto = ErrorResponseDto.builder().status(status).title(title).detail(detail)
				.build();

		return AppResponse.<T>builder().data(null).meta(metaDto).errors(new ArrayList<ErrorResponseDto>(Arrays.asList(errorDto))).build();
	}

	
	/** Builds and returns the error response using the provided error details
	 * @param <T> the type of date element in this the returning AppResponse object
	 * @param status short message to identify the status of the request. (Ex: 400 Bad request etc.)
	 * @param title a title message for the error message
	 * @param detail detailed message about the error (Ex: A user with the given id does not exist)
	 * @return AppResponse which contains the error information
	 */
	public static <T> AppResponse<T> error(String code, String status, String title, String detail) {
		MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
				.build();

		ErrorResponseDto errorDto = ErrorResponseDto.builder().code(code).status(status).title(title).detail(detail)
				.build();

		return AppResponse.<T>builder().data(null).meta(metaDto).errors(new ArrayList<ErrorResponseDto>(Arrays.asList(errorDto))).build();
	}
	
	/** Builds and returns the error response using the provided error details
	 * @param <T> the type of date element in this the returning AppResponse object
	 * @param ErrorResponseDto Object
	 * @return AppResponse which contains the error information
	 */
	public static <T> AppResponse<T> error(ErrorResponseDto errorDto) {
		MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
				.build();
		
		return AppResponse.<T>builder().data(null).meta(metaDto).errors(new ArrayList<ErrorResponseDto>(Arrays.asList(errorDto))).build();
	}
	

	/** Builds and returns the error response using the provided error details
	 * @param <T> the type of date element in this the returning AppResponse object
	 * @param status short message to identify the status of the request. (Ex: 400 Bad request etc.)
	 * @param detail detailed message about the error (Ex: A user with the given id does not exist)
	 * @return AppResponse which contains the error information
	 */
	public static <T> AppResponse<T> error(String status, String detail) {
		MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
				.build();

		ErrorResponseDto errorDto = ErrorResponseDto.builder().status(status)/*.source(source).title(title)*/.detail(detail)
				.build();

		return AppResponse.<T>builder().data(null).meta(metaDto).errors(new ArrayList<ErrorResponseDto>(Arrays.asList(errorDto))).build();
	}

	/** Builds and returns the error response using the provided error details and response data
	 * @param <T> the type of date element in this the returning AppResponse object
	 * @param data the data object to be used in the response body
	 * @param title a title message for the error message
	 * @param detail detailed message about the error (Ex: A user with the given id does not exist)
	 * @return AppResponse which contains the response body together with error information
	 */
	public static <T> AppResponse<T> error(T data, /*String status, String source,*/ String title, String detail) {
		MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
				.build();

		ErrorResponseDto errorDto = ErrorResponseDto.builder()/*.status(status).source(source)*/.title(title).detail(detail)
				.build();

		return AppResponse.<T>builder().data(data).meta(metaDto).errors(new ArrayList<ErrorResponseDto>(Arrays.asList(errorDto))).build();
	}
}
