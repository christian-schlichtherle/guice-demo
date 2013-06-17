/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.printer;

import java.io.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * @author Christian Schlichtherle
 */
public class StandardPrinterTest {

    @Test public void testPrint() throws IOException {
        final PrintStream out = mock(PrintStream.class);
        final Printer printer = new StandardPrinter(out);
        final Printer.Job job = mock(Printer.Job.class);

        printer.print(job);

        verify(job).renderTo(out);
        verifyNoMoreInteractions(job);
        verifyZeroInteractions(out);
    }
}
