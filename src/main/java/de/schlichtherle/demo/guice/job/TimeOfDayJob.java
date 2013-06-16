/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.job;

import de.schlichtherle.demo.guice.printer.Printer;
import static de.schlichtherle.demo.guice.util.Objects.requireNonNull;
import java.io.PrintStream;
import static java.lang.Integer.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import javax.annotation.concurrent.Immutable;
import javax.inject.*;

/**
 * Prints the localized time-of-day to a given stream.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class TimeOfDayJob implements Printer.Job {

    private static final Pattern INTEGER_PATTERN = Pattern.compile("[0-9]+");

    private final Provider<Date> clock;
    private final Locale locale;
    private final ResourceBundle bundle;

    public @Inject TimeOfDayJob(final Provider<Date> clock, final Locale locale) {
        this.clock = requireNonNull(clock);
        this.locale = requireNonNull(locale);
        this.bundle = ResourceBundle.getBundle(TimeOfDayJob.class.getName(),
                locale);
    }

    @Override public void printTo(PrintStream out) {
        out.println(timeOfDay());
    }

    private String timeOfDay() {
        return format(Message.message, args(fields(dates())));
    }

    private String[] args(final Object[] fields) {
        final String[] args = stringsFor(Message.args);
        for (int i = args.length; --i >= 0; )
            args[i] = replaceIntegersWithWords(format(args[i], fields));
        return args;
    }

    private Object[] fields(final Date[] dates) {
        final String[] strings = stringsFor(Message.fields);
        final Object[] objects = new Object[strings.length];
        for (int i = strings.length; --i >= 0; ) {
            final String string = format(strings[i], dates);
            try {
                objects[i] = valueOf(string);
            } catch (NumberFormatException ex) {
                objects[i] = string;
            }
        }
        return objects;
    }

    private Date[] dates() { return new Date[] { clock.get() }; }

    private String replaceIntegersWithWords(final String text) {
        final Matcher matcher = INTEGER_PATTERN.matcher(text);
        final Format format = new TimeOfDayFormat(locale);
        final StringBuffer result = new StringBuffer(text.length());
        while (matcher.find()) {
            matcher.appendReplacement(result, "");
            format.format(valueOf(matcher.group()),
                    result, new FieldPosition(0));
        }
        return matcher.appendTail(result).toString();
    }

    private String format(Message pattern, Object[] arguments) {
        return format(stringFor(pattern), arguments);
    }

    private String format(String pattern, Object[] arguments) {
        return format(pattern).format(arguments);
    }

    private MessageFormat format(String pattern) {
        return new MessageFormat(pattern, locale);
    }

    private String[] stringsFor(Message message) {
        return message.stringsFrom(bundle);
    }

    private String stringFor(Message message) {
        return message.stringFrom(bundle);
    }

    private enum Message {
        message, args, fields;

        String[] stringsFrom(ResourceBundle bundle) {
            return new ResourceBundleMessage(bundle, this).toStrings();
        }

        String stringFrom(ResourceBundle bundle) {
            return new ResourceBundleMessage(bundle, this).toString();
        }
    }

    private static class ResourceBundleMessage {
        final ResourceBundle bundle;
        final Message message;

        ResourceBundleMessage(
                final ResourceBundle bundle,
                final Message message) {
            this.bundle = requireNonNull(bundle);
            this.message = requireNonNull(message);
        }

        String[] toStrings() {
            final int length = lookupLength();
            final String[] strings = new String[length];
            for (int i = strings.length; --i >= 0;)
                strings[i] = lookupIndex(i);
            return strings;
        }

        private int lookupLength() {
            return parseInt(lookup(lengthKey()));
        }

        private String lengthKey() { return name() + ".length"; }

        private String lookupIndex(int index) {
            return lookup(indexKey(index));
        }

        private String indexKey(int index) {
            return name() + '[' + index + ']';
        }

        @Override public String toString() { return lookup(name()); }

        private String lookup(String key) { return bundle.getString(key); }

        private String name() { return message.name(); }
    }
}
