package com.fv.shiftreport.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.fv.shiftreport.model.AccountReceivable;
import com.fv.shiftreport.model.Collection;
import com.fv.shiftreport.util.POIUtil;

public class MainControllerHelper {
	
	private static final String[] arHeader={"Created Date","Customer","Invoice #","Amount"};
 	private static final String[] collectionHeader={"Created Date","Customer","Check Bank #","Amount"};

	public static Map<String, List<AccountReceivable>> groupByCustomerAR(List<AccountReceivable> arList) {
		Map<String, List<AccountReceivable>> customerGroup = new HashMap<String, List<AccountReceivable>>();
		for (AccountReceivable ar : arList) {
			List<AccountReceivable> group = customerGroup.get(ar.getCustomer().toUpperCase());
			if (null != group) {
				group.add(ar);
			} else {
				group = new ArrayList<AccountReceivable>();
				group.add(ar);
				customerGroup.put(ar.getCustomer().toUpperCase(), group);
			}
		}
		return customerGroup;
	}

	public static Map<String, List<Collection>> groupByCustomerCollection(List<Collection> collectionList) {
		Map<String, List<Collection>> customerGroup = new HashMap<String, List<Collection>>();
		for (Collection col : collectionList) {
			List<Collection> group = customerGroup.get(col.getCustomer().toUpperCase());
			if (null != group) {
				group.add(col);
			} else {
				group = new ArrayList<Collection>();
				group.add(col);
				customerGroup.put(col.getCustomer().toUpperCase(), group);
			}
		}
		return customerGroup;
	}

	private static void createArExcel(File file, List<AccountReceivable> arList) {
		HSSFWorkbook workbook = POIUtil.createWorkBook();
		HSSFSheet sheet = POIUtil.createWorkSheet(workbook, "Sheet1");
		createExcelHeader(sheet, arHeader);
		int x = 1;
		for (AccountReceivable ar : arList) {
			int y = 0;
			POIUtil.writeToCell(sheet, x, y++, ar.getCreatedDate().toString());
			POIUtil.writeToCell(sheet, x, y++, ar.getCustomer());
			POIUtil.writeToCell(sheet, x, y++, ar.getInvoiceNo());
			POIUtil.writeToCell(sheet, x, y++, String.valueOf(ar.getAmount()));
			x++;
		}
		POIUtil.writeExccelToFile(workbook, file);
	}

	public static void exportARtoExcel(String currentDir, List<AccountReceivable> arList)
			throws IOException {
		Map<String, List<AccountReceivable>> groupAr = groupByCustomerAR(arList);
		for (String customer : groupAr.keySet()) {
			List<AccountReceivable> customerAr = groupAr.get(customer);
			File file = new File(currentDir + "/AccountReceivable/" + customer.toUpperCase() + "/" + customer.toUpperCase() + ".xls");
			file.getParentFile().mkdirs();
			createArExcel(file, customerAr);
		}
	}

	public static void exportCollectiontoExcel(String currentDir, List<Collection> colList)
			throws IOException {
		Map<String, List<Collection>> groupCol = groupByCustomerCollection(colList);
		for (String customer : groupCol.keySet()) {
			List<Collection> customerCol = groupCol.get(customer);
			File file = new File(currentDir + "/Collections/" + customer.toUpperCase() + "/" + customer.toUpperCase() + ".xls");
			file.getParentFile().mkdirs();
			createCollectionExcel(file, customerCol);
		}
	}

	private static void createCollectionExcel(File file, List<Collection> collList) {
		HSSFWorkbook workbook = POIUtil.createWorkBook();
		HSSFSheet sheet = POIUtil.createWorkSheet(workbook, "Sheet1");
		createExcelHeader(sheet, collectionHeader);
		int x = 1;
		for (Collection col : collList) {
			int y = 0;
			POIUtil.writeToCell(sheet, x, y++, col.getCustomer());
			POIUtil.writeToCell(sheet, x, y++, col.getBankCheck());
			POIUtil.writeToCell(sheet, x, y++, String.valueOf(col.getAmount()));
			x++;
		}
		POIUtil.writeExccelToFile(workbook, file);
	}

	private static void createExcelHeader(HSSFSheet sheet, String[] headers) {

		int y = 0;
		for (String header : headers) {
			POIUtil.writeToCell(sheet, 0, y, header);
			y++;
		}

	}
}
