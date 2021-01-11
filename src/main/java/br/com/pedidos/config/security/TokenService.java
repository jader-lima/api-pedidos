package br.com.pedidos.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.pedidos.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${pedido.jwt.expiration}")
	private String expiration;
	
	@Value("${pedido.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication ) {
		
		Usuario logado = (Usuario)authentication.getPrincipal();
		Date hoje = new Date();
		
		Date dataexpiratacao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API Pedidos")
				.setSubject(Integer.toString(logado.getId()) )
				.setIssuedAt(hoje)
				.setExpiration(dataexpiratacao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
				
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}catch(Exception e) {		
			return false;
		}
		
				
		
	}

	public int getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return  Integer.parseInt(claims.getSubject());
		
	}



}
