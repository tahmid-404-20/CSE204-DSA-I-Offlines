#include<cstdlib>
long partition_quick(int* A, long p, long r) {

    int x = A[r];
    long i = p-1;
    for(long j=p; j<r;j++)
    {
        if(A[j]<=x)
        {
            i++;
            int temp = A[j];
            A[j] = A[i];
            A[i] = temp;
        }
    }

    int temp = A[i+1];
    A[i+1] = A[r];
    A[r] = temp;

    return i+1;
}

long partition_quick_randomized(int* A, long p, long r) {

    // Choose a random index
    long n = r-p+1;
    srand(1);
    long m = p + rand()%n;

    // Swap A[r] and A[m]
    int temp = A[r];
    A[r] = A[m];
    A[m] = temp;

    return partition_quick(A,p,r);
}

void QuickSort(int *a, long p, long r) {
    if(p<r)
    {
        long q = partition_quick(a,p,r);
        QuickSort(a,p,q-1);
        QuickSort(a,q+1,r);
    }
}

void RandomizedQuickSort(int *a, long p, long r) {
    if(p<r)
    {
        long q = partition_quick_randomized(a,p,r);
        RandomizedQuickSort(a,p,q-1);
        RandomizedQuickSort(a,q+1,r);
    }
}

void Merge(int *A,long p, long q, long r){
    long n_l = q-p+1;
    long n_r = r-q;

    int* l = new int[n_l];
    int* h = new int[n_r];

    long i,j;
    for(i=0,j=p;j<=q;i++,j++)
        l[i] = A[j];

    for(i=0,j=q+1;j<=r;i++,j++)
        h[i] = A[j];

    i = 0;
    j = 0;
    long k = p;

    for(long k=p;k<=r;k++) {
        if(i<n_l && j<n_r)
        {
            if(l[i] <= h[j])
            {
                A[k] = l[i++];
            }
            else
            {
                A[k] = h[j++];
            }
        }
        else if(i >= n_l && j<=n_r) {
            while(j< n_r)
                A[k++] = h[j++];

            break;
        }
        else if(i <= n_l && j>=n_r) {
            while(i < n_l)
                A[k++] = l[i++];

            break;
        }

    }

    delete[] l;
    delete[] h;

}

void MergeSort(int *a, long p, long r){
    if(p<r)
    {
        long q = (p+r)/2;
        MergeSort(a,p,q);
        MergeSort(a,q+1,r);
        Merge(a,p,q,r);
    }
}

void insertionSort(int *a, long n)
{

    for(int i=1;i<=n;i++)
    {
        int x = a[i];
        long j = i-1;

        while(j>=0 && a[j]>x)
        {
            a[j+1] = a[j];
            j--;
        }

        a[j+1] = x;
    }
}


