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

void fn_calc(vector<string> bags, int result){
	string fn = "a";
			for(int i = 0;i<result;i++){
	string fn_1 = fn;
				


					fn.clear();
			for(int j = 0;j<fn_1.length();j++)
			fn = fn+bags[int(fn_1[j])-int('a')];


	}
	



	cout<<fn<<endl;
}










vector<string> bags;


vector<vector<long long int>> mat;


int min_n(vector<long long int> now_ct,long long int i){
	int k = bags.size();
	long long int next_op[k][k];

	for(int x=0;x<k;x++){
		for(int y=0;y<k;y++){
			next_op[x][y] = count(bags[y].begin(),bags[y].end(),char('a'+x));
		}
	}
	
	vector<long long int> next_ct;

					int result = 0;
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






result++;
s = 0;
for(int i=0;i<k;i++)
s += now_ct[i];
	}
	// cout<<"|f^result| = "<<s<<endl;
	return result;
}

long long int ct = 0;
int number;

char charac(long long int i,vector<long long int> now_ct,int m,int result){

	if(result == 0){
		result = min_n(now_ct,i);
		len1(result);
	}
	if(result==1){
		ct++;
		return bags[m][i];
	}
	else{
		fill(now_ct.begin(),now_ct.end(),0);
		int m_temp = 0;
		number = int(bags[m][m_temp])-'a';
		now_ct[number] = 1;
		long long int ln = mat[result-1][number];
		// long long int ln = len(result-1,now_ct);
		// cout<<"idx = "<<number<<"\tn= "<<result<<" "<<ln<<" "<<ln1<<endl;
		while(ln<=i){
			i = i-ln;
			fill(now_ct.begin(),now_ct.end(),0);
			number = int(bags[m][++m_temp])-'a';
			now_ct[number] = 1;
			ln = mat[result-1][number];
			// ln = len(result-1,now_ct);
		}
		return charac(i,now_ct,number,result-1);
	}
}

void len1(long long int result){
	
	int k = bags.size();
	for(long long int x = 0;x<result+1;x++){
		vector<long long int> var;
		for(int y = 0;y<k;y++){
			if(x==0){
				var.push_back(0);
			}
			else if(x==1){
				var.push_back(bags[y].length());	
			}
			else{
				long long int sum = 0;
				for(int z = 0;z<bags[y].length();z++){
					sum += mat[x-1][int(bags[y][z])-'a'];
				}
				var.push_back(sum);
			}
		}
		mat.push_back(var);
	}
}
int main(){
	clock_t tStart = clock();
	// ifstream inp("A22");
	// ofstream out("outfile5.txt");
	int k,test,test_no;
	cin>>k;
	string str;
	long long int result;
	string w;
	vector<int> tests;
	list<long long int> no_que;
	list<string> str_que;


	for(int i = 0;i<k;i++){
		cin>>str;
		bags.push_back(str);
	}

	cin>>test_no;
	for(int i  = 0;i<test_no;i++){
		cin>>test;
		tests.push_back(test);

		switch(test/2){
			case (0):
				cin>>result;
				no_que.push_back(result);
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
			cout<<len(no_que.front(),now_ct);

			no_que.pop_front();
		}
		else if(tests[i] == 2){
			// cout<<str_que.front()<<endl;

			cout<<0<<endl;
			str_que.pop_front();
		}
		else if(tests[i] == 1){
			// cout<<no_que.front()<<endl;
			cout<<charac(no_que.front(),now_ct,0,0)<<endl;

			no_que.pop_front();
		}
		else if(tests[i] == 3){
			// cout<<str_que.front()<<endl;

			cout<<0<<endl;
			str_que.pop_front();
		}
	}

	// printf("Time taken: %.2fs\result", (double)(clock() - tStart)/CLOCKS_PER_SEC);
}
long long int len(long long int result,vector<long long int> now_ct){
	int k = bags.size();
	long long int next_op[k][k];
	for(int i=0;i<k;i++){
		for(int j=0;j<k;j++){
			next_op[i][j] = count(bags[j].begin(),bags[j].end(),char('a'+i));
		}
	}
	vector<long long int> next_ct;

	for(long long int i = 0;i<result;i++){
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