# Guice Demo

This standalone project demonstrates how to setup and run a Guice Injector for
printing a localized time-of-the-day message to some output stream, together
with some decorations to set the message apart from others.

The project provides interfaces and implementations for printers and jobs.
Some implementations are decorators for printers which separate the content
produced by print jobs and do some error checking.
When bootstrapping, a Guice injector is configured which wires a print job
for announcing the localized time-of-the-day message to a decorated printer.

## Documentation

The documentation for this Guice demo is available at
<https://christian-schlichtherle.github.io/guice-demo/>.
This page also contains information about how to contact the project
author / team.

## Building

You need Java SE 6 and Apache Maven 3.0.4 or higher installed.
Change to the repository directory and run:

    $ mvn clean install

## Running

After building, you can run the standalone JAR like this:

    $ java -jar target/guice-demo-*-shaded.jar

For the UK, the output should be similar to this:

    ---------- BEGIN PRINT ----------
    Good morning.
    At the third stroke, it will be eleven fifty-nine and three seconds. BEEP, BEEP, BEEP.
    At the third stroke, it will be eleven fifty-nine and four seconds. BEEP, BEEP, BEEP.
    At the third stroke, it will be eleven fifty-nine and five seconds. BEEP, BEEP, BEEP.
    At the third stroke, it will be eleven fifty-nine and six seconds. BEEP, BEEP, BEEP.
    At the third stroke, it will be eleven fifty-nine and seven seconds. BEEP, BEEP, BEEP.
    ----------  END PRINT  ----------

You may notice that this message is similar to the messages of BT's popular
[Speaking Clock](http://en.wikipedia.org/wiki/Speaking_clock).

You can set different combinations of countries and languages,
e.g. the following command:

    $ java -Duser.language=de -Duser.country=DE -jar target/guice-demo-*-shaded.jar

... should produce a message which is similar to the messages of the Deutsche
Telekom Zeitansage:

    ---------- DRUCK ANFANG ----------
    Guten Morgen.
    Beim nächsten Ton ist es elf Uhr, neunundfünfzig Minuten und zehn Sekunden. PIEP.
    Beim nächsten Ton ist es elf Uhr, neunundfünfzig Minuten und elf Sekunden. PIEP.
    Beim nächsten Ton ist es elf Uhr, neunundfünfzig Minuten und zwölf Sekunden. PIEP.
    Beim nächsten Ton ist es elf Uhr, neunundfünfzig Minuten und dreizehn Sekunden. PIEP.
    Beim nächsten Ton ist es elf Uhr, neunundfünfzig Minuten und vierzehn Sekunden. PIEP.
    ----------  DRUCK ENDE  ----------

For fun, you can easily add other localizations. Please send me a pull request
if you do, so that I can add it to the code base. I am looking forward to it.

## License

This demo project is covered by the
[Eclipse Software License, Version 1.0](http://www.eclipse.org/legal/epl-v10.html).
