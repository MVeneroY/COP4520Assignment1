all: runner.java
	javac runner.java 
	java Main

single: SThreaded.java 
	javac SThreaded.java 
	java SThreaded 100000000

clean: *.class 
	rm *.class