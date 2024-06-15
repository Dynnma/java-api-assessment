package com.cbfacademy.apiassessment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.cbfacademy.apiassessment.App;
import com.cbfacademy.apiassessment.vehicleRentals.VehicleRental;
import com.cbfacademy.apiassessment.vehicleRentals.VehicleRentalService;

import java.net.URI;
import java.net.URISyntaxException;
import java.lang.Double;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppTests {

	@LocalServerPort
	private int port;

	private URI baseURI;

	@Autowired
	private TestRestTemplate restTemplate;

	private List<VehicleRental> defaultVehicleRentals = new ArrayList<>() {
		{
			add(new VehicleRental("John", "WA23 BRT", "Van", Double.valueOf("305.87"), getLocalDateTime(24),
					getLocalDateTime(24)));
			add(new VehicleRental("Mary", "BS21 CGA", "Car", Double.valueOf("135.45"), getLocalDateTime(48),
					getLocalDateTime(48)));
			add(new VehicleRental("Linda", "GL32 MLS", "SUV", Double.valueOf("232.00"), getLocalDateTime(72),
					getLocalDateTime(72)));
		}
	};

	@MockBean
	private VehicleRentalService vehiclerentalService;

	@BeforeEach
	void setUp() throws RuntimeException {
		this.baseURI = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("localhost")
				.port(port)
				.path("api/vehicleRentals")
				.build()
				.toUri();

		when(vehiclerentalService.getAllVehicleRentals()).thenReturn(defaultVehicleRentals);
	}

	@Test
	@Description("GET /api/vehicleRentals returns all vehicleRentals")
	public void getAllVehicleRental() throws URISyntaxException {

		ResponseEntity<List<VehicleRental>> response = restTemplate.exchange(baseURI, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<VehicleRental>>() {
				});
		List<VehicleRental> responseVehicleRentals = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(responseVehicleRentals);
		assertEquals(defaultVehicleRentals.size(), responseVehicleRentals.size());
		verify(vehiclerentalService).getAllVehicleRentals();
	}

	@Test
	@Description("GET /api/vehicleRentals{reservationId} returns matching vehicleRental")
	public void getVehicleRentalByReservationId() {

		VehicleRental vehiclerental = selectRandomVehicleRental();
		URI endpoint = getEndpoint(vehiclerental);

		when(vehiclerentalService.getVehicleRental(any(UUID.class))).thenReturn(vehiclerental);

		ResponseEntity<VehicleRental> response = restTemplate.getForEntity(endpoint, VehicleRental.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(vehiclerental.getReservationId(), response.getBody().getReservationId());
		verify(vehiclerentalService).getVehicleRental(vehiclerental.getReservationId());
	}

	@Test
	@Description("POST /api/vehicleRentals creates new VehicleRental")
	void createVehicleRental() {

		VehicleRental vehiclerental = createNewVehicleRental();

		when(vehiclerentalService.createVehicleRental(any(VehicleRental.class)))
				.thenAnswer(invocation -> setReservationId(invocation.getArgument(0)));

		ResponseEntity<VehicleRental> response = restTemplate.postForEntity(baseURI.toString(), vehiclerental,
				VehicleRental.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getReservationId());
		verify(vehiclerentalService).createVehicleRental(any(VehicleRental.class));
	}

	@Test
	@Description("PUT /api/vehicleRentals{reservationId} updates matching VehicleRental")
	void updateVehicleRental() {

		VehicleRental vehiclerental = selectRandomVehicleRental();
		URI endpoint = getEndpoint(vehiclerental);

		when(vehiclerentalService.getVehicleRental(any(UUID.class))).thenReturn(vehiclerental);
		when(vehiclerentalService.updateVehicleRental(any(UUID.class), any(VehicleRental.class)))
				.thenReturn(vehiclerental);

		vehiclerental.setRenter("UpdatedRenter");
		restTemplate.put(endpoint, vehiclerental);

		ResponseEntity<VehicleRental> response = restTemplate.getForEntity(endpoint, VehicleRental.class);
		VehicleRental updatedVehicleRental = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(vehiclerental.getReservationId(), updatedVehicleRental.getReservationId());
		assertEquals("UpdatedRenter", updatedVehicleRental.getRenter());
		verify(vehiclerentalService).getVehicleRental(vehiclerental.getReservationId());
		verify(vehiclerentalService).updateVehicleRental(any(UUID.class), any(VehicleRental.class));
	}

	@Test
	@Description("PUT /api/vehicleRentals{reservationId} returns 404 for invalid VehicleRental")
	void updateInvalidVehicleRental() {

		VehicleRental vehiclerental = createNewVehicleRental();
		URI endpoint = getEndpoint(vehiclerental);

		when(vehiclerentalService.updateVehicleRental(any(UUID.class), any(VehicleRental.class)))
				.thenThrow(new NoSuchElementException("VehicleRental not found"));

		RequestEntity<VehicleRental> request = RequestEntity.put(endpoint).accept(MediaType.APPLICATION_JSON)
				.body(vehiclerental);
		ResponseEntity<VehicleRental> response = restTemplate.exchange(request, VehicleRental.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(vehiclerentalService).updateVehicleRental(any(UUID.class), any(VehicleRental.class));
	}

	@Test
	@Description("DELETE /api/vehicleRentals{reservationId} deletes matching VehicleRental")
	void deleteVehicleRental() {

		VehicleRental vehiclerental = selectRandomVehicleRental();
		URI endpoint = getEndpoint(vehiclerental);

		when(vehiclerentalService.getVehicleRental(any(UUID.class))).thenReturn(vehiclerental);

		ResponseEntity<VehicleRental> foundResponse = restTemplate.getForEntity(endpoint, VehicleRental.class);

		doAnswer(invocation -> {
			return null;
		}).when(vehiclerentalService).deleteVehicleRental(any(UUID.class));
		when(vehiclerentalService.getVehicleRental(any(UUID.class))).thenThrow(NoSuchElementException.class);

		RequestEntity<?> request = RequestEntity.delete(endpoint).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<?> deletionResponse = restTemplate.exchange(request, Object.class);
		ResponseEntity<VehicleRental> deletedResponse = restTemplate.getForEntity(endpoint, VehicleRental.class);

		assertEquals(HttpStatus.OK, foundResponse.getStatusCode());
		assertTrue(deletionResponse.getStatusCode() == HttpStatus.OK
				|| deletionResponse.getStatusCode() == HttpStatus.NO_CONTENT);
		assertEquals(HttpStatus.NOT_FOUND, deletedResponse.getStatusCode());
		verify(vehiclerentalService).deleteVehicleRental(vehiclerental.getReservationId());
	}

	@Test
	@Description("DELETE /api/vehicleRentals{reservationId} returns 404 for invalid VehicleRental")
	void deleteInvalidIOU() {

		VehicleRental vehiclerental = createNewVehicleRental();
		URI endpoint = getEndpoint(vehiclerental);

		doThrow(new NoSuchElementException("VehicleRental not found")).when(vehiclerentalService)
				.deleteVehicleRental(any(UUID.class));

		RequestEntity<?> request = RequestEntity.delete(endpoint).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<VehicleRental> response = restTemplate.exchange(request, VehicleRental.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(vehiclerentalService).deleteVehicleRental(vehiclerental.getReservationId());
	}

	private VehicleRental selectRandomVehicleRental() {
		int randomIndex = new Random().nextInt(defaultVehicleRentals.size());

		return setReservationId(defaultVehicleRentals.get(randomIndex));
	}

	private VehicleRental createNewVehicleRental() {
		return setReservationId(new VehicleRental("John", "WA23 BRT", "Van", Double.valueOf("305.87"),
				getLocalDateTime(24), getLocalDateTime(24)));
	}

	private URI getEndpoint(VehicleRental vehiclerental) {
		return appendPath(baseURI, vehiclerental.getReservationId().toString());
	}

	private LocalDateTime getLocalDateTime(int hoursToSubtract) {
		ZoneId systemTimeZone = ZoneId.systemDefault();
		ZonedDateTime currentDateTime = ZonedDateTime.now(systemTimeZone);

		Duration duration = Duration.ofHours(hoursToSubtract);
		ZonedDateTime resultDateTime = currentDateTime.minus(duration);

		LocalDateTime localdatetime = resultDateTime.toLocalDateTime();

		return localdatetime;
	}

	private URI appendPath(URI uri, String path) {
		return UriComponentsBuilder.fromUri(uri).pathSegment(path).build().encode().toUri();
	}

	private static VehicleRental setReservationId(VehicleRental vehiclerental) {
		ReflectionTestUtils.setField(vehiclerental, "reservationId", UUID.randomUUID());
		return vehiclerental;
	}
}
