package de.schlichtherle.demo.guice.printer;

import static de.schlichtherle.demo.guice.util.Objects.requireNonNull;
import java.io.IOException;
import javax.inject.*;

/**
 * Prints each job to two printers.
 *
 * @author Christian Schlichtherle
 */
public final class TeePrinter implements Printer {

    private final Printer primary, secondary;

    public @Inject TeePrinter(
            final @Named("primary") Printer primary,
            final @Named("secondary") Printer secondary) {
        this.primary = requireNonNull(primary);
        this.secondary = requireNonNull(secondary);
    }

    @Override public void print(final Job job) throws IOException {
        primary.print(job);
        secondary.print(job);
    }
}
