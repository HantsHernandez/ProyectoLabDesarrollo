package com.farmacia.farmacia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
@SpringBootApplication
public class FarmaciaV1Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(FarmaciaV1Application.class, args);
		/*try {
			// Cargar el archivo HTML desde la carpeta resources/templates
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream("templates/factura.html");

			if (inputStream == null) {
				throw new IllegalArgumentException("Archivo no encontrado en resources/templates/factura.html");
			}

			// Leer el contenido del archivo HTML
			String html = new String(inputStream.readAllBytes());

			// Reemplazar variables dinámicas
			html = html.replace("{{facturaNumero}}", "0001-001");
			html = html.replace("{{fecha}}", "25/11/2024");
			html = html.replace("{{cliente}}", "Juan Pérez");

			// Generar filas de productos dinámicamente
			String productosHtml = """
					    <tr>
					        <td>Paracetamol</td>
					        <td>2</td>
					        <td>$5.00</td>
					        <td>$10.00</td>
					    </tr>
					    <tr>
					        <td>Ibuprofeno</td>
					        <td>1</td>
					        <td>$8.00</td>
					        <td>$8.00</td>
					    </tr>
					""";
			html = html.replace("{{#productos}}", productosHtml);
			html = html.replace("{{/productos}}", "");

			// Reemplazar el total
			html = html.replace("{{total}}", "18.00");

			// Guardar el PDF generado en la raíz del proyecto
			try (FileOutputStream os = new FileOutputStream("factura.pdf")) {
				PdfRendererBuilder builder = new PdfRendererBuilder();
				builder.useFastMode();
				builder.withHtmlContent(html, null);
				builder.toStream(os);
				builder.run();
			}

			System.out.println("Factura PDF generada exitosamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
