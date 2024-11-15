package com.tecsup.EduconnectBackEnd.Views;

import com.tecsup.EduconnectBackEnd.models.Evento;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("evento/ver.xlsx") // Asegúrate de que esta coincidencia sea correcta
public class EventoXlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"eventos.xlsx\"");

        List<Evento> eventos = (List<Evento>) model.get("eventos");
        Sheet sheet = workbook.createSheet("Lista de Eventos");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Titulo");
        header.createCell(2).setCellValue("Descripcion");

        int rowNum = 1;
        for (Evento evento : eventos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(evento.getId());
            row.createCell(1).setCellValue(evento.getTitulo());
            row.createCell(2).setCellValue(evento.getDescripcion());
        }
    }
}
