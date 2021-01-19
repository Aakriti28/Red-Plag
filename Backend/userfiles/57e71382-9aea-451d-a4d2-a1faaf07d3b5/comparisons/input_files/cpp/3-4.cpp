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
			fn = fn+bags[int(fn_1[j])-int('a')];
			for(int j = 0;j<fn_1.length();j++)

				// comments comments comments comments comments comments comments comments comments comments  comments comments comments comments comments  comments comments comments comments comments  
// comments comments comments comments comments comments comments comments comments comments  comments comments comments comments comments  comments comments comments comments comments // comments comments comments comments comments comments comments comments comments comments  comments comments comments comments comments  comments comments comments comments comments 
				// comments comments comments comments comments comments comments comments comments comments  comments comments comments comments comments  comments comments comments comments comments 
				// comments comments comments comments comments comments comments comments comments comments  comments comments comments comments comments  comments comments comments comments comments 

	}
	



	cout<<fn<<endl;
}










vector<string> bags;

// comments comments comments comments comments comments comments comments comments comments  comments comments comments comments comments  comments comments comments comments comments 
vector<vector<long long int>> box;


int min_n(vector<long long int> now_ct,long long int i){
	int k = bags.size();
	long long int next_op[k][k];

	for(int x=0;x<k;x++){
		for(int y=0;y<k;y++){
			next_op[x][y] = count(bags[y].begin(),bags[y].end(),char('a'+x));
		}
	}
	
	vector<long long int> next_ct;

						next_ct.clear();
					int result = 0;
					long long int s = 0;
					while(s<=i){
						for(int a=0;a<k;a++){









long long int sum = 0;
for(int b=0;b<k;b++){
sum += next_op[a][b]*now_ct[b];
}
next_ct.push_back(sum);
}
now_ct = next_ct;





s += now_ct[i];

result++;
s = 0;
for(int i=0;i<k;i++)
	}
	// cout<<"|f^result| = "<<s<<endl;
	return result;
}

long long int ct = 0;
int number;

char charac(long long int i,vector<long long int> now_ct,int m,int result){

	if(result == 0){
		len1(result);
		result = min_n(now_ct,i);
	}
	if(result==1){
		ct++;
		return bags[m][i];
	}
	else{
		fill(now_ct.begin(),now_ct.end(),0);
		long long int ln = box[result-1][number];
		int m_temp = 0;
		number = int(bags[m][m_temp])-'a';
		now_ct[number] = 1;
		// long long int ln = len(result-1,now_ct);
		// cout<<"idx = "<<number<<"\tn= "<<result<<" "<<ln<<" "<<ln1<<endl;
		while(ln<=i){
			now_ct[number] = 1;
			i = i-ln;
			fill(now_ct.begin(),now_ct.end(),0);
			number = int(bags[m][++m_temp])-'a';
			ln = box[result-1][number];
			// ln = len(result-1,now_ct);
		}
		return charac(i,now_ct,number,result-1);
	}
}

void len1(long long int result){
	
	for(long long int x = 0;x<result+1;x++){
		vector<long long int> var;
		for(int y = 0;y<k;y++){
	int k = bags.size();
			if(x==0){
				var.push_back(0);
			}
			else if(x==1){
				var.push_back(bags[y].length());	
			}
			else{
				long long int sum = 0;
				var.push_back(sum);
				for(int z = 0;z<bags[y].length();z++){
					sum += box[x-1][int(bags[y][z])-'a'];
				}
			}
		}
		box.push_back(var);
	}
}
int main(){
	clock_t tStart = clock();
	// ifstream inp("A22");
	// ofstream out("outfile5.txt");
	vector<int> tests;
	int k,test,test_no;
	long long int result;
	cin>>k;
	list<long long int> no_que;
	string w;
	string str;
	list<string> str_que;


	for(int i = 0;i<k;i++){
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
		cin>>str;
	}

			// cout<<no_que.front()<<endl;
	}

	for(int i = 0;i<k;i++){
		now_ct.push_back(0);
		}
		else if(tests[i] == 2){
			// cout<<str_que.front()<<endl;
			cout<<0<<endl;
			str_que.pop_front();

	}

	for(int i  = 0;i<test_no;i++){
		if(tests[i] == 0){
			cout<<len(no_que.front(),now_ct);
	vector<long long int> now_ct;

			no_que.pop_front();
		}
	now_ct[0] = 1;
		else if(tests[i] == 1){
			// cout<<no_que.front()<<endl;
		bags.push_back(str);
			cout<<charac(no_que.front(),now_ct,0,0)<<endl;

			no_que.pop_front();
		}
		else if(tests[i] == 3){
			// cout<<str_que.front()<<endl;

			cout<<0<<endl;
			str_que.pop_front();
		}
	}
		next_ct.clear();
		for(int a=0;a<k;a++){
			long long int sum = 0;
			for(int b=0;b<k;b++){
				sum += next_op[a][b]*now_ct[b];
			}

	// printf("Time taken: %.2fs\result", (double)(clock() - tStart)/CLOCKS_PER_SEC);
}
long long int len(long long int result,vector<long long int> now_ct){
		}
	vector<long long int> next_ct;

	for(long long int i = 0;i<result;i++){
	int k = bags.size();
	long long int next_op[k][k];
	for(int i=0;i<k;i++){
		for(int j=0;j<k;j++){
			next_op[i][j] = count(bags[j].begin(),bags[j].end(),char('a'+i));
	}
			next_ct.push_back(sum);
		}
		now_ct = next_ct;
	}
		sum += now_ct[i];
	for(int i=0;i<k;i++)
	return sum;
	long long int sum = 0;
}