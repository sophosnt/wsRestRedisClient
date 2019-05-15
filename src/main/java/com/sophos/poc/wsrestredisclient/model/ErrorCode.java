package com.sophos.poc.wsrestredisclient.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-13T20:33:13.018Z")

public class ErrorCode {
	@JsonProperty("HttpCode")
	private String httpCode = null;

	@JsonProperty("HttpMessage")
	private String httpMessage = null;

	@JsonProperty("MoreInformation")
	private String moreInformation = null;

	public ErrorCode httpCode(String httpCode) {
		this.httpCode = httpCode;
		return this;
	}

	@Size(max = 5)
	public String getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

	public ErrorCode httpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
		return this;
	}

	@Size(max = 500)
	public String getHttpMessage() {
		return httpMessage;
	}

	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}

	public ErrorCode moreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
		return this;
	}

	@Size(max = 1000)
	public String getMoreInformation() {
		return moreInformation;
	}

	public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ErrorCode errorCode = (ErrorCode) o;
		return Objects.equals(this.httpCode, errorCode.httpCode)
				&& Objects.equals(this.httpMessage, errorCode.httpMessage)
				&& Objects.equals(this.moreInformation, errorCode.moreInformation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(httpCode, httpMessage, moreInformation);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ErrorCode {\n");

		sb.append("    httpCode: ").append(toIndentedString(httpCode)).append("\n");
		sb.append("    httpMessage: ").append(toIndentedString(httpMessage)).append("\n");
		sb.append("    moreInformation: ").append(toIndentedString(moreInformation)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
