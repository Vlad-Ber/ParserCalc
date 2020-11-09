all:	
	mkdir -p classes
	javac -cp JUNIT/junit-4.12.jar:./classes:JUNIT/hamcrest-core-1.3.jar -d classes *.java

run:
	java -cp classes org.ioopm.calculator.Calculator

test:
	java -cp classes org.ioopm.calculator.testers.Test -enableassertions

test_no_jit:
	java -cp classes org.ioopm.calculator.testers.Test -enableassertions -Djava.compiler=NONE


JUnitTest:
	javac -cp JUNIT/junit-4.12.jar:./classes:JUNIT/hamcrest-core-1.3.jar -d classes TestAst.java
	javac -cp JUNIT/junit-4.12.jar:./classes:JUNIT/hamcrest-core-1.3.jar -d classes TestIntegration.java
	javac -cp JUNIT/junit-4.12.jar:./classes:JUNIT/hamcrest-core-1.3.jar -d classes TestParserIntegration.java
	java -cp JUNIT/junit-4.12.jar:./classes:JUNIT/hamcrest-core-1.3.jar org.junit.runner.JUnitCore org.ioopm.calculator.testers.TestAst
	java -cp JUNIT/junit-4.12.jar:./classes:JUNIT/hamcrest-core-1.3.jar org.junit.runner.JUnitCore org.ioopm.calculator.testers.TestIntegration
	java -cp JUNIT/junit-4.12.jar:./classes:JUNIT/hamcrest-core-1.3.jar org.junit.runner.JUnitCore org.ioopm.calculator.testers.TestParserIntegration	

SystemLvlTest:
	java -cp classes org.ioopm.calculator.Calculator < input.txt > output.txt
	diff output.txt expectedOutput.txt

clean:
	rm -rf classes

