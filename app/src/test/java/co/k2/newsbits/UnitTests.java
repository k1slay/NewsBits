package co.k2.newsbits;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.k2.newsbits.common.Utils;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 30/03/19
 */

public class UnitTests {

    public static final String TEST_DATE_PATTERN = "MMM d, y h:mm a";
    public static final String TEST_ISO_DATE = "2019-03-29T17:05:42Z";

    @Test
    public void utilsTest() {
        Date date = Utils.isoToDate(TEST_ISO_DATE);
        Date expectedDate = new Date(1553879142000L);
        assertEquals(date.getTime(), expectedDate.getTime());

        String dateString = Utils.dateToLocalString(date, new SimpleDateFormat(TEST_DATE_PATTERN));
        assertEquals("Mar 29, 2019 10:35 PM", dateString);
    }

}
