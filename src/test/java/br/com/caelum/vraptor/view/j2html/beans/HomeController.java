package br.com.caelum.vraptor.view.j2html.beans;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.view.j2html.config.J2HtmlRenderer;

@Controller
public class HomeController {								
	
	@Path({"/", ""})
	@J2HtmlRenderer(HomeRenderer.class)
	public Client home() {
		Client client = new Client();
		client.setLogin("admin");
		return client;
	}
	
}