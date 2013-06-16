/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.job;

import static de.schlichtherle.demo.guice.util.Objects.requireNonNull;
import java.text.*;
import java.util.*;

/**
 * Formats a given integer as localized words.
 *
 * @author Christian Schlichtherle
 */
final class TimeOfDayFormat extends Format {

    private static final long serialVersionUID = 1L;

    private final Locale locale;
    private final ResourceBundle bundle;

    TimeOfDayFormat(final Locale locale) {
        this.locale = requireNonNull(locale);
        this.bundle = ResourceBundle.getBundle(TimeOfDayFormat.class.getName(),
                locale);
    }

    @Override
    public StringBuffer format(
            final Object obj,
            final StringBuffer toAppendTo,
            final FieldPosition pos) {
        final int integer = (Integer) obj;
        if (integer < 0 || 60 < integer)
            throw new NumberFormatException(integer + " is out of range. Valid integers range from 0 to 60 inclusively.");
        pos.setBeginIndex(toAppendTo.length());
        toAppendTo.append(words(integer));
        pos.setEndIndex(toAppendTo.length());
        return toAppendTo;
    }

    @Override public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }

    private String words(final int integer) {
        try {
            return lookup(Integer.toString(integer));
        } catch (MissingResourceException ex) {
            return format(Message.tensAndOnes,
                    words(tens(integer) * 10), words(ones(integer)));
        }
    }

    private String format(Message pattern, Object... arguments) {
        return format(stringFor(pattern), arguments);
    }

    private String format(String pattern, Object... arguments) {
        return new MessageFormat(pattern, locale).format(arguments);
    }

    private String stringFor(Message message) {
        return message.stringFrom(this);
    }

    String lookup(String key) { return bundle.getString(key); }

    private static int tens(int integer) { return integer / 10; }

    private static int ones(int integer) { return integer % 10; }

    private enum Message {
        tensAndOnes;

        String stringFrom(TimeOfDayFormat format) {
            return format.lookup(name());
        }
    }
}
