all: main.class

main.class:
	javac cli/modules/administrator/*.java cli/modules/driver/*.java cli/modules/manager/*.java cli/modules/passenger/*.java cli/modules/*.java cli/validators/*.java service/*.java *.java

run:
	java -cp mysql-connector-java-5.1.47.jar:. Main

clean:
	find . -name "*.class" -type f -delete