package com.tecsup.EduconnectBackEnd.Views;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tecsup.EduconnectBackEnd.models.Evento;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.util.List;
import java.util.Map;

@Component("evento/ver.pdf") // Asegúrate de que esta coincidencia sea correcta
public class EventoPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        document.open(); // Abre el documento antes de agregar contenido

        List<Evento> eventos = (List<Evento>) model.get("eventos");

        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = new PdfPCell(new Phrase("Lista de Eventos"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        PdfPTable tabla2 = new PdfPTable(3);
        tabla2.addCell("ID");
        tabla2.addCell("Titulo");
        tabla2.addCell("Descripcion");

        for (Evento evento : eventos) {
            tabla2.addCell(String.valueOf(evento.getId()));
            tabla2.addCell(evento.getTitulo());
            tabla2.addCell(evento.getDescripcion());
        }

        document.add(tabla);
        document.add(tabla2);
        document.close(); // Asegúrate de cerrar el documento
    }
}
