package xero.invoice.pojo;

import java.util.List;

public class InvoiceCreateHeadersPojo {
	//Invoice headers
	private String to;
	private String date; 
	private String due_date;
	private String invoice_number;
	private String reference;
	private String branding;
	private String currency;
	private String ammount_are; 
	private List<InvoicePojo> items;
	public List<InvoicePojo> getItems() {
		return items;
	}
	public void setItems(List<InvoicePojo> items) {
		this.items = items;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getBranding() {
		return branding;
	}
	public void setBranding(String branding) {
		this.branding = branding;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAmmount_are() {
		return ammount_are;
	}
	public void setAmmount_are(String ammount_are) {
		this.ammount_are = ammount_are;
	}
}
