package br.com.dsm.trabalho_avaliativo_2.controller;


import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import br.com.dsm.trabalho_avaliativo_2.model.Figurinha;
import br.com.dsm.trabalho_avaliativo_2.model.AppModel;

@Controller
@RequestMapping("/figurinha")
public class AppController {

	@GetMapping
	public ModelAndView view() {
		return new ModelAndView("figurinha");
	}

	@PostMapping
	public ModelAndView gerarFigurinha(Figurinha figurinha) throws IOException {
		System.out.println(figurinha);

		ModelAndView modelAndView = new ModelAndView("figurinha");

		try {
			InputStream inputStream = new URL(figurinha.getUrl()).openStream();
			String titulo = figurinha.getTitulo();
			String nome = figurinha.getNome();

			var gerador = new AppModel();
			gerador.gerarFigurinha(inputStream, titulo, nome);

			String mensagem = "Figurinha criada com sucesso.";
			modelAndView.addObject("mensagem", mensagem);
			modelAndView.addObject("nome", nome);
		} catch (IOException e) {
			String mensagemErro = "Poxa, isso não funcionou direito. Por favor, verifique o link digitado.";
			modelAndView.addObject("mensagemErro", mensagemErro);
		}

		return modelAndView;
	}
}
