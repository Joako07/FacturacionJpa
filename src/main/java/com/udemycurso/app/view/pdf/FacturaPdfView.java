package com.udemycurso.app.view.pdf;

import java.awt.Color;
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
//Se trabaja con wwww.developers.itextpdf.com
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
		
		PdfPCell cell = null; //Instancio para poder crear celdas
		
		//De esta forma podes modificar la celda para darle color,pading,etc 
		cell= new PdfPCell(new Phrase("Datos del Cliente"));
		cell.setBackgroundColor(new Color(184,218,255));
		cell.setPadding(8f);
		table.addCell(cell);
		
		//table.addCell("Datos del Cliente"); Si no Podes ponerlo así si no queres darle color de fondo ni nada por el estilo
		table.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
		table.addCell(factura.getCliente().getEmail());
		
		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(20);//Agrega un espacio
		
		cell= new PdfPCell(new Phrase("Datos de la Factura"));
		cell.setBackgroundColor(new Color(195,230,203));
		cell.setPadding(8f);
		table2.addCell(cell);
		
		//table2.addCell("Datos de la Factura");
		table2.addCell("Folio: " + factura.getId());
		table2.addCell("Descripción: " + factura.getDescripcion());
		table2.addCell("Fecha: " + factura.getCreateAt());
				
		//Creamos la tabla de los items
		//Header de la tabla con los nombres de campo
		PdfPTable table3 = new PdfPTable (4);
		table3.setWidths(new float [] {3.5f, 1, 1, 1}); //Con esto cambias el tamaño de fuente de cada columna
		table3.addCell("Producto");
		table3.addCell("Precio");
		table3.addCell("Cantidad");
		table3.addCell("Total");		
		
		//Acá genero cada linea con sus items correspondientes
		for(ItemFactura item: factura.getItems()) {
			table3.addCell(item.getProducto().getNombre());
			table3.addCell(item.getProducto().getPrecio().toString());	
			/*Centro los datos de la columna cantidad
			la cual estaba asi --> table3.addCell(item.getCantidad().toString());*/
			cell = new PdfPCell(new Phrase(item.getCantidad().toString()));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table3.addCell(cell);
			table3.addCell(item.calcularImporte().toString());
		}
		
		//Footer con el total
		
		cell = new PdfPCell(new Phrase("Total: "));
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
