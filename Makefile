
build:
	make -C app build
run-dist:
	make -C app run-dist
report:
	make -C app report
build-run: build run
.PHONY: build
