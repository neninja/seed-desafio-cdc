MVN := mvn
DEBUG_PORT ?= 5005

run:
	$(MVN) spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:${DEBUG_PORT}" -Dspring-boot.run.profiles=local

fmt:
	$(MVN) spotless:apply

clean:
	$(MVN) clean

test:
	$(MVN) test

package:
	$(MVN) package

check:
	$(MVN) spotless:check

verify:
	$(MVN) verify