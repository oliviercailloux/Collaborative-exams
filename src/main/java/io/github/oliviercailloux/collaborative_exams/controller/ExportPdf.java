package io.github.oliviercailloux.collaborative_exams.controller;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;

/**
 * Jax-RS Servlet that allows to export a pdf 
 */

@Path("ExportPdf")
public class ExportPdf {
	
	private static String FILE = "src\\main\\Examen_.pdf" ;
	private static Font titre = new Font(Font.FontFamily.TIMES_ROMAN, 20,
	        Font.BOLD);
	private static Font soustitre = new Font(Font.FontFamily.TIMES_ROMAN, 14,
	        Font.BOLD);
	private static Font titreQuestion = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	        Font.BOLD);
	private static Font smalltext = new Font(Font.FontFamily.TIMES_ROMAN, 8,
	        Font.NORMAL);
	
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
	
	@Inject
	private Question question;
	private QuestionService questionservice;
	private String printquestion = "";


	@GET
	@Transactional
	@Produces(MediaType.TEXT_PLAIN)
	public void ExportPdf() {
		try {
	    	System.out.println("Access Path" + FILE);
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(FILE));
	        document.open();

	        PdfPTable table = new PdfPTable(3);
		    table.setWidthPercentage(100);
		    Calendar prevYear = Calendar.getInstance();
		    prevYear.add(Calendar.YEAR, -1);
		    table.addCell(getCell("Plateforme Collaborative Examens", PdfPCell.ALIGN_LEFT));
		    table.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		    table.addCell(getCell("Année scolaire : " + prevYear.get(Calendar.YEAR) + "/" + Calendar.getInstance().get(Calendar.YEAR)+"", PdfPCell.ALIGN_RIGHT));
		    document.add(table);
	        
			final List<Question> listquestions = questionservice.getAll();
			for (final Question question : listquestions) {
				
				Paragraph preface = new Paragraph("", titre);
			    PdfPTable table0 = new PdfPTable(3);
			    table0.setWidthPercentage(100);
			    table0.addCell(getCell(question.getPhrasing(), PdfPCell.ALIGN_LEFT));
			    document.add(table0);
			}
	        document.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	     
	}

    
	}

