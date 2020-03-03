package edu.uark.registerapp.controllers.enums;

public enum ViewModelNames {
	NOT_DEFINED(""),
	ERROR_MESSAGE("errorMessage"),
	PRODUCTS("products"), // Product listing
	PRODUCT("product"), 
	EMPLOYEE_TYPES("employeeTypes"), //Might need to adjust later JC
	; // Product detail
	
	public String getValue() {
		return value;
	}
	
	private String value;

	private ViewModelNames(final String value) {
		this.value = value;
	}
}
