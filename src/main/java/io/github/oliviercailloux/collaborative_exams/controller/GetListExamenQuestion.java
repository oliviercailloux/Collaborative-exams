package io.github.oliviercailloux.collaborative_exams.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionAdapter;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Path("ExportPdf")
public class GetListExamenQuestion {

	private Document document;

	final Font Title = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
	final Font Subtitles = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	final Font Smalltext = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

	public static PdfPCell getCell(String text, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public Document createTitleDocument() throws DocumentException {
		document.open();
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		Calendar prevYear = Calendar.getInstance();
		prevYear.add(Calendar.YEAR, -1);
		table.addCell(getCell("Plateforme Collaborative Examens", PdfPCell.ALIGN_LEFT));
		table.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table.addCell(getCell("Année scolaire : " + prevYear.get(Calendar.YEAR) + "/"
				+ Calendar.getInstance().get(Calendar.YEAR) + "", PdfPCell.ALIGN_RIGHT));
		document.add(table);
		Paragraph preface2 = new Paragraph("EXAMEN ECRIT", Title);
		preface2.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(preface2);
		Paragraph preface3 = new Paragraph("Culture Générale", Subtitles);
		preface3.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(preface3);
		Paragraph preface4 = new Paragraph("Aucun document autorisé. Durée de l'examen 2 deux heures.", Smalltext);
		preface3.add(new Paragraph(" "));
		preface3.add(new Paragraph(" "));
		preface3.add(new Paragraph(" "));
		document.add(preface4);
		document.close();
		return document;
	}

	public Document createBodyDocument(JsonArray questionIdsAsJson) throws DocumentException {
		document.open();
		for (int i = 0; i < questionIdsAsJson.size(); i++) {
			try {
				// Find an question that is not exists in the database will
				// throw an exception.
				Question question = questionService.findQuestion(questionIdsAsJson.getInt(i));
				Paragraph preface = new Paragraph("", Title);
				PdfPTable table0 = new PdfPTable(3);
				table0.setWidthPercentage(100);
				table0.addCell(getCell(question.getPhrasing(), PdfPCell.ALIGN_LEFT));
				document.add(table0);
				document.add(preface);
			} catch (EntityNotFoundException e) {
				e.getMessage();
			}
		}
		document.close();
		return document;
	}

	@Inject
	QuestionService questionService;

	@Inject
	QuestionAdapter questionAdapter;

	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/pdf")
	public Response asPdf(JsonArray questionIdsAsJson) throws IOException, DocumentException {

		File file = new File("C:\\tmp\\givelistExamenQuestion.pdf");
		FileInputStream fileInputStream = new FileInputStream(file);
		PdfWriter.getInstance(document, new FileOutputStream(file));
		ResponseBuilder responseBuilder = Response.ok((Object) fileInputStream);
		responseBuilder.type("application/pdf");
		responseBuilder.header("Content-Disposition", "filename=givelistExamenQuestion.pdf");
		document = createTitleDocument();
		document = createBodyDocument(questionIdsAsJson);
		return responseBuilder.build();

	}

}
