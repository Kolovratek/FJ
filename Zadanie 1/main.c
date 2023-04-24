#include <stdio.h>
#include <string.h>
#include "ps.h"

#define LEN 64

int main() {
    char str[LEN];
    printf("Zadaj vstup: ");
    fgets(str, LEN, stdin);

    str[strcspn(str, "\n")] = 0;

    printf("\nVstupny retazec: %s, dlzky: %i", str, (int) strlen(str));
    printf("\nStav: %c\n", Accept(str) == 1 ? 'A' : 'N');

    return 0;
}
