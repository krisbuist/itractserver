package com.google.code.geocoder;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;

public class TestGetLocation {

	@Test
	public void testGetLocation() {
	    final Geocoder geocoder = new Geocoder();
	    GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress("Rijnsburgstraat 9-11, Amsterdam, The Netherlands").getGeocoderRequest();
	    GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
	    List<GeocoderResult> results = geocoderResponse.getResults();
	    float latitude = results.get(0).getGeometry().getLocation().getLat().floatValue();
	    float longitude = results.get(0).getGeometry().getLocation().getLng().floatValue();
	    assertEquals(52.347797f, latitude, 0.00001);
	    assertEquals(4.8507648f, longitude, 0.00001);
	}

}
