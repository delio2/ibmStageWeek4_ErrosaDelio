package com.stage.cic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.stage.cic.entity.Apartment;
import com.stage.cic.exception.BadRequestException;
import com.stage.cic.exception.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class ServiceTest {
	
	private static Dao dao;
	
	private static Service service;
	
	@BeforeAll
	static void init() {
		dao = Mockito.mock(Dao.class);
		service = new Service(dao);
	}

	@Test
	public void testRateApartment_AreaZero() throws EntityNotFoundException {
		//appartamento con area 0.0
		Apartment ap = new Apartment(1, 0.0, new BigDecimal(5000));
		Mockito.when(dao.findApartment(1)).thenReturn(ap);
		// Calcola rate appartamento
		int rating = service.rateApartment(1);
		// Verifica che rate sia -1, area non valida
		assertEquals(-1, rating);
	}

	@Test
	public void testRateApartment_CheapAp() throws EntityNotFoundException {
		// Crea un appartamento economico
		Apartment ap = new Apartment(1, 20, new BigDecimal(2000));
		Mockito.when(dao.findApartment(1)).thenReturn(ap);
		int rating = service.rateApartment(1);
		// Verifica che il rating sia 0
		assertEquals(0, rating);
	}

	@Test
	public void testRateApartment_ModerateAp() throws EntityNotFoundException {
		// Crea appartamento medie dimensioni
		Apartment ap = new Apartment(1, 15, new BigDecimal(100000));
		Mockito.when(dao.findApartment(1)).thenReturn(ap);
		int rating = service.rateApartment(1);
		// Verifica che il rating sia 1 
		assertEquals(1, rating);
	}

	@Test
	public void testRateApartment_ExpensiveAp() throws EntityNotFoundException {
		// Crea appartamento costoso
		Apartment ap = new Apartment(1, 150, new BigDecimal(1500000));
		Mockito.when(dao.findApartment(1)).thenReturn(ap);
		int rating = service.rateApartment(1);
		// Verifica che il rating sia 2
		assertEquals(2, rating);
	}
	
	//
	@Test
	public void testRateAp_NotFound() throws EntityNotFoundException {
		//appartamento non trovato
		Mockito.when(dao.findApartment(1)).thenReturn(null);
		// Verifico che venga lanciata l'eccezione
		assertThrows(EntityNotFoundException.class, () -> service.rateApartment(1));
	}

	@Test
	public void testUpdateArea_ValidArea() throws BadRequestException, EntityNotFoundException {
		Apartment ap = new Apartment(1, 75, new BigDecimal(100000));
		Mockito.when(dao.findApartment(1)).thenReturn(ap);
		Mockito.when(dao.updateApartment(ap)).thenReturn(ap);
		// Aggiorno l'area dell'appartamento
		int rating = service.updateArea(1, 15);
		// Verifico che il rate sia aggiornato
		assertEquals(1, rating);
	}

	@Test
	public void testUpdateArea_greaterThen1000() throws BadRequestException {
		// Verifico che venga lanciata l'eccezione BadRequestException
		assertThrows(BadRequestException.class, () -> service.updateArea(1, 1001.0));
	}

	@Test
	public void testUpdateArea_NotFound() throws EntityNotFoundException {
		Mockito.when(dao.findApartment(1)).thenReturn(null);
		// Verifica che venga lanciata l'eccezione EntityNotFoundException
		assertThrows(EntityNotFoundException.class, () -> service.updateArea(1, 50.0));
	}
	
}
