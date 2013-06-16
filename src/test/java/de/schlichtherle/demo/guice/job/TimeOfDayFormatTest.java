/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.job;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author Christian Schlichtherle
 */
@RunWith(Parameterized.class)
public class TimeOfDayFormatTest {

    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { Locale.GERMANY, 0, "null" },
            { Locale.GERMANY, 1, "eins" },
            { Locale.GERMANY, 2, "zwei" },
            { Locale.GERMANY, 3, "drei" },
            { Locale.GERMANY, 4, "vier" },
            { Locale.GERMANY, 5, "fünf" },
            { Locale.GERMANY, 6, "sechs" },
            { Locale.GERMANY, 7, "sieben" },
            { Locale.GERMANY, 8, "acht" },
            { Locale.GERMANY, 9, "neun" },
            { Locale.GERMANY, 10, "zehn" },
            { Locale.GERMANY, 11, "elf" },
            { Locale.GERMANY, 12, "zwölf" },
            { Locale.GERMANY, 13, "dreizehn" },
            { Locale.GERMANY, 14, "vierzehn" },
            { Locale.GERMANY, 15, "fünfzehn" },
            { Locale.GERMANY, 16, "sechzehn" },
            { Locale.GERMANY, 17, "siebzehn" },
            { Locale.GERMANY, 18, "achtzehn" },
            { Locale.GERMANY, 19, "neunzehn" },
            { Locale.GERMANY, 20, "zwanzig" },
            { Locale.GERMANY, 21, "einundzwanzig" },
            { Locale.GERMANY, 30, "dreißig" },
            { Locale.GERMANY, 31, "einunddreißig" },
            { Locale.GERMANY, 40, "vierzig" },
            { Locale.GERMANY, 41, "einundvierzig" },
            { Locale.GERMANY, 50, "fünfzig" },
            { Locale.GERMANY, 51, "einundfünfzig" },
            { Locale.GERMANY, 60, "sechzig" }, // Schaltsekunde

            { Locale.UK, 0, "zero" },
            { Locale.UK, 1, "one" },
            { Locale.UK, 2, "two" },
            { Locale.UK, 3, "three" },
            { Locale.UK, 4, "four" },
            { Locale.UK, 5, "five" },
            { Locale.UK, 6, "six" },
            { Locale.UK, 7, "seven" },
            { Locale.UK, 8, "eight" },
            { Locale.UK, 9, "nine" },
            { Locale.UK, 10, "ten" },
            { Locale.UK, 11, "eleven" },
            { Locale.UK, 12, "twelve" },
            { Locale.UK, 13, "thirteen" },
            { Locale.UK, 14, "fourteen" },
            { Locale.UK, 15, "fifteen" },
            { Locale.UK, 16, "sixteen" },
            { Locale.UK, 17, "seventeen" },
            { Locale.UK, 18, "eighteen" },
            { Locale.UK, 19, "nineteen" },
            { Locale.UK, 20, "twenty" },
            { Locale.UK, 21, "twenty-one" },
            { Locale.UK, 30, "thirty" },
            { Locale.UK, 31, "thirty-one" },
            { Locale.UK, 40, "fourty" },
            { Locale.UK, 41, "fourty-one" },
            { Locale.UK, 50, "fifty" },
            { Locale.UK, 51, "fifty-one" },
            { Locale.UK, 60, "sixty" }, // leap-second

            { Locale.US, 0, "zero" },
            { Locale.US, 1, "one" },
            { Locale.US, 2, "two" },
            { Locale.US, 3, "three" },
            { Locale.US, 4, "four" },
            { Locale.US, 5, "five" },
            { Locale.US, 6, "six" },
            { Locale.US, 7, "seven" },
            { Locale.US, 8, "eight" },
            { Locale.US, 9, "nine" },
            { Locale.US, 10, "ten" },
            { Locale.US, 11, "eleven" },
            { Locale.US, 12, "twelve" },
            { Locale.US, 13, "thirteen" },
            { Locale.US, 14, "fourteen" },
            { Locale.US, 15, "fifteen" },
            { Locale.US, 16, "sixteen" },
            { Locale.US, 17, "seventeen" },
            { Locale.US, 18, "eighteen" },
            { Locale.US, 19, "nineteen" },
            { Locale.US, 20, "twenty" },
            { Locale.US, 21, "twenty-one" },
            { Locale.US, 30, "thirty" },
            { Locale.US, 31, "thirty-one" },
            { Locale.US, 40, "fourty" },
            { Locale.US, 41, "fourty-one" },
            { Locale.US, 50, "fifty" },
            { Locale.US, 51, "fifty-one" },
            { Locale.US, 60, "sixty" }, // leap-second
        });
    }

    private final TimeOfDayFormat format;
    private final int integer;
    private final String string;

    public TimeOfDayFormatTest(
            final Locale locale,
            final int integer,
            final String string) {
        this.format = new TimeOfDayFormat(locale);
        this.integer = integer;
        this.string = string;
    }

    @Test
    public void testFormat() {
        assertEquals(string, format.format(integer));
    }
}
