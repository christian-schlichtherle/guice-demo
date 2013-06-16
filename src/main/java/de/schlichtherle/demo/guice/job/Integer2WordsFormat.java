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
final class Integer2WordsFormat extends Format {

    private static final long serialVersionUID = 1L;

    private final Locale locale;
    private final ResourceBundle bundle;

    Integer2WordsFormat(final Locale locale) {
        this.locale = requireNonNull(locale);
        this.bundle = ResourceBundle.getBundle(Integer2WordsFormat.class.getName(),
                locale);
    }

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        pos.setBeginIndex(toAppendTo.length());
        toAppendTo.append(words((Integer) obj));
        pos.setEndIndex(toAppendTo.length());
        return toAppendTo;
    }

    @Override public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }

    private String words(final int number) {
        try {
            return lookup(Integer.toString(number));
        } catch (MissingResourceException ex) {
            return format(Message.tensAndOnes,
                    words(tens(number) * 10), words(ones(number)));
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

    private static int tens(int number) { return number / 10; }

    private static int ones(int number) { return number % 10; }

    private enum Message {
        tensAndOnes;

        String stringFrom(Integer2WordsFormat i2wf) {
            return i2wf.lookup(name());
        }
    }
}
