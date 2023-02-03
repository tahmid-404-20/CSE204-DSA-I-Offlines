#include<iostream>
#include<cmath>
#include<limits>
#include<cstring>
using namespace std;

int setBit(long mask, int i)
{
    return mask|((unsigned long)1<<i);
}

bool isSet(long mask, int i)
{
    return mask & ((unsigned long)1<<i);
}

long findCost_DP(long **C, int **A, int n)
{
    for(int i=0; i<n;i++)
    {
       C[i][0] = 0;
       C[i][1<<i] = A[i][i];
    }
    long nstates = (long)pow(2,n);

    for(long city_mask = 1; city_mask<nstates;city_mask++)
    {
        for(int i=0;i<n;i++)
        {
            if(isSet(city_mask,i))
            {
                for(int j=0; j<n;j++)
                {
                    if(!isSet(city_mask,j))     // To the unvisited cities, unset bit
                    {
                        long extra_charge = 0;
                        long cur_cost;
                        for(int k=0;k<n;k++)
                        {
                            if(isSet(city_mask,k))
                                extra_charge += A[j][k];

                            // Taken from here
                        }
                        // Going into a unvisited city, so  extra_charge + entry fee + prev_cost
                        cur_cost = extra_charge + A[j][j] + C[i][city_mask]; // <- prev_cost
                        if(cur_cost<C[j][setBit(city_mask,j)])
                        {
                            C[j][setBit(city_mask,j)] = cur_cost;
                        }
                    }
                }
            }
        }
    }

    long min_cost = C[0][nstates-1];
    for(int i=1;i<n;i++)
    {
        if(min_cost>C[i][nstates-1])
        {
            min_cost = C[i][nstates-1];
        }
    }

    return min_cost;
}

int main() {

    int **A;
    long **C;

    int n;
    cin >> n;

    if(!n) {cout << 0 << endl; return 0;}

    A = new int*[n];
    C = new long*[n];
    long nstates = (long)pow(2,n);
    for(int i=0;i<n;i++)
    {
        A[i] = new int[n];
        C[i] = new long[nstates];
        for(int j=0;j<nstates;j++)
        {
            C[i][j] = LONG_MAX;
        }
    }

    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
            cin >> A[i][j];
    }

    cout << findCost_DP(C,A,n);

    // Memory delete
    for(int i=0;i<n;i++)
    {
        delete[] A[i];
        delete[] C[i];
    }

    delete[] A;
    delete[] C;

    return 0;
}
