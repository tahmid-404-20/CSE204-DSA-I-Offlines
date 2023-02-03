#include<iostream>
#include<cstdlib>
#include<fstream>
#include<cstdio>
#include<ctime>
#include <bits/stdc++.h>
#include "functions.h"
using namespace std;

#define SIZE 1

void insertRandomValuesToArray(int *a, long size){
    for(long i=0;i<size;i++)
        a[i] = rand();
}

void printArray(int *a, long size)
{
    for(long i=0;i<size;i++)
        cout << a[i] << " ";

    cout << endl;
}

int main(){

    srand(1);
    int *a = new int[SIZE];

    insertRandomValuesToArray(a,SIZE);
    printArray(a,SIZE);
    MergeSort(a,0,SIZE-1);
    printArray(a,SIZE);
    cout << endl;

    insertRandomValuesToArray(a,SIZE);
    printArray(a,SIZE);
    QuickSort(a,0,SIZE-1);
    printArray(a,SIZE);
    cout << endl;

    insertRandomValuesToArray(a,SIZE);
    printArray(a,SIZE);
    RandomizedQuickSort(a,0,SIZE-1);
    printArray(a,SIZE);
    cout << endl;

    insertRandomValuesToArray(a,SIZE);
    printArray(a,SIZE);
    insertionSort(a,SIZE-1);
    printArray(a,SIZE);
    cout << endl;


    //insertRandomValuesToArray(a,SIZE);
    QuickSort(a,0,SIZE-1);
    printArray(a,SIZE);
    cout << endl;

    //insertRandomValuesToArray(a,SIZE);
    RandomizedQuickSort(a,0,SIZE-1);
    printArray(a,SIZE);
    cout << endl;


    return 0;
}
