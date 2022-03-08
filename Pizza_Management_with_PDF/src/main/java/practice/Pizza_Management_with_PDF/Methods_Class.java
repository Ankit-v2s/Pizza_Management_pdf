package practice.Pizza_Management_with_PDF;

import java.io.*;
import java.util.*;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Methods_Class {

	Scanner sc = new Scanner(System.in);
	int order_id = 101, quantity, count = 0;
	String pizza_name;
	double price, amt;
	boolean flag = true;

	public Pizza take_Order() {
		System.out.println("Enter Pizza Name");
		pizza_name = sc.next();
		System.out.println("Enter Quantity");
		quantity = sc.nextInt();
		System.out.println("Enter price of pizza");
		price = sc.nextDouble();
		amt = price * quantity;

		Pizza take_order = new Pizza();
		take_order.setOrder_Id(order_id);
		take_order.setPizza_Name(pizza_name);
		take_order.setQuantity(quantity);
		take_order.setPrice(price);
		take_order.setAmount(amt);

		order_id++;
		return take_order;
	}

	public void writer(ArrayList<Pizza> list) throws IOException {
		File file = new File("/home/v2stech/Documents/Pizza.pdf");
		PDDocument doc = PDDocument.load(file);

		PDPage page = doc.getPage(0);
		PDPageContentStream contentStream = new PDPageContentStream(doc, page);

		contentStream.beginText();
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentStream.setLeading(14.5f);
		contentStream.newLineAtOffset(25, 700);

		contentStream.showText("Order_Id  Pizza Name Quantity Price Amount");
		contentStream.newLine();

		list.forEach(
				(pizza)->{
					try {
						contentStream.showText(pizza.getOrder_Id() + " " + pizza.getPizza_Name() + " " + pizza.getQuantity() + " "
						+ pizza.getPrice() + " " + pizza.getAmount());
						contentStream.newLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				);
		
		contentStream.close();

		doc.save(new File("/home/v2stech/Documents/Pizza.pdf"));
		doc.close();
	}

	public void view_Orders(ArrayList<Pizza> list) {
		for (Pizza p : list) {
			if (count == 0) {
				System.out.println("Order_Id \tPizza Name \tQuantity \tPrice \t\tAmount");
			}
			System.out.println(p.getOrder_Id() + "\t\t" + p.getPizza_Name() + "\t\t" + p.getQuantity() + "\t\tRs. "
					+ p.getPrice() + "\t\tRs. " + p.getAmount());
			count++;
			flag = false;
		}
		if (flag) {
			System.out.println("No orders available");
		}
	}

	public void delete_Order(ArrayList<Pizza> list, int del_Order) {
		for (Pizza p : list) {
			if (del_Order == p.getOrder_Id()) {
				list.remove(p);
				System.out.println(del_Order + " is removed");
				flag = false;
			}
			if (list.isEmpty()) {
				break;
			}
			break;
		}
		if (flag) {
			System.out.println(del_Order + " not found");
		}
	}

	public void search_Order(ArrayList<Pizza> list, int search_Order) {
		for (Pizza p : list) {
			if (search_Order == p.getOrder_Id()) {
				System.out.println("Order_Id \tPizza Name \tQuantity \tPrice \tAmount");
				System.out.println(p.getOrder_Id() + "\t" + p.getPizza_Name() + "\t" + p.getQuantity() + "\tRs. "
						+ p.getPrice() + "\tRs. " + p.getAmount());
				flag = false;
			}
		}
		if (flag) {
			System.out.println(search_Order + " not found");
		}
	}

}
