package com.google.code.geocoder;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.BeforeClass;

/**
 * @author <a href="mailto:panchmp@gmail.com">Michael Panchenko</a>
 */
public abstract class BaseGeocoderTest extends Assert {
    protected static Geocoder geocoder;

    @BeforeClass
    public static void setUp() throws InvalidKeyException {
        geocoder = new Geocoder("clientID", "AIzaSyBLaI6-4ArOQZsP0S-bmyxXYvZTg4oLhLY");
    }

}

