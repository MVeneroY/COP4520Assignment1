# Miguel Venero Yupanqui

all: Runner.java SieveRunnable.java
	javac Runner.java 
	java Runner 100000000

clean: *.class 
	rm *.class