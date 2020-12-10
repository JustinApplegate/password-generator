# C++ Random Password Generator
This is my version of a random password generator in C++. It's written to be used in the Linux terminal. It outputs 10 random passwords based off of the requirements specified. There are 2 requirements:

* Password length (between 1 and 50)
* Character sets (lowercase, uppercase, digits, and symbols)

You may specify password length from inside the program or on the command line. The character sets must be specified in the program. 

To compile the program with the makefile, enter this command into the terminal:

`make`

To run the program, enter this command into the terminal (after compiling):

`./pwdgen`

OR

`./pwdgen [password length]`

To remove the binary file after being compiled and run, use this command:

`make clean`

## Character Sets
When specifying the character sets, there are 5 options - LC/lc (lowercase), UC/uc (uppercase), DG/dg (digits), SM/sm (symbols), and AL/al (all). Any invalid input results in the program ending. 

Example:
`lc UC dg` - that will give you a password with the lowercase, uppercase, and digit character sets. 