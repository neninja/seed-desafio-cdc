.PHONY: help clean test package run fmt check verify debug

MVN := mvn
DEBUG_PORT ?= 5005

help:
	@echo "Alvos disponíveis:"
	@echo "  make clean     - limpa o build"
	@echo "  make test      - roda testes"
	@echo "  make package   - gera o jar (sem pular testes)"
	@echo "  make run       - roda a aplicação (spring-boot:run)"
	@echo "  make debug     - roda a aplicação com debug remoto (porta $(DEBUG_PORT))"
	@echo "  make fmt       - formata o código (spotless:apply)"
	@echo "  make check     - valida formatação (spotless:check)"
	@echo "  make verify    - roda verify (inclui spotless-check no lifecycle)"

clean:
	$(MVN) clean

test:
	$(MVN) test

package:
	$(MVN) package

run:
	$(MVN) spring-boot:run

debug:
	$(MVN) spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:${DEBUG_PORT}"

fmt:
	$(MVN) spotless:apply

check:
	$(MVN) spotless:check

verify:
	$(MVN) verify