#pragma once
using std::vector;
class Heap{
    int* arr;
    long array_size;
    long count;

    void maxHeapify(int *arr, long i) {
        long l = i*2;
        long r = l+1;

        long largest = i;
        if(l <= count && arr[i] < arr[l]) {
            largest = l;
        }
        if(r <=count && arr[largest] < arr[r]){
            largest = r;
        }

        if(largest != i){
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            maxHeapify(arr,largest);
        }
    }

    void buildHeap() {
        for(long i=count/2;i>=1;i--)
            maxHeapify(this->arr,i);
    }

    void increaseSize() {
        int* temp = new int[2*array_size-1];        // keep 2n elements now
        for(int i=1;i<=count;i++)
            temp[i] = arr[i];

        delete []arr;
        arr = temp;
        array_size = 2*array_size-1;
    }

public:
    //Constructors
    Heap(int size){
        this->array_size = size+1;
        arr = new int[this->array_size];
        count = 0;
    }

    Heap(const vector<int>& numbers)
    {
        this->array_size = numbers.size()+1;
        arr = new int[this->array_size];
        count = numbers.size();

        for(long  i=1;i<=count;i++){
            arr[i] = numbers[i-1];
        }
        buildHeap();
    }

    // Destructor
    ~Heap() {
        delete []arr;
    }

    Heap& operator=(const Heap &h) {
        this->array_size = h.array_size;
        this->count = h.count;
        delete []this->arr;
        this->arr = new int[this->array_size];
        for(int i=1;i<=this->count;i++)
            this->arr[i] = h.arr[i];

        return *this;
    }

    Heap(const Heap &h)
    {
        this->array_size = h.array_size;
        this->count = h.count;
        this->arr = new int[this->array_size];
        for(int i=1;i<=this->count;i++)
            this->arr[i] = h.arr[i];
    }

    void insert(int n) {

        //Size update
        if(count+1 >= array_size)
            increaseSize();

        arr[++count] = n;

        long  k = count;
        while(k>1) {
            if(arr[k >> 1] < arr[k]){
                int temp = arr[k];
                arr[k] = arr[k >> 1];
                arr[k >> 1] = temp;
            } else {
                break;
            }
            k >>= 1;
        }
    }

    int getMax() const {
        if(count !=0 ){
            return arr[1];
        }
        std::cout << "Heap empty! Can't get max!";
        return -1;
    }

    void deleteKey() {
        if(count != 0) {
            arr[1] = arr[count];
            count--;
            maxHeapify(arr,1);
        }
    }

    long size() const {
        return count;
    }
};

void heapsort(vector<int> &v) {

    int length = v.size();
    Heap h(v);

    for(int i=0;i<length;i++){
        v[i] = h.getMax();
        h.deleteKey();
    }
}
