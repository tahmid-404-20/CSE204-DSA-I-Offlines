#include<iostream>
#include  <bits/stdc++.h>
#include<cstring>
#include<string>
using namespace std;

#define SIZE 50

char* construct_LCS(char *x, char* y, int** l, int **d)
{
    int m = strlen(x);
    int n = strlen(y);

    int i = l[m][n];
    char* lcs = new char[i+1];
    lcs[i--] = '\0';

    while(m!=0 && n!=0)
    {
        if(d[m][n] == 1)
        {
            lcs[i--] = x[m-1];
            m--;
            n--;

        } else if(d[m][n] == 2)
        {
            m--;
        } else
        {
            n--;
        }
    }

    return lcs;
}

int lcs_dp(char *x, char *y, int **l, int **d)
{
    int m = strlen(x);
    int n = strlen(y);

    for(int i=0;i<=n;i++)
        l[0][i] = 0;

    for(int i=0;i<=m;i++)
        l[i][0] = 0;



    for(int i=1;i<=m;i++)
    {
        for(int j=1;j<=n;j++)
        {
            if(x[i-1] == y[j-1]) {
                l[i][j] = l[i-1][j-1] + 1;
                d[i][j]=  1;
            }
            else {
                if(l[i-1][j]>l[i][j-1])
                {
                    l[i][j] = l[i-1][j];        // taken from above
                    d[i][j] = 2;
                } else
                {
                    l[i][j] = l[i][j-1];
                    d[i][j] = 3;
                }
            }
        }
    }
    return l[m][n];
}


int main()
{
    char *str1, *str2;

    str1 = new char[SIZE+1];
    str2 = new char[SIZE+1];

    cin.getline(str1,SIZE+1);
    cin.getline(str2,SIZE+1);

    int m = strlen(str1);
    int n = strlen(str2);
    int **l = new int*[m+1];
    int **d = new int*[m+1];

    for(int i=0;i<=m;i++)
    {
        l[i] = new int[n+1];
        d[i] = new int[n+1];
    }

    int lcs_length = lcs_dp(str1,str2,l,d);
    char* lcs = construct_LCS(str1,str2,l,d);

    cout << lcs_length << endl;
    cout << lcs << endl;

    for(int i=0;i<=m;i++)
    {
        for(int j=0;j<=n;j++)
            cout << l[i][j] << " ";
        cout << endl;
    }


    // Memory deletion
    delete[] str1;
    delete[] str2;
    delete[] lcs;

    for(int i=0;i<=m;i++)
    {
        delete[] l[i];
        delete[] d[i];
    }

    delete[] l;
    delete[] d;

    return 0;
}
