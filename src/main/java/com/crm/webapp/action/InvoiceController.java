/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.jasper.Customer;
import com.crm.jasper.InvoiceData;
import com.crm.jasper.InvoiceDesign;
import com.crm.jasper.Item;
import com.crm.model.Client;
import com.crm.model.Invoice;
import com.crm.model.OpVente;
import com.crm.model.Produit;
import com.crm.model.Vente;
import com.crm.model.lazy.InvoiceLazyModel;
import com.crm.model.lazy.OpVenteLazyModel;
import com.crm.service.ClientManager;
import com.crm.service.InvoiceManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zied
 */
@Component("invoiceController")
@Scope("view")
public class InvoiceController extends BasePage implements Serializable{
    private OpVenteLazyModel opVentes=new OpVenteLazyModel();
    private InvoiceLazyModel invoices=new InvoiceLazyModel();
    private ClientManager clientManager;
    private InvoiceManager invoiceManager;
    private List<Client> clients=new ArrayList<>();
    private Invoice newInvoice;
    private Invoice searchObject;
    private String pdfInvoice;
    private List<OpVente> selOpVentes=new ArrayList<>();
    
    @Autowired
    public void setClientManager(@Qualifier("clientManager")ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    @Autowired
    public void setInvoiceManager(@Qualifier("invoiceManager")InvoiceManager invoiceManager) {
        this.invoiceManager = invoiceManager;
    }

    public OpVenteLazyModel getOpVentes() {
        return opVentes;
    }

    public void setOpVentes(OpVenteLazyModel opVentes) {
        this.opVentes = opVentes;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public String getPdfInvoice() {
        return pdfInvoice;
    }

    public void setPdfInvoice(String pdfInvoice) {
        this.pdfInvoice = pdfInvoice;
    }

    public Invoice getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(Invoice searchObject) {
        this.searchObject = searchObject;
    }

    public List<OpVente> getSelOpVentes() {
        return selOpVentes;
    }

    public void setSelOpVentes(List<OpVente> selOpVentes) {
        this.selOpVentes = selOpVentes;
    }

    public InvoiceLazyModel getInvoices() {
        return invoices;
    }

    public void setInvoices(InvoiceLazyModel invoices) {
        this.invoices = invoices;
    }

    public Invoice getNewInvoice() {
        return newInvoice;
    }

    public void setNewInvoice(Invoice newInvoice) {
        this.newInvoice = newInvoice;
    }
    
    public void search() {
    	invoices=new InvoiceLazyModel();
        List<Invoice> list=new ArrayList<>();
    	for(Invoice i:invoiceManager.getAll()){
    		boolean match=true;
                if(searchObject.getClient()!=null&&i.getClient().getId()!=searchObject.getClient().getId())match=false;
    		if(searchObject.getDateFact()!=null&&i.getDateFact()!=searchObject.getDateFact())match=false;
    		if(match)list.add(i);
    	}	
        invoices=new InvoiceLazyModel(list);
    }
    
    @PostConstruct
    public void init(){
        searchObject=new Invoice();
        invoices=new InvoiceLazyModel(invoiceManager.getAll());
        clients=clientManager.getAll();
    }
    
    public void onClientSelect(){
        opVentes=new OpVenteLazyModel(new ArrayList<>(newInvoice.getClient().getOpVentes()));
    }
    
    public void prepareAdd(){
        this.newInvoice=new Invoice();
        selOpVentes=new ArrayList<>();
    }
    
    public void prepareView(String url){
        this.pdfInvoice=url;
    }
    
    public void generateInvoice(){
                Customer c=new Customer();
                c.setAddress(newInvoice.getClient().getAddress());
                c.setCity(newInvoice.getClient().getVille());
                c.setEmail(newInvoice.getClient().getEmail());
                c.setName(newInvoice.getClient().getFullName());
                List<Item> items=new ArrayList<>();
                for(OpVente o:selOpVentes)
                    for(Vente v:o.getVentes()){
                        boolean found=false;
                        for(Item i:items){
                            if(i.getDescription().equals(v.getProduit().getDescription())&&i.getUnitprice().floatValue()==v.getPrixUnit()){
                                i.setQuantity(i.getQuantity()+v.getQuantite());
                                found=true;
                                break;
                            }
                        }
                        if(!found){
                            Item item=new Item();
                            item.setDescription(v.getProduit().getDescription());
                            item.setQuantity(v.getQuantite());
                            item.setUnitprice(new BigDecimal(v.getPrixUnit()));
                            items.add(item);
                        }
                    }
                InvoiceData inD=new InvoiceData(c,items,0.2);
		InvoiceDesign design = new InvoiceDesign(inD,newInvoice.getDateFact());
                String pathname =getServletContext().getRealPath("/invoices"); 
                String filename=newInvoice.getClient().getFullName().replace(" ","_")+"_"+newInvoice.getDateFact().toString().replace(" ","_").replace(":","_")+".pdf";
                System.out.print(filename);
		try {
                    JasperReportBuilder report = design.build();
                    File f=new File(pathname+"/"+filename);
                    OutputStream out=new FileOutputStream(f);
                    report.toPdf(out);                        
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException ex) {
                Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
                }
                newInvoice.setUrl("/invoices/"+filename);
                invoiceManager.save(newInvoice);
    }
}
