package edu.uark.registerapp.controllers;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.products.ProductQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;

@Controller //2 Route endpoints for this class JC
@RequestMapping(value = "/productDetail") //whenever request is recieved at this URL, it will be mapped to this class JC
public class ProductDetailRouteController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView start() {
		return (new ModelAndView(ViewNames.PRODUCT_DETAIL.getViewName()))
			.addObject(
				ViewModelNames.PRODUCT.getValue(),
				(new Product()).setLookupCode(StringUtils.EMPTY).setCount(0));
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET) //Curly brace indicates that value will come from HTTP request (URL) JC
	public ModelAndView startWithProduct(@PathVariable final UUID productId) {
		final ModelAndView modelAndView =
			new ModelAndView(ViewNames.PRODUCT_DETAIL.getViewName());

		try {
			modelAndView.addObject(
				ViewModelNames.PRODUCT.getValue(), //lookup key JC
				this.productQuery.setProductId(productId).execute()); //data associated w key JC
		} catch (final Exception e) {
			modelAndView.addObject(
				ViewModelNames.ERROR_MESSAGE.getValue(),
				e.getMessage());
			modelAndView.addObject(
				ViewModelNames.PRODUCT.getValue(),
				(new Product())
					.setCount(0)
					.setLookupCode(StringUtils.EMPTY));
		}

		return modelAndView;
	}

	// Properties
	@Autowired
	private ProductQuery productQuery;
}
