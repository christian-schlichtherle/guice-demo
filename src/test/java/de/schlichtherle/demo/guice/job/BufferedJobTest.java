/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.job;

import de.schlichtherle.demo.guice.printer.Printer;
import java.io.PrintStream;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * @author Christian Schlichtherle
 */
public class BufferedJobTest {

    @Test public void testRenderTo() {
        final String message = "Hello world!";
        final Printer.Job delegate = spy(new Printer.Job() {
            @Override public void renderTo(PrintStream out) {
                out.print(message);
            }
        });
        final Printer.Job job = new BufferedJob(delegate, message.length());
        final PrintStream out = mock(PrintStream.class);

        job.renderTo(out);
        job.renderTo(out);

        verify(delegate, never()).renderTo(out);
        verify(delegate).renderTo((PrintStream) any());
        verify(out, times(2)).write(
                eq(message.getBytes()),
                eq(0),
                eq(message.length()));
    }
}
