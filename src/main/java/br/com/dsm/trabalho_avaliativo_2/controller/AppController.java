package br.com.dsm.trabalho_avaliativo_2.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dsm.trabalho_avaliativo_2.model.Figurinha;
import br.com.dsm.trabalho_avaliativo_2.model.GeradorFigurinha;

@Controller
@RequestMapping("/figurinha")
public class AppController {

	@GetMapping
	public ModelAndView exibirPagina() {
		return new ModelAndView("figurinha");
	}

	@PostMapping
	public ModelAndView gerarFigurinha(Figurinha figurinha) throws IOException {
		System.out.println(figurinha);

		ModelAndView modelAndView = new ModelAndView("figurinha");

		try {
			InputStream inputStream = new URL(figurinha.getUrlImagem()).openStream();
			String titulo = figurinha.getTitulo();
			String nomeArquivo = figurinha.getNomeArquivo();

			var gerador = new GeradorFigurinha();
			gerador.gerarFigurinha(inputStream, titulo, nomeArquivo);

			String caminhoFigurinha = "saida/" + nomeArquivo + ".png";
			String mensagem = "Figurinha gerada com sucesso. Caminho da figurinha: " + caminhoFigurinha;

			modelAndView.addObject("mensagem", mensagem);
			modelAndView.addObject("caminhoFigurinha", caminhoFigurinha);
			modelAndView.addObject("nomeArquivo", nomeArquivo);
		} catch (IOException e) {
			String mensagemErro = "Ocorreu um erro ao gerar a figurinha. Verifique o link da imagem e tente novamente.";
			modelAndView.addObject("mensagemErro", mensagemErro);
		}

		return modelAndView;
	}
}
