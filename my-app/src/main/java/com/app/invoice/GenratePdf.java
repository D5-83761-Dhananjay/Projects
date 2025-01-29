package com.app.invoice;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class GenratePdf {

	public static void main(String[] args) throws FileNotFoundException {
		String path = "invoice.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		
		PdfDocument pdfdoc = new PdfDocument(pdfWriter);
		pdfdoc.setDefaultPageSize(PageSize.A4);
		
		Document document = new Document(pdfdoc);
		
		float twocal = 285 ;
		float twocal150 = twocal + 150 ;
		float threecal = 190; 
		
		float fullwidth [] = {threecal*3};
		float twocalwidth [] = {twocal150 , twocal}; 
		
		
		Paragraph onsp = new Paragraph("\n");
		
		Table table = new Table(twocalwidth) ;
		table.addCell(new Cell().add("Invoice").setFontSize(20f).setBorder(Border.NO_BORDER).setBold());
		Table nestedTable = new Table(new float [] {twocal/2,twocal/2});
		nestedTable.addCell(getHeaderTextCell("Invoice No : "));
		nestedTable.addCell(getHeaderTextCellValue("6565995"));
		nestedTable.addCell(getHeaderTextCell("Invoice Date : "));
		nestedTable.addCell(getHeaderTextCellValue("11/09/2025"));
		
		Border gb = new SolidBorder(Color.GRAY, 2);
		Table divider = new Table(fullwidth);
		divider.setBorder(gb);
		

		table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
		document.add(table) ;
		document.add(onsp);
		document.add(divider);
		document.add(onsp);
		
		
		Table twoColTable = new Table(twocalwidth);
		twoColTable.addCell(getBillingandShippingCelll("Billing Information"));
		twoColTable.addCell(getBillingandShippingCelll("Shipping Information"));
		document.add(twoColTable.setMarginBottom(12));
		
		
		Table twoColTable2 = new Table(twocalwidth);
		twoColTable2.addCell(getCell10left("Company", true));
		twoColTable2.addCell(getCell10left("Name", true));
		twoColTable2.addCell(getCell10left("BlinkIt", false));
		twoColTable2.addCell(getCell10left("Dhananjay", false));
		document.add(twoColTable2);

		Table twoColTable3 = new Table(twocalwidth);
		twoColTable3.addCell(getCell10left("Name", true));
		twoColTable3.addCell(getCell10left("Address", true));
		twoColTable3.addCell(getCell10left("Aryln Pinnjil", false));
		twoColTable3.addCell(getCell10left("Plot No.53 lekha nagar \n savadi A.Nagar pin : 414003", false));
		document.add(twoColTable3);

		float oneCol [] = {twocal150};
		Table twoColTable4 = new Table(oneCol);
		twoColTable4.addCell(getCell10left("Address", true));
		twoColTable4.addCell(getCell10left("Plot No.53 lekha nagar \\n savadi A.Nagar pin : 414003", false));
		twoColTable4.addCell(getCell10left("Email", true));
		twoColTable4.addCell(getCell10left("dgnagargoje@gmail.com", false));
		document.add(twoColTable4.setMarginBottom(10));
		
		Table divider2 = new Table(fullwidth);
		Border db = new DashedBorder(Color.GRAY, 0.5f);
		document.add(divider2.setBorder(db));
		Paragraph productPara = new Paragraph("Products");
		document.add(productPara.setBold());
		
		float threecolist [] = {threecal,threecal,threecal};
		
		Table threeColumnTable = new Table(threecolist);
		threeColumnTable.setBackgroundColor(Color.BLACK,0.7f);
		threeColumnTable.addCell(new Cell().add("Description").setBold().setTextAlignment(TextAlignment.LEFT).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
		threeColumnTable.addCell("Quantity").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER);
		threeColumnTable.addCell("Price").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER);
		document.add(threeColumnTable);
		
		List<Product>prodList = new  ArrayList<>();
		prodList.add(new Product("Laptop", 5, 1200.99f));
        prodList.add(new Product("Smartphone", 10, 699.50f));
        prodList.add(new Product("Headphones", 15, 49.99f));
        prodList.add(new Product("Keyboard", 8, 29.99f));
        prodList.add(new Product("Monitor", 4, 199.99f));
        prodList.add(new Product("Mouse", 12, 19.99f));
        prodList.add(new Product("Tablet", 6, 399.99f));
        
        float totalSum = 0 ;
        
        Table threeColumnTable2 = new Table(threecolist);
        for(Product  p : prodList) {
        	float total = p.getQuantity()*p.getPrice();
        	totalSum = totalSum + total ;
        	threeColumnTable2.addCell(new Cell().add(p.getName()).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
    		threeColumnTable2.addCell(new Cell().add(String.valueOf(p.getQuantity())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
    		threeColumnTable2.addCell(new Cell().add(String.valueOf(p.getPrice())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)).setMarginRight(15);
    		
        }
        
        document.add(threeColumnTable2.setMarginBottom(20));
        
        float oneTwo[] = {threecal + 125, threecal * 2};
        Table totalTab = new Table(oneTwo);

        // First column (no border)
        totalTab.addCell(new Cell().add("").setBorder(Border.NO_BORDER));

        // Last two columns merged into a single cell with a dotted bottom border
        Cell dottedLineCell = new Cell(1, 2).setBorderBottom(new DashedBorder(Color.GRAY, 0.5f)).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER);
        totalTab.addCell(dottedLineCell);

        document.add(totalTab);

        
        
        Table threeColumnTable3 = new Table(threecolist);
    	threeColumnTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f);
		threeColumnTable3.addCell(new Cell().add("Total").setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
		threeColumnTable3.addCell(new Cell().add(String.valueOf(totalSum)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)).setMarginRight(15);
		document.add(threeColumnTable3.setBorder(Border.NO_BORDER));
		document.add(divider2);
		document.add(new Paragraph("\n"));
		document.add(divider.setBorder(new SolidBorder(Color.GRAY,1)).setMarginBottom(15));
        
		document.add(new Paragraph("Terms & Conditions").setBold().setFontSize(12));
		document.add(new Paragraph("1. Payment is due within 30 days from the invoice date.\n" +
		                           "2. Late payments may incur additional charges.\n" +
		                           "3. Goods once sold cannot be returned or exchanged.\n" +
		                           "4. For any disputes, the decision of the company shall be final.\n" +
		                           "5. Contact support@example.com for inquiries.").setFontSize(10));

		document.close();
	}

	public static Cell getHeaderTextCell(String textVal) 
	{
		return new Cell().add(textVal).setBorder(Border.NO_BORDER).setBold().setTextAlignment(TextAlignment.LEFT);
	}
	
	public static Cell getHeaderTextCellValue(String textVal) 
	{
		return new Cell().add(textVal).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
	}

	public static Cell getBillingandShippingCelll(String textVal) 
	{
		return new Cell().add(textVal).setFontSize(12).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
	}

	public static Cell getCell10left(String textVal,boolean isBold) 
	{
		Cell mycell = new Cell().add(textVal).setFontSize(10).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
		return isBold ? mycell.setBold() : mycell ;
	}	
}

class Product{
	private String name ;
	private int quantity ;
	private float price ;
	
	public Product(String name, int quantity, float price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}

