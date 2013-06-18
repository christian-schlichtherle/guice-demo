package de.schlichtherle.demo.guice;

import java.util.Date;
import java.util.Locale;

/**
 * Provides parameters for the {@link TimeOfDayJob}.
 *
 * @author Christian Schlichtherle
 */
public interface TimeOfDayParameters {
    Date now();
    Locale locale();
    int duration();
    int interval();
}
