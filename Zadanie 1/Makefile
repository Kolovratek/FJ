CC=gcc
CFLAGS=-std=c11 -Wall -Werror -g
LDLIBS=-lm
OUTPUT=ps

all: $(OUTPUT)
$(OUTPUT): ps.o main.o
	# cppcheck --enable=performance,unusedFunction --error-exitcode=1 *.c
	gcc $(CFLAGS) $^ $(LDLIBS) -o $(OUTPUT)

test: ps.o test.o
	gcc $(CFLAGS) $^ $(LDLIBS) -o test

ps.o: ps.c
	gcc $(CFLAGS) -c ps.c -o ps.o

main.o: main.c
	gcc $(CFLAGS) -c main.c -o main.o

test.o: test.c
	gcc $(CFLAGS) -c test.c -o test.o

clean:
	rm -rf $(OUTPUT) test *.o
