package io.github.oliviercailloux.collaborative_exams.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;
import com.itextpdf.text.Document;
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
import javax.json.JsonObject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Path("ExportPdf")
public class GetListExamenQuestion {

	private static Font titre = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
	private static Font soustitre = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font smalltext = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

	@Inject
	QuestionService questionService;

	final List<Question> listExamenQuestion = null;

	@Inject
	QuestionAdapter questionAdapter;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Examination(JsonObject questionJson) throws Exception {

		Question question = questionAdapter.adaptFromJson(questionJson);
		listExamenQuestion.add(question);
		return Response.ok().entity("ok").encoding(MediaType.TEXT_PLAIN).build();
	}

	@GET
	@Transactional
	@Path("/exportpdf")
	@Produces("application/pdf")
	public Response givelistExamenQuestion() throws Exception {

		Document document = new Document();
		File file = new File("C:\\tmp\\givelistExamenQuestion.pdf");
		FileInputStream fileInputStream = new FileInputStream(file);
		ResponseBuilder responseBuilder = Response.ok((Object) fileInputStream);
		responseBuilder.type("application/pdf");
		responseBuilder.header("Content-Disposition", "filename=givelistExamenQuestion.pdf");
		PdfWriter.getInstance(document, new FileOutputStream(file));
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
		Paragraph preface2 = new Paragraph("EXAMEN ECRIT", titre);
		preface2.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(preface2);
		Paragraph preface3 = new Paragraph("Culture Générale", soustitre);
		preface3.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(preface3);
		Paragraph preface4 = new Paragraph("Aucun document autorisé. Durée de l'examen 2 deux heures.", smalltext);
		addEmptyLine(preface4, 3);
		document.add(preface4);
		for (final Question question : listExamenQuestion) {
			Paragraph preface = new Paragraph("", titre);
			PdfPTable table0 = new PdfPTable(3);
			table0.setWidthPercentage(100);
			table0.addCell(getCell(question.getPhrasing(), PdfPCell.ALIGN_LEFT));
			document.add(table0);
			addEmptyLine(preface, 3);
			document.add(preface);
		}
		document.close();

		return responseBuilder.build();
	}
	// curl --header "Content-Type: application/json" --request POST --data
	// '{"id":"2"}' http://localhost:8080/examcollab/ExportPdf

	public static PdfPCell getCell(String text, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
