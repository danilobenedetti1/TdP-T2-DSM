package br.com.dsm.trabalho_avaliativo_2.model;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GeradorFigurinha {

	public void gerarFigurinha(InputStream inputStream, String titulo, String nomeArquivo) throws IOException {

		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// criar um nova imagem em memória com transparência e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TYPE_INT_ARGB);

		// copiar a imagem original para a nova imagem (em memória)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0 , 0, null);

		// configurar a fonte para escrever na imagem
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
		graphics.setFont(fonte);
		graphics.setColor(Color.YELLOW);

		// centralizar o título na imagem
		FontMetrics fontMetrics = graphics.getFontMetrics();
		int tituloLargura = fontMetrics.stringWidth(titulo);
		int tituloAltura = fontMetrics.getHeight();
		int tituloX = (largura - tituloLargura) / 2;
		int tituloY = novaAltura - (tituloAltura / 2);

		// escrever uma mensagem na nova imagem
		graphics.drawString(titulo, tituloX, tituloY);

		String urlFigurinha = "saida/" + nomeArquivo + ".png";
		ImageIO.write(novaImagem, "png", new File(urlFigurinha));
	}
}
