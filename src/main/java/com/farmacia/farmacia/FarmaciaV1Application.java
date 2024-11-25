package com.farmacia.farmacia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;

@SpringBootApplication
public class FarmaciaV1Application {

	public static void main(String[] args) {
		SpringApplication.run(FarmaciaV1Application.class, args);
		FileInputStream fis = new FileInputStream("/path/a/tu/plantilla.pdf");
		PdfReader reader = new PdfReader(fis);

		// Obtener todos los campos del formulario
		AcroFields form = reader.getAcroFields();

		// Obtener y mostrar todos los nombres de los campos
		Map<String, AcroFields.Item> fields = form.getFields();
		for (String fieldName : fields.keySet()) {
			System.out.println("Campo: " + fieldName);
		}
	}

}
