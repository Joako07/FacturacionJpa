package com.udemycurso.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.udemycurso.app.entities.Factura;


//Creamos la vista PDF
@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// traigo la factura
		Factura factura = (Factura) model.get("factura");
		
		//Con el objeto PdfPTable de itext creo una tabla de 1 columna con 3 filas 
		PdfPTable table = new PdfPTable(1);
		table.addCell("Datos del Cliente");
		table.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
		table.addCell(factura.getCliente().getEmail());
		
		PdfPTable table2 = new PdfPTable(1);
		table2.addCell("Datos de la Factura");
		table2.addCell("Folio: " + factura.getId());
		table2.addCell("Descripción" + factura.getDescripcion());
		table2.addCell("Fecha" + factura.getCreateAt());
		
		//Guardo las tablas en el documento
		document.add(table);
		document.add(table2);
		
	}
}
