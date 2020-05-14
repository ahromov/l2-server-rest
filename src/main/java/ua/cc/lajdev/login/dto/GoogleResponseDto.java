package ua.cc.lajdev.login.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "success", "challenge_ts", "hostname", "score", "action", "error-codes" })
public class GoogleResponseDto {

//    "success": true,
//    "challenge_ts": "2019-11-27T13:29:54Z",
//    "hostname": "lajdev.cc.ua",
//    "score": 0.9,
//    "action": "login"

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("challenge_ts")
	private String challengeTs;

	@JsonProperty("hostname")
	private String hostname;

	@JsonProperty("score")
	private double score;

	@JsonProperty("action")
	private String action;

	@JsonProperty("error-codes")
	private List<String> errorCodes;

	@JsonProperty("success")
	public boolean isSuccess() {
		return success;
	}

	@JsonProperty("success")
	public void setSuccess(boolean success) {
		this.success = success;
	}

	@JsonProperty("challenge_ts")
	public String getChallengeTs() {
		return challengeTs;
	}

	@JsonProperty("challenge_ts")
	public void setChallengeTs(String challengeTs) {
		this.challengeTs = challengeTs;
	}

	@JsonProperty("hostname")
	public String getHostname() {
		return hostname;
	}

	@JsonProperty("hostname")
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@JsonProperty("score")
	public double getScore() {
		return score;
	}

	@JsonProperty("score")
	public void setScore(double score) {
		this.score = score;
	}

	@JsonProperty("action")
	public String getAction() {
		return action;
	}

	@JsonProperty("action")
	public void setAction(String action) {
		this.action = action;
	}

	@JsonProperty("error-codes")
	public List<String> getErrorCodes() {
		return errorCodes;
	}

	@JsonProperty("error-codes")
	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

}