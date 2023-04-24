#include <stdio.h>
#include <string.h>
#include "ps.h"

#define UND -1

int status = 0;

int Accept(char *IString){
    status = 0;
    int len = strlen(IString);

    for(int i=0; i<len; i++){
        if(status==0){
            zero(IString[i]);
        }
        else if(status==1){
            one(IString[i]);
        }
        else if(status==2){
            two(IString[i]);
        }
        else if(status==3){
            three(IString[i]);
        }
        else if(status==4){
            four(IString[i]);
        }
        else {
            status = UND;
        }
    }

    if (status == 4 || status == 5) {
        return 1; 
    } else {
        return UND;
    }
}

void  zero (char c) {
    if (c == 'a') {
        status = 1;
    } 
    else if (c == 'x') {
        status = 2;
    }
    else {
        status = UND;
    }
}

void one (char c) {
    if(c == 'b') {
        status = 3;
    }
    else {
        status = UND;
    }
}

void two (char c) {
    if(c == 'b') {
        status = 3;
    }
    else if(c== 'x') {
        status = 1;
    }
    else {
        status = UND;
    }
}

void three (char c) {
    if(c == 'c') {
        status = 4;
    }
    else {
        status = UND;
    }
}

void four (char c) {
    if(c == 'b') {
        status = 4;
    }
    else if(c=='x') {
        status = 5;
    }
    else {
        status = UND;
    }
}
