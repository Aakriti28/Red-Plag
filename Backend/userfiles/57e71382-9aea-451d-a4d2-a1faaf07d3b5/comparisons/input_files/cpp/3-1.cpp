#include<iostream>
#include<string>
#include<vector>
#include<list>
#include<algorithm>
#include<iterator>
#include<numeric>
#include<time.h>
#include<fstream>
using namespace std;

void fn_calc(vector<string> words, int n){
	string fn = "a";
	for(int i = 0;i<n;i++){
		string fn_1 = fn;
		fn.clear();
		for(int j = 0;j<fn_1.length();j++)
			fn = fn+words[int(fn_1[j])-int('a')];
	}
	cout<<fn<<endl;
}

vector<string> words;

long long int len(long long int n,vector<long long int> now_ct){
	int k = words.size();
	long long int next_op[k][k];
	for(int i=0;i<k;i++){
		for(int j=0;j<k;j++){
			next_op[i][j] = count(words[j].begin(),words[j].end(),char('a'+i));
		}
	}
	vector<long long int> next_ct;

	for(long long int i = 0;i<n;i++){
		next_ct.clear();
		for(int a=0;a<k;a++){
			long long int sum = 0;
			for(int b=0;b<k;b++){
				sum += next_op[a][b]*now_ct[b];
			}
			next_ct.push_back(sum);
		}
		now_ct = next_ct;
	}
	long long int sum = 0;
	for(int i=0;i<k;i++)
		sum += now_ct[i];
	return sum;
}

vector<vector<long long int>> mat;

void len1(long long int n){
	
	int k = words.size();
	for(long long int x = 0;x<n+1;x++){
		vector<long long int> temp;
		for(int y = 0;y<k;y++){
			if(x==0){
				temp.push_back(0);
			}
			else if(x==1){
				temp.push_back(words[y].length());	
			}
			else{
				long long int sum = 0;
				for(int z = 0;z<words[y].length();z++){
					sum += mat[x-1][int(words[y][z])-'a'];
				}
				temp.push_back(sum);
			}
		}
		mat.push_back(temp);
	}
}

int min_n(vector<long long int> now_ct,long long int i){
	int k = words.size();
	long long int next_op[k][k];

	for(int x=0;x<k;x++){
		for(int y=0;y<k;y++){
			next_op[x][y] = count(words[y].begin(),words[y].end(),char('a'+x));
		}
	}
	
	vector<long long int> next_ct;

	int n = 0;
	long long int s = 0;
	while(s<=i){
		next_ct.clear();
		for(int a=0;a<k;a++){
			long long int sum = 0;
			for(int b=0;b<k;b++){
				sum += next_op[a][b]*now_ct[b];
			}
			next_ct.push_back(sum);
		}
		now_ct = next_ct;
		n++;
		s = 0;
		for(int i=0;i<k;i++)
			s += now_ct[i];
	}
	// cout<<"|f^n| = "<<s<<endl;
	return n;
}

long long int ct = 0;
int indx;

char charac(long long int i,vector<long long int> now_ct,int m,int n){

	if(n == 0){
		n = min_n(now_ct,i);
		len1(n);
	}
	if(n==1){
		ct++;
		return words[m][i];
	}
	else{
		fill(now_ct.begin(),now_ct.end(),0);
		int m_temp = 0;
		indx = int(words[m][m_temp])-'a';
		now_ct[indx] = 1;
		long long int ln = mat[n-1][indx];
		// long long int ln = len(n-1,now_ct);
		// cout<<"idx = "<<indx<<"\tn= "<<n<<" "<<ln<<" "<<ln1<<endl;
		while(ln<=i){
			i = i-ln;
			fill(now_ct.begin(),now_ct.end(),0);
			indx = int(words[m][++m_temp])-'a';
			now_ct[indx] = 1;
			ln = mat[n-1][indx];
			// ln = len(n-1,now_ct);
		}
		return charac(i,now_ct,indx,n-1);
	}
}

int main(){
	clock_t tStart = clock();
	// ifstream inp("A22");
	// ofstream out("outfile5.txt");
	int k,test,test_no;
	cin>>k;
	string str;
	long long int n;
	string w;
	vector<int> tests;
	list<long long int> no_que;
	list<string> str_que;


	for(int i = 0;i<k;i++){
		cin>>str;
		words.push_back(str);
	}

	cin>>test_no;
	for(int i  = 0;i<test_no;i++){
		cin>>test;
		tests.push_back(test);

		switch(test/2){
			case (0):
				cin>>n;
				no_que.push_back(n);
				break;
			case (1):
				cin>>w;
				str_que.push_back(w);
				break;
			default:
				break;
		}
	}

	vector<long long int> now_ct;
	for(int i = 0;i<k;i++){
		now_ct.push_back(0);
	}
	now_ct[0] = 1;

	for(int i  = 0;i<test_no;i++){
		if(tests[i] == 0){
			// cout<<no_que.front()<<endl;
			cout<<len(no_que.front(),now_ct)<<'\n';

			no_que.pop_front();
		}
		else if(tests[i] == 1){
			// cout<<no_que.front()<<endl;
			cout<<charac(no_que.front(),now_ct,0,0)<<'\n';

			no_que.pop_front();
		}
		else if(tests[i] == 2){
			// cout<<str_que.front()<<endl;

			cout<<0<<endl;
			str_que.pop_front();
		}
		else if(tests[i] == 3){
			// cout<<str_que.front()<<endl;

			cout<<0<<endl;
			str_que.pop_front();
		}
	}

	// printf("Time taken: %.2fs\n", (double)(clock() - tStart)/CLOCKS_PER_SEC);
}