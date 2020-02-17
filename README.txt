CONTENTS OF THIS FILE
---------------------

 * Introduction
 * Requirements
 * Troubleshooting
 * Maintainers

INTRODUCTION
------------

This application will read the provided files, parse the data, and output some simple analysis of the read information.

This application was written in Java 8, with the IntelliJ IDE.

REQUIREMENTS
------------

This project uses Gradle as it requires OpenCSV to run properly.
For best results, run within IntelliJ or build the project while including libraries in dependencies.


TROUBLESHOOTING
---------------

"I cannot get it to read a file other than the default." - Add program arguments representing the file paths of the files to read (separated by a space).

"I cannot find the result CSV file." - The CSV file is generated in your set working-directory.

MAINTAINERS
-----------

Created by John Brooks for the purpose of the Genova Diagnostics coding challenge.