package com.empapp.dto;

public class LoginResponseDTO {
    private String status;
    private String message;
    private UserDTO user;
	private String redirectUrl;

    public LoginResponseDTO(String status, String message, UserDTO user, String redirectUrl) {
        this.status = status;
        this.message = message;
        this.user = user;
		this.redirectUrl = redirectUrl;
    }
	
	private Long sessionId;
	
	public LoginResponseDTO(String status, String message, UserDTO user, String redirectUrl, Long sessionId) {
		this.status = status;
		this.message = message;
		this.user = user;
		this.redirectUrl = redirectUrl;
		this.sessionId = sessionId;
	}

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
	
	public String getRedirectUrl() { return redirectUrl; }
	public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }
}
