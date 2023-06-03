.PHONY: build exec run clean

build:
	mkdir -p build
	java_files=$$(find . -type f -not -path "./tests/*" -name "*.java") && \
	  javac -d ./build $${java_files}
	cd build && \
	  jar cmvf ../META-INF/MANIFEST_MAIN.MF main.jar ElectoralSystem/*.class

test:
	mkdir -p build
	java_files=$$(find . -type f -name "*.java") && \
	  javac -d ./build $${java_files}
	cd build && \
	  jar cmvf ../manifests/MANIFEST_TEST.MF main_test.jar ElectoralSystem/*.class

exec:
	java -jar ./build/main.jar

run: build exec

clean:
	rm **/*.class