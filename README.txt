Miguel Venero Yupanqui
Professor Parra
COP 4520
January 27, 2023

To compile: $ javac Runner.java 

to run: $ java Runner n 
          where n is the input number (ie: 100000000)

=================================== OR ===================================

To compile and run: $ make all


---------------------------------------------------------------------------

The approach I used consisted on keeping an array of booleans that would represent whether
a number is prime or not. I started with the assumption that all numbers are prime unless 
proved otherwise. Then, for each number starting at 2, the program will check its 
corresponding boolean value in the array. Non prime numbers are ignored; and for each prime 
number n, we will set the boolean values of all its multiples to false, starting from n^2.

Finally, after iterating through all the numbers in the range, we will iterate through the 
array once again to collect the total number of primes found, the sum of said primes, and the 
max 10 primes within the range.

We can guarantee efficiency considering the fact that the computations mentioned above are 
assigned to the 8 threads in the pool, and the load was balanced by assigning the tasks one
by one, such that each thread would get assigned a new task as soon as it finished with the
previous one, until the entire job is done. 

We can also guarantee the program's correctness thanks to the fact that the work is divided 
into tasks, and each task is assigned iteratively and in order. Thus, when a number is being
evaluated, all the values preceding it will have already been evaluated, so we wouls have all
the data necessary to carry out the current task.

I started the coding process by implementing a single-threaded approach, that had a processing
time of 2-3 seconds for an input of 10^8. I proceeded then to implement some optimizations, which 
reduced the processing time down to about one second. After that I started working on a 
multithreaded solution, which took around 550 ms for the same input. However, some threads would 
take about 5 times more than others to finish their jobs, so I decided to make another 
implementation using thread pooling. This last implementation took between 400 and 500 ms to 
compute and has a more balanced load across threads.*

*code was run with a 10 core machine.