package br.com.pedidos.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.pedidos.dao.DaoUsuario;
import br.com.pedidos.dto.TokenDto;
import br.com.pedidos.model.Usuario;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
/*Classes filter não aceitam ingestão de dependencia ,@Autowired não funciona*/
	
	private TokenService tokenService;
	
	private DaoUsuario daoUsuario;
	

	public AutenticacaoViaTokenFilter(TokenService tokenService, DaoUsuario daoUsuario) {
		this.tokenService = tokenService;
		this.daoUsuario = daoUsuario;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		String token = recuperarToken(request);
		System.out.println(token);
		boolean valido = tokenService.isTokenValido(token);
		System.out.println(valido);
		System.out.println("aquiii");
		
		if(valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private void autenticarCliente(String token) {
		int idUsuario = tokenService.getIdUsuario(token);
		System.out.println(idUsuario);
		
		try {
			Usuario usuario = (Usuario)daoUsuario.findbyId(Usuario.class, idUsuario);
			for (GrantedAuthority auth : usuario.getAuthorities()) {
				System.out.println(auth.getAuthority());
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}else {
			return token.substring(7,token.length());
		}
	}

}
