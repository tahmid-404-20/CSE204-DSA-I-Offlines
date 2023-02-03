#include<iostream>
#include<cstdlib>
#include<fstream>
#include<cstdio>
#include<ctime>
#include <bits/stdc++.h>
#include "functions.h"
using namespace std;

#define ELEMENTS_ARRAY_SIZE 6
#define Q_RUNNING 20

void insertRandomValuesToArray(int *a, long size){
    for(long i=0;i<size;i++)
        a[i] = rand();
}

int main()
{
    ios_base::sync_with_stdio(false);
    fstream file;
    file.open("Report.txt", ios::out);

    streambuf* sb_cout = cout.rdbuf();
    cout.rdbuf(file.rdbuf());
    srand(1);

    cout << "Time required in ms\n";
    cout << "n,Merge_Sort,Quicksort,Randomized_Quicksort,Insertion_Sort,Quicksort_with_Sorted _Input,";
    cout << "Randomized_Quicksort_with_Sorted_Input,STL_sort()_function\n";

    long nElements[] = {5,10,100,1000,10000,100000};
    int *a;

    for(int i=0;i<ELEMENTS_ARRAY_SIZE;i++)
    {
        a = new int[nElements[i]];

        cout << nElements[i] << ",";

        // MergeSort
        double total_time = 0.0;
        for(int j=0;j<Q_RUNNING;j++)
        {
            insertRandomValuesToArray(a,nElements[i]);
            clock_t start_time = clock();
            MergeSort(a,0,nElements[i]-1);
            total_time += 1000.0*double(clock()- start_time)/CLOCKS_PER_SEC;
        }
        cout << total_time/Q_RUNNING << ",";


        // QuickSort
        total_time = 0.0;
        for(int j=0;j<Q_RUNNING;j++)
        {
            insertRandomValuesToArray(a,nElements[i]);
            clock_t start_time = clock();
            QuickSort(a,0,nElements[i]-1);
            total_time += 1000.0*double(clock()- start_time)/CLOCKS_PER_SEC;
        }
        cout << total_time/Q_RUNNING << ",";


        // Randomized_QuickSort
        total_time = 0.0;
        for(int j=0;j<Q_RUNNING;j++)
        {
            insertRandomValuesToArray(a,nElements[i]);
            clock_t start_time = clock();
            RandomizedQuickSort(a,0,nElements[i]-1);
            total_time += 1000.0*double(clock()- start_time)/CLOCKS_PER_SEC;
        }
        cout << total_time/Q_RUNNING << ",";


        // InsertionSort
        total_time = 0.0;
        for(int j=0;j<Q_RUNNING;j++)
        {
            insertRandomValuesToArray(a,nElements[i]);
            clock_t start_time = clock();
            insertionSort(a,nElements[i]-1);
            total_time += 1000.0*double(clock()- start_time)/CLOCKS_PER_SEC;
        }
        cout << total_time/Q_RUNNING << ",";


        //QuickSort_SortedInput
        total_time = 0.0;
        for(int j=0;j<Q_RUNNING;j++)
        {
            clock_t start_time = clock();
            QuickSort(a,0,nElements[i]-1);
            total_time += 1000.0*double(clock()- start_time)/CLOCKS_PER_SEC;
        }
        cout << total_time/Q_RUNNING << ",";

        //Randomized_QuickSort_SortedInput
        total_time = 0.0;
        for(int j=0;j<Q_RUNNING;j++)
        {
            clock_t start_time = clock();
            RandomizedQuickSort(a,0,nElements[i]-1);
            total_time += 1000.0*double(clock()- start_time)/CLOCKS_PER_SEC;
        }
        cout << total_time/Q_RUNNING << ",";

        // STL_Sort
        total_time = 0.0;
        for(int j=0;j<Q_RUNNING;j++)
        {
            insertRandomValuesToArray(a,nElements[i]);
            clock_t start_time = clock();
            sort(a,a+nElements[i]);
            total_time += 1000.0*double(clock()- start_time)/CLOCKS_PER_SEC;
        }
        cout << total_time/Q_RUNNING;

        cout << "\n";

        delete[] a;

    }

    // cout back to screen
    cout.rdbuf(sb_cout);
    file.close();
    return 0;
}
