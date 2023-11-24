package br.com.dsm.trabalho_avaliativo_2.model;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AppModel {

	public void gerarFigurinha(InputStream inputStream, String titulo, String nome) throws IOException {

		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// criar um nova imagem em memória com transparência e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 100;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TYPE_INT_ARGB);

		// copiar a imagem original para a nova imagem (em memória)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0 , 0, null);

		// escrever uma mensagem na nova imagem
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 35);
		graphics.setFont(fonte);
		graphics.setColor(Color.YELLOW);
		graphics.drawString(titulo, 50, novaAltura - 80);

		String url = "saida/" + nome + ".png";
		ImageIO.write(novaImagem, "png", new File(url));
	}
}
