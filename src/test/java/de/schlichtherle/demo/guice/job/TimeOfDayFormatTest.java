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
            { Locale.ROOT, 0, "zero" },
            { Locale.ROOT, 1, "one" },
            { Locale.ROOT, 2, "two" },
            { Locale.ROOT, 3, "three" },
            { Locale.ROOT, 4, "four" },
            { Locale.ROOT, 5, "five" },
            { Locale.ROOT, 6, "six" },
            { Locale.ROOT, 7, "seven" },
            { Locale.ROOT, 8, "eight" },
            { Locale.ROOT, 9, "nine" },
            { Locale.ROOT, 10, "ten" },
            { Locale.ROOT, 11, "eleven" },
            { Locale.ROOT, 12, "twelve" },
            { Locale.ROOT, 13, "thirteen" },
            { Locale.ROOT, 14, "fourteen" },
            { Locale.ROOT, 15, "fifteen" },
            { Locale.ROOT, 16, "sixteen" },
            { Locale.ROOT, 17, "seventeen" },
            { Locale.ROOT, 18, "eighteen" },
            { Locale.ROOT, 19, "nineteen" },
            { Locale.ROOT, 20, "twenty" },
            { Locale.ROOT, 21, "twenty-one" },
            { Locale.ROOT, 30, "thirty" },
            { Locale.ROOT, 31, "thirty-one" },
            { Locale.ROOT, 40, "fourty" },
            { Locale.ROOT, 41, "fourty-one" },
            { Locale.ROOT, 50, "fifty" },
            { Locale.ROOT, 51, "fifty-one" },
            { Locale.ROOT, 60, "sixty" }, // leap-second

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
