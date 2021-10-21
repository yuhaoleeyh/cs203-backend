package csd.cs203project.security.cognito;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

import java.net.MalformedURLException;
import java.net.URL;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JWTProcessor {
	@Autowired
	private JwtConfiguration jwtConfiguration;

	@Bean
	public ConfigurableJWTProcessor configurableJWTProcessor() throws MalformedURLException {
		ResourceRetriever resourceRetriever =
				new DefaultResourceRetriever(jwtConfiguration.getConnectionTimeout(),
						jwtConfiguration.getReadTimeout());
						URL jwkSetURL= new URL(jwtConfiguration.getJwkUrl());
		JWKSource keySource= new RemoteJWKSet(jwkSetURL, resourceRetriever);
		ConfigurableJWTProcessor jwtProcessor= new DefaultJWTProcessor();
		JWSKeySelector keySelector= new JWSVerificationKeySelector(RS256, keySource);
		jwtProcessor.setJWSKeySelector(keySelector);
		return jwtProcessor;
	}

}
