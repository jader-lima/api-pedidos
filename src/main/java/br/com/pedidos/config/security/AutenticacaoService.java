package br.com.pedidos.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pedidos.dao.DaoUsuario;
import br.com.pedidos.model.Usuario;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private DaoUsuario daoUsuario;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = daoUsuario.findbyEmail(username);
		if(usuario != null) {
			return usuario;
		}
		throw new UsernameNotFoundException("dados inválidos");

	}

}
