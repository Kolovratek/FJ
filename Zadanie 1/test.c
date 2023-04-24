#include <stdio.h>
#include <string.h>
#include "greatest.h"
#include "ps.h"


TEST first_test(void) {
    char data[] = "abc"; //A
    int result = Accept(data);
    ASSERT_EQ(result, 1);
    PASS();
}

TEST second_test(void) {
    char data[] = "abcbbbbb"; //A
    int result = Accept(data);
    ASSERT_EQ(result, 1);
    PASS();
}

TEST third_test(void) {
    char data[] = "abcbbbbx"; //A
    int result = Accept(data);
    ASSERT_EQ(result, 1);
    PASS();
}

TEST fourth_test(void) {
    char data[] = "xbc"; //A
    int result = Accept(data);
    ASSERT_EQ(result, 1);
    PASS();
}

TEST fifth_test(void) {
    char data[] = "xbcbbx"; //A
    int result = Accept(data);
    ASSERT_EQ(result, 1);
    PASS();
}

TEST sixth_test(void) {
    char data[] = "xxbcbbbbbx"; //A
    int result = Accept(data);
    ASSERT_EQ(result, 1);
    PASS();
}

TEST seventh_test(void) {
    char data[] = "abcxb"; //A
    int result = Accept(data);
    ASSERT_EQ(result, -1);
    PASS();
}

TEST eighth_test(void) {
    char data[] = "xbb"; //A
    int result = Accept(data);
    ASSERT_EQ(result, -1);
    PASS();
}

TEST ninth_test(void) {
    char data[] = "abcbbxbb"; //A
    int result = Accept(data);
    ASSERT_EQ(result, -1);
    PASS();
}

TEST tenth_test(void) {
    char data[] = "xxbcbbbbbxb"; //A
    int result = Accept(data);
    ASSERT_EQ(result, -1);
    PASS();
}

SUITE(accept_suite) {
    RUN_TEST(first_test);
    RUN_TEST(second_test);
    RUN_TEST(third_test);
    RUN_TEST(fourth_test);
    RUN_TEST(fifth_test);
    RUN_TEST(sixth_test);
    RUN_TEST(seventh_test);
    RUN_TEST(eighth_test);
    RUN_TEST(ninth_test);
    RUN_TEST(tenth_test);
}

/* Add definitions that need to be in the test runner's main file. */
GREATEST_MAIN_DEFS();

int main(int argc, char **argv) {
    GREATEST_MAIN_BEGIN();      /* command-line options, initialization. */

    RUN_SUITE(accept_suite);

    GREATEST_MAIN_END();        /* display results */
}

	
