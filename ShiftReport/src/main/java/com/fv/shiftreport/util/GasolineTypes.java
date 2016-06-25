package com.fv.shiftreport.util;

public enum GasolineTypes {

	DIESEL(2,"Diesel"),
	AVGAS(1,"Avgas"),
	KERO(3,"Kero");
	
	private int id;
	private String description;
	
	 GasolineTypes(int id, String desc) {
		this.id = id;
		this.description = desc;
	}
	
	public static String getDescById(int id){
		for(GasolineTypes type: GasolineTypes.values()){
			if(type.getId() == id){
				return type.getDescription();
			}
		}
		return null;
	}
	
	public static GasolineTypes getGasTypeId(int id){
		for(GasolineTypes type: GasolineTypes.values()){
			if(type.getId() == id){
				return type;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
