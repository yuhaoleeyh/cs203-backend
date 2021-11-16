package csd.cs203project.security.cognito;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "com.kb.jwt.aws"
)
public class JwtConfiguration {
    private String userPoolId;
    private String identityPoolId;
    private String jwkUrl;
    private String region = "ap-southeast-1";
    private String userNameField = "cognito:username";
    private int connectionTimeout = 2000;
    private int readTimeout = 2000;
    private String httpHeader = "Authorization";

    public JwtConfiguration() {
    }

    
    /** 
     * Get URL for JWK
     * @return String JwkUrl
     */
    public String getJwkUrl() {
        return this.jwkUrl != null && !this.jwkUrl.isEmpty() ? this.jwkUrl : String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", this.region, this.userPoolId);
    }

    
    /** 
     * Get URL for Cognito Identity Pool
     * @return String
     */
    public String getCognitoIdentityPoolUrl() {
        return String.format("https://cognito-idp.%s.amazonaws.com/%s", this.region, this.userPoolId);
    }

    
    /** 
     * Get User Pool Id
     * @return String
     */
    public String getUserPoolId() {
        return userPoolId;
    }

    
    /** 
     * Set User Pool Id
     * @param userPoolId
     */
    public void setUserPoolId(String userPoolId) {
        this.userPoolId = userPoolId;
    }

    
    /** 
     * Get Identity Pool Id
     * @return String
     */
    public String getIdentityPoolId() {
        return identityPoolId;
    }

    
    /** 
     * Set Identity Pool Id
     * @param identityPoolId
     */
    public void setIdentityPoolId(String identityPoolId) {
        this.identityPoolId = identityPoolId;
    }

    
    /** 
     * Set Jwk Url
     * @param jwkUrl
     */
    public void setJwkUrl(String jwkUrl) {
        this.jwkUrl = jwkUrl;
    }

    
    /** 
     * Get Region
     * @return String
     */
    public String getRegion() {
        return region;
    }

    
    /** 
     * Set Region
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    
    /** 
     * Get UserNameField
     * @return String
     */
    public String getUserNameField() {
        return userNameField;
    }

    
    /** 
     * Set UserNameField
     * @param userNameField
     */
    public void setUserNameField(String userNameField) {
        this.userNameField = userNameField;
    }

    
    /** 
     * Get Connection Timeout
     * @return int
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    
    /** 
     * Set Connection Timeout
     * @param connectionTimeout
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    
    /** 
     * Get Read Timeout
     * @return int
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    
    /** 
     * Set Read Timeout
     * @param readTimeout
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    
    /** 
     * Get HTTP Header
     * @return String
     */
    public String getHttpHeader() {
        return httpHeader;
    }

    
    /** 
     * Set HTTP Header
     * @param httpHeader
     */
    public void setHttpHeader(String httpHeader) {
        this.httpHeader = httpHeader;
    }
}
