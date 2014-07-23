/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
 * http://www.dynamicreports.org
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package com.crm.jasper;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class InvoiceDesign {
	private InvoiceData data ;
	private AggregationSubtotalBuilder<BigDecimal> totalSum;
        private Date date;
        
        public InvoiceDesign(InvoiceData data,Date date){
            this.data=data;
            this.date=date;
        }

	public JasperReportBuilder build() throws DRException {
		JasperReportBuilder report = report();
		StyleBuilder columnStyle = stl.style(Templates.columnStyle)
			.setBorder(stl.pen1Point());
		StyleBuilder subtotalStyle = stl.style(columnStyle)
			.bold();
		StyleBuilder shippingStyle = stl.style(Templates.boldStyle)
			.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn()
			.setFixedColumns(2)
			.setHorizontalAlignment(HorizontalAlignment.CENTER);
		TextColumnBuilder<String> descriptionColumn = col.column("Description", "description", type.stringType())
			.setFixedWidth(250);
		TextColumnBuilder<Integer> quantityColumn = col.column("Quantité", "quantity", type.integerType())
			.setHorizontalAlignment(HorizontalAlignment.CENTER);
		TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Prix Unitaire", "unitprice", Templates.currencyType);
		TextColumnBuilder<String> taxColumn = col.column("Tax", exp.text("20%"))
			.setFixedColumns(3);
		//price = unitPrice * quantity
		TextColumnBuilder<BigDecimal> priceColumn = unitPriceColumn.multiply(quantityColumn)
			.setTitle("Prix HT")
			.setDataType(Templates.currencyType);
		//vat = price * tax
		TextColumnBuilder<BigDecimal> vatColumn = priceColumn.multiply(data.getInvoice().getTax())
			.setTitle("TTC")
			.setDataType(Templates.currencyType);
		//total = price + vat
		TextColumnBuilder<BigDecimal> totalColumn = priceColumn.add(vatColumn)
			.setTitle("Prix Total")
			.setDataType(Templates.currencyType)
			.setRows(2)
			.setStyle(subtotalStyle);
		//init subtotals
		totalSum = sbt.sum(totalColumn)
			.setLabel("Total:")
			.setLabelStyle(Templates.boldStyle);

		//configure report
		report
			.setTemplate(Templates.reportTemplate)
			.setColumnStyle(columnStyle)
			.setSubtotalStyle(subtotalStyle)
			//columns
			.columns(
				rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn, totalColumn, priceColumn, taxColumn, vatColumn)
			.columnGrid(
				rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn,
				grid.horizontalColumnGridList()
					.add(totalColumn).newRow()
					.add(priceColumn, taxColumn, vatColumn))
			//subtotals
			.subtotalsAtSummary(
				totalSum, sbt.sum(priceColumn), sbt.sum(vatColumn))
			//band components
                        
			.title(
				Templates.createTitleComponent("Date : " + new SimpleDateFormat("dd-MM-yyyy").format(date)),
				cmp.horizontalList().setStyle(stl.style(10)).setGap(50).add(
					cmp.hListCell(createCustomerComponent("Facture Pour", data.getInvoice().getBillTo())).heightFixedOnTop()),
				cmp.verticalGap(10))
			.pageFooter(
				Templates.footerComponent)
			.summary(
				
				cmp.horizontalList(
					cmp.text("Paiement dans : 30 jours").setStyle(Templates.bold12CenteredStyle),
					cmp.text(new TotalPaymentExpression()).setStyle(Templates.bold12CenteredStyle)))
			.setDataSource(data.createDataSource());

		return report;
	}

	private ComponentBuilder<?, ?> createCustomerComponent(String label, Customer customer) {
		HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));
		addCustomerAttribute(list, "Nom", customer.getName());
		addCustomerAttribute(list, "Adresse", customer.getAddress());
		addCustomerAttribute(list, "Ville", customer.getCity());
		addCustomerAttribute(list, "Email", customer.getEmail());
		return cmp.verticalList(cmp.text(label).setStyle(Templates.boldStyle),list);
	}

	private void addCustomerAttribute(HorizontalListBuilder list, String label, String value) {
		if (value != null) {
			list.add(cmp.text(label + ":").setFixedColumns(8).setStyle(Templates.boldStyle), cmp.text(value)).newRow();
		}
	}

	private class TotalPaymentExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			BigDecimal total = reportParameters.getValue(totalSum);
			
			return "Paiement total: " + Templates.currencyType.valueToString(total, reportParameters.getLocale());
		}
	}
}