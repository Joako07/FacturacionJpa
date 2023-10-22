package com.udemycurso.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.udemycurso.app.entities.Factura;
import com.udemycurso.app.entities.ItemFactura;


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
		table.setSpacingAfter(20);//Agrega un espacio
		table.addCell("Datos del Cliente");
		table.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
		table.addCell(factura.getCliente().getEmail());
		
		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(20);//Agrega un espacio
		table2.addCell("Datos de la Factura");
		table2.addCell("Folio: " + factura.getId());
		table2.addCell("Descripción: " + factura.getDescripcion());
		table2.addCell("Fecha: " + factura.getCreateAt());
				
		//Creamos la tabla de los items
		//Header de la tabla con los nombres de campo
		PdfPTable table3 = new PdfPTable (4);
		table3.addCell("Producto");
		table3.addCell("Precio");
		table3.addCell("Cantidad");
		table3.addCell("Total");
		
		//Acá genero cada linea con sus items correspondientes
		for(ItemFactura item: factura.getItems()) {
			table3.addCell(item.getProducto().getNombre());
			table3.addCell(item.getProducto().getPrecio().toString());
			table3.addCell(item.getCantidad().toString());
			table3.addCell(item.calcularImporte().toString());
		}
		
		//Footer con el total
		
		PdfPCell cell = new PdfPCell(new Phrase("Total: "));
		cell.setColspan(3); // para que ocupe 3 columnas
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT); //Alineo el texto a la derecha
		table3.addCell(cell);//La paso a la tabla 3
		table3.addCell(factura.getTotal().toString());
		
		//Guardo las tablas en el documento
		document.add(table);
		document.add(table2);
		document.add(table3);
		
	}
}
