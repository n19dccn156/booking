package com.group.booking.Interfaces.Addons;

public interface JWTImplement {
    
    public String generateAccessToken(String subject);
    public String generateVerify(String subject);
    public String getSubject(String jwt);
    public String validateAndGetSubject(String jwt);
    public String validateJWT(String jwt);

}
