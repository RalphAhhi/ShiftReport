package com.fv.shiftreport.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transaction extends Audit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5199784602692138722L;
	
	private long id;
	private Double totalGas;
	private Double totalLubes;
	private List<AccountReceivable> accountReceivableList;
	private List<Collection> collectionList;
	private Double fuelSales;
	private Double addLubes;
	private Double totalSales;
	private Double lessAr;
	private Double totalCashSales;
	private Double addCollection;
	private Double lessExpenses;
	private Double totalCashOnHand;
	private Double cashDeposited;
	private Double overShort;
	private List<SubTransaction> subTransactionList;
	private String computerAddress;
	private Double dieselUgtInvt;
	private Double dieselOpening;
	private Double dieselDelivery;
	private Double dieselTotal;
	private Double dieselClosing;
	private Double dieselSTK;
	private Double dieselMeter;
	private Double avgasUgtInvt;
	private Double avgasOpening;
	private Double avgasDelivery;
	private Double avgasTotal;
	private Double avgasClosing;
	private Double avgasSTK;
	private Double avgasMeter;
	private long linkId;
	private Double totalCollections;
	private String universalTrxId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getTotalGas() {
		return totalGas;
	}

	public void setTotalGas(Double totalGas) {
		this.totalGas = totalGas;
	}

	public Double getTotalLubes() {
		return totalLubes;
	}

	public void setTotalLubes(Double totalLubes) {
		this.totalLubes = totalLubes;
	}

	public List<AccountReceivable> getAccountReceivableList() {
		if(null == accountReceivableList){
			accountReceivableList = new ArrayList<AccountReceivable>();
		}
		return accountReceivableList;
	}

	public void setAccountReceivableList(List<AccountReceivable> accountReceivableList) {
		this.accountReceivableList = accountReceivableList;
	}

	public List<Collection> getCollectionList() {
		if(null == collectionList){
			collectionList = new ArrayList<Collection>();
		}
		return collectionList;
	}

	public void setCollectionList(List<Collection> collectionList) {
		this.collectionList = collectionList;
	}

	public Double getFuelSales() {
		return fuelSales;
	}

	public void setFuelSales(Double fuelSales) {
		this.fuelSales = fuelSales;
	}

	public Double getAddLubes() {
		return addLubes;
	}

	public void setAddLubes(Double addLubes) {
		this.addLubes = addLubes;
	}

	public Double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}

	public Double getLessAr() {
		return lessAr;
	}

	public void setLessAr(Double lessAr) {
		this.lessAr = lessAr;
	}

	public Double getTotalCashSales() {
		return totalCashSales;
	}

	public void setTotalCashSales(Double totalCashSales) {
		this.totalCashSales = totalCashSales;
	}

	public Double getAddCollection() {
		return addCollection;
	}

	public void setAddCollection(Double addCollection) {
		this.addCollection = addCollection;
	}

	public Double getLessExpenses() {
		return lessExpenses;
	}

	public void setLessExpenses(Double lessExpenses) {
		this.lessExpenses = lessExpenses;
	}

	public Double getTotalCashOnHand() {
		return totalCashOnHand;
	}

	public void setTotalCashOnHand(Double totalCashOnHand) {
		this.totalCashOnHand = totalCashOnHand;
	}

	public Double getCashDeposited() {
		return cashDeposited;
	}

	public void setCashDeposited(Double cashDeposited) {
		this.cashDeposited = cashDeposited;
	}

	public Double getOverShort() {
		return overShort;
	}

	public void setOverShort(Double overShort) {
		this.overShort = overShort;
	}

	public List<SubTransaction> getSubTransactionList() {
		if(null == subTransactionList){
			subTransactionList = new ArrayList<SubTransaction> ();
		}
		return subTransactionList;
	}

	public void setSubTransactionList(List<SubTransaction> subTransactionList) {
		this.subTransactionList = subTransactionList;
	}

	public String getComputerAddress() {
		return computerAddress;
	}

	public void setComputerAddress(String computerAddress) {
		this.computerAddress = computerAddress;
	}

	public long getLinkId() {
		return linkId;
	}

	public void setLinkId(long linkId) {
		this.linkId = linkId;
	}

	public Double getDieselUgtInvt() {
		return dieselUgtInvt;
	}

	public void setDieselUgtInvt(Double dieselUgtInvt) {
		this.dieselUgtInvt = dieselUgtInvt;
	}

	public Double getDieselOpening() {
		return dieselOpening;
	}

	public void setDieselOpening(Double dieselOpening) {
		this.dieselOpening = dieselOpening;
	}

	public Double getDieselDelivery() {
		return dieselDelivery;
	}

	public void setDieselDelivery(Double dieselDelivery) {
		this.dieselDelivery = dieselDelivery;
	}

	public Double getDieselTotal() {
		return dieselTotal;
	}

	public void setDieselTotal(Double dieselTotal) {
		this.dieselTotal = dieselTotal;
	}

	public Double getDieselClosing() {
		return dieselClosing;
	}

	public void setDieselClosing(Double dieselClosing) {
		this.dieselClosing = dieselClosing;
	}

	public Double getDieselSTK() {
		return dieselSTK;
	}

	public void setDieselSTK(Double dieselSTK) {
		this.dieselSTK = dieselSTK;
	}

	public Double getDieselMeter() {
		return dieselMeter;
	}

	public void setDieselMeter(Double dieselMeter) {
		this.dieselMeter = dieselMeter;
	}

	public Double getAvgasUgtInvt() {
		return avgasUgtInvt;
	}

	public void setAvgasUgtInvt(Double avgasUgtInvt) {
		this.avgasUgtInvt = avgasUgtInvt;
	}

	public Double getAvgasOpening() {
		return avgasOpening;
	}

	public void setAvgasOpening(Double avgasOpening) {
		this.avgasOpening = avgasOpening;
	}

	public Double getAvgasDelivery() {
		return avgasDelivery;
	}

	public void setAvgasDelivery(Double avgasDelivery) {
		this.avgasDelivery = avgasDelivery;
	}

	public Double getAvgasTotal() {
		return avgasTotal;
	}

	public void setAvgasTotal(Double avgasTotal) {
		this.avgasTotal = avgasTotal;
	}

	public Double getAvgasClosing() {
		return avgasClosing;
	}

	public void setAvgasClosing(Double avgasClosing) {
		this.avgasClosing = avgasClosing;
	}

	public Double getAvgasSTK() {
		return avgasSTK;
	}

	public void setAvgasSTK(Double avgasSTK) {
		this.avgasSTK = avgasSTK;
	}

	public Double getAvgasMeter() {
		return avgasMeter;
	}

	public void setAvgasMeter(Double avgasMeter) {
		this.avgasMeter = avgasMeter;
	}

	public Double getTotalCollections() {
		return totalCollections;
	}

	public void setTotalCollections(Double totalCollections) {
		this.totalCollections = totalCollections;
	}

	public String getUniversalTrxId() {
		return universalTrxId;
	}

	public void setUniversalTrxId(String univesalTrxId) {
		this.universalTrxId = univesalTrxId;
	}

}
