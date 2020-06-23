package com.freetests4u.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.User;

@Component
public class AuthFilter implements Filter{

    private final ObjectMapper mapper;
    
    public AuthFilter() {
    	mapper = new ObjectMapper();
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if(req.getRequestURI().contains("login")) {
			chain.doFilter(request, response);
			return;
		}
		
		try {
		String token = req.getHeader(SecurityConstants.HEADER_STRING);
				if(token==null||!token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
					throw new Exception(SecurityConstants.HEADER_STRING+" missing");
				}
				
				String jwtToken = token.substring(7);
				Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET);
				
				JWTVerifier verifier = JWT.require(algorithm)
					        .withIssuer("freetests4u.com")
					        .build(); //Re
				DecodedJWT jwt = verifier.verify(jwtToken);
			    User u = jwt.getHeaderClaim("userInfo").as(User.class);
			    req.setAttribute("userInfo", u);
			    		}
		catch(Exception e) {
			e.printStackTrace();			
//			System.out.println("just check");
			res.setContentType("application/json");
			res.setStatus(401);
		     GenericResponseObject<User> re = new GenericResponseObject<User>(null,"Authentication failed",true);
	        mapper.writeValue(res.getWriter(), re);
		}

		System.out.println("Request URI is: " + req.getRequestURI());
		chain.doFilter(request, response);
		System.out.println("Response Status Code is: " + res.getStatus());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}