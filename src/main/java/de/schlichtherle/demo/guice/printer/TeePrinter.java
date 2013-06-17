package de.schlichtherle.demo.guice.printer;

import static de.schlichtherle.demo.guice.util.Objects.requireNonNull;
import java.io.IOException;
import javax.annotation.concurrent.Immutable;
import javax.inject.*;

/**
 * Prints each job to two printers.
 * If a job's content is dynamically rendered, then the two printers may
 * produce different output.
 *
 * @author Christian Schlichtherle
 */
@Immutable
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
