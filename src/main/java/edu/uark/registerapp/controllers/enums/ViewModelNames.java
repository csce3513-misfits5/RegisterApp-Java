package edu.uark.registerapp.controllers.enums;

public enum ViewModelNames {
	NOT_DEFINED(""),
	ERROR_MESSAGE("errorMessage"),
	PRODUCTS("products"), // Product listing
	PRODUCT("product"), // Product detail
	EMPLOYEE_TYPES("employeeTypes"), 
	IS_ELEVATED_USER("elevatedUser"), //Might need to adjust later JC
	; 
	
	public String getValue() {
		return value;
	}
	
	private String value;

	private ViewModelNames(final String value) {
		this.value = value;
	}
}
