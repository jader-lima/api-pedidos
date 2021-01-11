package br.com.pedidos.dao;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.pedidos.model.Produto;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DaoProdutosTest {
	



	@Test
	public void getProdutosbyDescriptionTest() {

		
		DaoProdutos daoProduto = new DaoProdutos();
		String descricao = "PS4 SLIM ALT 1";
		Produto produto  =  daoProduto.findProdutosbyDescription(descricao);
		Assert.assertNotNull(produto);
		Assert.assertEquals(descricao, produto.getDescricao());
	
	}

}
