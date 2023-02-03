#include<iostream>
#include<fstream>
#include<cstdio>
#include<queue>
#include<ctime>
#include<cstdlib>
#include "heap.h" //You need to implement this.

using namespace std;

class PriorityQueue{
    Heap *h;

public:
    PriorityQueue(long size){
        h = new Heap(size);
    }

    PriorityQueue(const vector<int> &v) {
        h = new Heap(v);
    }

    void insert(int x)
    {
        h->insert(x);
    }

    void deleteMax() {
        h->deleteKey();
    }

    int getMax() const
    {
        h->getMax();
    }

    long size() const {
        h->size();
    }
};

int main() {

    int size;

    cin >> size;
    vector<int> numbers;

    for(int i=1;i<=100;i++)
    {
        numbers.push_back(i);
    }

    PriorityQueue p1(size), p2(numbers);
    for(int i=1;i<=100;i++)
        p1.insert(i);

    for(int i=0;i<10;i++){
        cout << p1.getMax() << " " << p2.getMax() << endl;
        p1.deleteMax();
        p2.deleteMax();
    }
    return 0;
}
