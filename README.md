# CSX42: Assignment 1
## Name: Bhagwan Sanjay Deore

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in wordPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile wordPlay/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile wordPlay/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile wordPlay/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -Dmetrics="metrics.txt"

Note: Arguments accept the absolute path of the files.

make sure input file is in the top-level directory (wordPlay/).

-----------------------------------------------------------------------
## Description:

Design of this assignment solution is based on principle of composition. rather than passing around
large result arrays (which need to be copied, hence, expensive) we can pass object 
references (with composition). Detailed design explanations can be found as comments
in the respective classes. data structure: to store information for calculating metrics(word length, line numbers) I'm using vector of vectors of integers. as vectors grow dynamically at run time, this frees the program from any arbitrary hardcoded limits. to store the results of rotation, a vector of string objects is used. 

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: June 6, 2020


