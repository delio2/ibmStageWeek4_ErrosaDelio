package com.stage.cic;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.stage.cic.entity.Apartment;
import com.stage.cic.exception.BadRequestException;
import com.stage.cic.exception.EntityNotFoundException;

public class Service {
	
	private static final BigDecimal CHEAP_THRESHOLD = new BigDecimal(6000.0);
	private static final BigDecimal MODERATE_THRESHOLD = new BigDecimal(8000.0);
	
	private static final String APARTMENT_NOT_FOUND_MSG = "Apartment with id %d not found";
	private static final String AREA_ERROR_MSG = "Cannot update area to a value greater than 1000";
	
	private Dao dao;
	
	public Service(Dao dao) {
		this.dao = dao;
	}
	
	/**
	 * Il metodo restituisce la valutazione di un appartamento in base a prezzo e area.
	 * Se area == 0.0 restituisce -1
	 * Se prezzo/area < CHEAP_THRESHOLD: restituisce 0
	 * Se prezzo/area >= CHEAP_THRESHOLD e < MODERATE_THRESHOLD: restituisce 1
	 * Altrimenti restituisce 2
	 */
	private int rateApartment(Apartment apartment) {
		if (apartment.getArea() == 0.0)
			return -1;

		BigDecimal ratio = apartment.getPrice().divide(new BigDecimal(apartment.getArea()), RoundingMode.HALF_UP);

		if (ratio.compareTo(CHEAP_THRESHOLD) < 0)
			return 0;
		else if (ratio.compareTo(CHEAP_THRESHOLD) >= 0 && ratio.compareTo(MODERATE_THRESHOLD) < 0)
			return 1;
		else
			return 2;
	}
	
	public int rateApartment(int apartmentId) throws EntityNotFoundException {
		Apartment apartment = dao.findApartment(apartmentId);
		if (apartment != null)
			return rateApartment(apartment);
		else
			throw new EntityNotFoundException(String.format(APARTMENT_NOT_FOUND_MSG, apartmentId));
	}
	
	public int updateArea(int apartmentId, double area) throws BadRequestException, EntityNotFoundException {
		if (area > 1000)
			throw new BadRequestException(AREA_ERROR_MSG);

		Apartment apartment = dao.findApartment(apartmentId);
		if (apartment != null) {
			apartment.setArea(area);
			Apartment updatedApartment = dao.updateApartment(apartment);
			return rateApartment(updatedApartment);
		} else
			throw new EntityNotFoundException(String.format(APARTMENT_NOT_FOUND_MSG, apartmentId));
		
    }

}
