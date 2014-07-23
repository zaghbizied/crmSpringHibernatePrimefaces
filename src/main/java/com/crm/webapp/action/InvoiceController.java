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
import com.crm.model.Vente;
import com.crm.service.ClientManager;
import com.crm.service.InvoiceManager;
import com.crm.service.OpVenteManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
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
    private LazyDataModel<OpVente> opVentes;
    private LazyDataModel<Invoice> invoices;
    private ClientManager clientManager;
    private OpVenteManager opVenteManager;
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
    public void setOpVenteManager(@Qualifier("opVenteManager")OpVenteManager opVenteManager) {
        this.opVenteManager = opVenteManager;
    }
    
    @Autowired
    public void setInvoiceManager(@Qualifier("invoiceManager")InvoiceManager invoiceManager) {
        this.invoiceManager = invoiceManager;
    }

    public LazyDataModel<OpVente> getOpVentes() {
        return opVentes;
    }

    public void setOpVentes(LazyDataModel<OpVente> opVentes) {
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

    public LazyDataModel<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(LazyDataModel<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Invoice getNewInvoice() {
        return newInvoice;
    }

    public void setNewInvoice(Invoice newInvoice) {
        this.newInvoice = newInvoice;
    }
    
    public void search() {
       System.out.println("searching");
        if(searchObject.getClient()!=null){
            if(searchObject.getDateFact()!=null){
                this.invoices = new LazyDataModel<Invoice>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<Invoice> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<Invoice> result = invoiceManager.getLazyByDateByClient(searchObject.getDateFact(),searchObject.getClient(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                }
                };
                opVentes.setRowCount(invoiceManager.countByDateByClient(searchObject.getDateFact(),searchObject.getClient()));
            }
            else{
                this.invoices = new LazyDataModel<Invoice>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<Invoice> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    List<Invoice> result = invoiceManager.getLazyByClient(searchObject.getClient(),first, pageSize, sortField, sortOrder, filters);
                    return result;
                }
                };
                invoices.setRowCount(invoiceManager.countByClient(searchObject.getClient()));
            }
        }
        else{
           if(searchObject.getDateFact()!=null){
                this.invoices = new LazyDataModel<Invoice>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<Invoice> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            System.out.println("loading by date "+first+" "+pageSize);
                            List<Invoice> result = invoiceManager.getLazyByDate(searchObject.getDateFact(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                invoices.setRowCount(invoiceManager.countByDate(searchObject.getDateFact()));
            }
           else{
               this.invoices = new LazyDataModel<Invoice>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<Invoice> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<Invoice> result = invoiceManager.getLazyAll(first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                invoices.setRowCount(invoiceManager.countAll());
           }
        }
        
    }
    
    @PostConstruct
    public void init(){
        searchObject=new Invoice();
        clients=clientManager.getAll();
        this.invoices = new LazyDataModel<Invoice>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<Invoice> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    List<Invoice> result = invoiceManager.getLazyByDate(new Date(System.currentTimeMillis()),first, pageSize, sortField, sortOrder, filters);
                    return result;
                }
        };
        invoices.setRowCount(invoiceManager.countByDate(new Date(System.currentTimeMillis())));
    }
    
    public void onClientSelect(){
        this.opVentes = new LazyDataModel<OpVente>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpVente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    List<OpVente> result = opVenteManager.getLazyByClient(newInvoice.getClient(), first, pageSize, sortField, sortOrder, filters);
                    return result;
                }
        };
        opVentes.setRowCount(opVenteManager.countByClient(newInvoice.getClient()));
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
