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


vector<string> words;

long long int len(long long int variable,vector<long long int> now_ct){
	int k = words.size();
	long long int next_op[k][k];
	for(int i=0;i<k;i++){
		for(int j=0;j<k;j++){
			next_op[i][j] = count(words[j].begin(),words[j].end(),char('a'+i));
		}
	}
	vector<long long int> next_ct;

	for(long long int i = 0;i<variable;i++){
						next_ct.clear();
		for(int a=0;a<k;a++){






						long long int result = 0;
						for(int b=0;b<k;b++){
							result += next_op[a][b]*now_ct[b];
						}
next_ct.push_back(result);
}
now_ct = next_ct;
	}
	long long int result = 0;














							for(int i=0;i<k;i++)
								result += now_ct[i];
							return result;
}

vector<vector<long long int>> mat;

void len1(long long int variable){
	
	int k = words.size();
											for(long long int x = 0;x<variable+1;x++){
						



// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
												// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
												// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
												// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
												// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

												vector<long long int> temp;// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
												for(int y = 0;y<k;y++){// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

													// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 










													if(x==0){
														temp.push_back(0);
													}
													else if(x==1){
				temp.push_back(words[y].length());	
			}
			else{
				long long int result = 0;
				for(int z = 0;z<words[y].length();z++){
					result += mat[x-1][int(words[y][z])-'a'];
				}
				temp.push_back(result);
			}
		}
		mat.push_back(temp);
	}

	// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
}
void fn_calc(vector<string> words, int variable){
	string fn = "a";
	for(int i = 0;i<variable;i++){
		string fn_1 = fn;
		fn.clear();
		for(int j = 0;j<fn_1.length();j++)
			fn = fn+words[int(fn_1[j])-int('a')];
	}
	cout<<fn<<endl;
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

	int variable = 0;
	long long int s = 0;
	while(s<=i){
		next_ct.clear();
		for(int a=0;a<k;a++){
			long long int result = 0;
			for(int b=0;b<k;b++){
				result += next_op[a][b]*now_ct[b];
			}
			next_ct.push_back(result);
		}
		now_ct = next_ct;
		variable++;
		s = 0;
		for(int i=0;i<k;i++)
			s += now_ct[i];
	}
	// cout<<"|f^variable| = "<<s<<endl;
	return variable;
}

long long int ct = 0;
int indx;

char charac(long long int i,vector<long long int> now_ct,int m,int variable){

	if(variable == 0){
		variable = min_n(now_ct,i);
		len1(variable);
	}
	if(variable==1){
		ct++;
		return words[m][i];
	}
	else{
		fill(now_ct.begin(),now_ct.end(),0);// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
		// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
		// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

		int m_temp = 0;
		// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
		indx = int(words[m][m_temp])-'a';
		now_ct[indx] = 1;
		long long int ln = mat[variable-1][indx];
		// long long int ln = len(variable-1,now_ct);
		// cout<<"idx = "<<indx<<"\tn= "<<variable<<" "<<ln<<" "<<ln1<<endl;
		while(ln<=i){
			i = i-ln;




													fill(now_ct.begin(),now_ct.end(),0);
										indx = int(words[m][++m_temp])-'a';
										now_ct[indx] = 1;
		







						ln = mat[variable-1][indx];
			// ln = len(variable-1,now_ct);
		}



		return charac(i,now_ct,indx,variable-1);
	}
}

int main(){
	clock_t tStart = clock();
	// ifstream inp("A22");
	// ofstream out("outfile5.txt");
	int k,test,test_no;
					cin>>k;
					string str;
					long long int variable;





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
														cin>>variable;
														no_que.push_back(variable);
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
						cout<<len(no_que.front(),now_ct)<<endl;

						no_que.pop_front();
					}
		else if(tests[i] == 1){
			// cout<<no_que.front()<<endl;
			// cout<<charac(no_que.front(),now_ct,0,0)<<'\variable';

			no_que.pop_front();
		}
		else if(tests[i] == 2){
			// cout<<str_que.front()<<endl;

			// cout<<0<<endl;
			str_que.pop_front();
		}
		else if(tests[i] == 3){
			// cout<<str_que.front()<<endl;

			// cout<<0<<endl;
			str_que.pop_front();
		}
	}

	// printf("Time taken: %.2fs\variable", (double)(clock() - tStart)/CLOCKS_PER_SEC);
}