## Word Rotator
 
#### OOP concepts - Abstraction, Inheritance and Composition

Following are the commands & instructions to build and run the program using ant.

-----------------------------------------------------------------------
### Instruction to clean:

#### Command: ant -buildfile wordPlay/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

#### Note: build.xml is present in wordPlay/src folder.

-----------------------------------------------------------------------
### Instruction to compile:

#### Command: ant -buildfile wordPlay/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
### Instruction to run:

#### Command: ant -buildfile wordPlay/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -Dmetrics="metrics.txt"

Note: Arguments accept the absolute path of the files.

#### please make sure input file is in the wordPlay/ directory. output files will be generated in the same directory.
#### program writes output to both standard out and files by default. 
#### i have deliberately omitted printing stackTrace upon exceptions as it clutters the output window.

if you get errors while running code, please make sure files are in correct directories.

Date: June 6, 2020


