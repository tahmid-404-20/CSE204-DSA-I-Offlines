#include<iostream>
#include<fstream>
#include<cstdio>
#include<queue>
#include<ctime>
#include <stdlib.h>
#include "heap.h" //You need to implement this.
#include "heap.h"

using namespace std;

int main()
{
//    priority_queue<int>pq;
//    vector<int>numbers;
//
//    for(int i=1;i<=15;i++)
//        numbers.push_back(i);
//
//    Heap h(numbers);
//    cout << h.getMax() << endl;
////    Heap h1 = h;
////    cout << h1.getMax() << endl;
////    Heap h2(10);
////    cout << h2.getMax() << endl;
////    h2 = h;
////    cout << h2.getMax() << endl;
////    h2.deleteKey();
//
//
//    h.deleteKey();
//    h.insert(200);
//    h.insert(400);
//    h.insert(500);
//
//
//    cout << h.getMax() << endl;
//    h.deleteKey();
//    cout << h.getMax() << endl;
//    h.deleteKey();
//    cout << h.getMax() << endl;
//    h.deleteKey();
//    cout << h.getMax() << endl;
//    h.deleteKey();
//    cout << h.getMax() << endl;
//    h.deleteKey();
//    cout << h.getMax() << endl;
//    h.deleteKey();
//
//    heapsort(numbers);
//    for(int i=0;i<numbers.size();i++)
//        cout << numbers[i] << " ";

    int k;
    cin >> k;
    Heap h(k);

    for(int i=1;;i++)
    {
        int value;
        cin >> value;
        if(i<= (k-1))
        {
            h.insert(value);
            cout << "Undefined" << endl;
        }

        else if(i == k)
        {
            h.insert(value);
            cout << h.getMax() << endl;
        }
        else{
            if(value<h.getMax())
            {
                h.deleteKey();
                h.insert(value);
            }
            cout << h.getMax() << "WHY" << endl;
        }
    }

    return 0;
}
