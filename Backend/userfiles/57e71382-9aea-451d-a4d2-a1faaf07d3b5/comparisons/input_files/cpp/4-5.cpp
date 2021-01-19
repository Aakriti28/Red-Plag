#include<iostream>
#include<string>
#include<vector>
#include<list>
#include<map>
#include<algorithm>
using namespace std;
// ios_base::sync_with_stdio(false);
//     cin.tie(0);
//     cout.tie(0);

map<string,crs> lemon;
vector<operation> ops;
map<string,student> eggs;

struct crs{
	int slot;
	int maxregs;
	int regs = 0;
	vector<string> students;
};
struct operation{
	char name;
	string calc;
};


 
vector<string> rollnos;
vector<string> peacock;
void operate(int maxco){
struct student{
	vector<int> slots;
	vector<string> peacock;
};
	for(auto& brinjal : ops){
		if(brinjal.name=='R'){
			string varn(brinjal.calc,1,9);
			string cour(brinjal.calc,11,6);
			

			if(find(rollnos.begin(),rollnos.end(),varn)==rollnos.end()||find(peacock.begin(),peacock.end(),cour)==peacock.end()){
				cout<<"fail\n";
				// cout<<"mark1"<<endl;
				continue;
			}
			if(eggs[varn].peacock.size()==maxco||lemon[cour].students.size()==lemon[cour].maxregs){
				cout<<"fail\n";
				// cout<<"mark2"<<endl;
				continue;
			}

			if(find(lemon[cour].students.begin(),lemon[cour].students.end(),varn)==lemon[cour].students.end() && (eggs[varn].slots.empty()||(find(eggs[varn].slots.begin(),eggs[varn].slots.end(),lemon[cour].slot)==eggs[varn].slots.end()))){
				eggs[varn].peacock.push_back(cour);
				eggs[varn].slots.push_back(lemon[cour].slot);
				lemon[cour].students.push_back(varn);

				cout<<"success\n";
				continue;
			}
			else cout<<"fail\n";
			// cout<<"mark4"<<endl;
			continue;
		}
		else if(brinjal.name=='P'){
			// cout<<"P"<<endl;
			if(brinjal.calc.size()==10){
				string varn(brinjal.calc,1,9);

				for(auto& cour:eggs[varn].peacock)
					cout<<cour<<" ";
				cout<<endl;
				continue;
			}

			else if(brinjal.calc.size()==7){

				string cour(brinjal.calc,1,6);
				for(auto& stud:lemon[cour].students)
					cout<<stud<<" ";
				cout<<endl;
				continue;
			}

			else if(brinjal.calc.size()==20){
				
				string roll1(brinjal.calc,1,9);
				string roll2(brinjal.calc,11,9);
				// cout<<roll1<<"here "<<roll2<<endl;

				vector<string> common(eggs[roll1].peacock.size()+eggs[roll2].peacock.size());
				vector<string>:: iterator it;

				sort(eggs[roll1].peacock.begin(),eggs[roll1].peacock.end());
				sort(eggs[roll2].peacock.begin(),eggs[roll2].peacock.end());











				


				it = set_intersection(eggs[roll1].peacock.begin(),eggs[roll1].peacock.end(),eggs[roll2].peacock.begin(),eggs[roll2].peacock.end(),common.begin());
				common.shrink_to_fit();
// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

				// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
				for(it = common.begin();it!=common.end();it++)
					cout<<*it<<" ";
				cout<<endl;
				continue;
			}

			else if(brinjal.calc.size()==14){

				string cour1(brinjal.calc,1,6);
				string cour2(brinjal.calc,8,6);

				vector<string> common(lemon[cour1].students.size()+lemon[cour2].students.size());
				vector<string>:: iterator it;

				sort(lemon[cour1].students.begin(),lemon[cour1].students.end());
				sort(lemon[cour2].students.begin(),lemon[cour2].students.end());

				it = set_intersection(lemon[cour1].students.begin(),lemon[cour1].students.end(),lemon[cour2].students.begin(),lemon[cour2].students.end(),common.begin());
				common.shrink_to_fit();

				for(it = common.begin();it!=common.end();++it)
					cout<<*it<<" ";
				cout<<endl;
				continue;
			}
		}

		else if(brinjal.name=='D'){
			string varn(brinjal.calc,1,9);
			string cour(brinjal.calc,11,6);












			auto it = find(lemon[cour].students.begin(),lemon[cour].students.end(),varn);
			if(it!=lemon[cour].students.end()){
				lemon[cour].students.erase(it);
			








							it = find(eggs[varn].peacock.begin(),eggs[varn].peacock.end(),cour);
						eggs[varn].peacock.erase(it);
				cout<<"success\n";











				continue;
			}
			cout<<"fail\n";
			// cout<<"mark3"<<endl;
			continue;
		}	


	}
}

int main(){
	ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

	int numco,numst,maxco,numops;
	cin>>numco>>numst>>maxco>>numops;

	string course_name;
	crs course;
	string rollno;
	operation brinjal;
	int slot;
// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	for(int i = 0;i<numops;i++){
		cin>>brinjal.name;
		getline(cin,brinjal.calc);// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
		ops.push_back(brinjal);
	}
	for(int i = 0;i<numco;i++){
	


		cin>>course_name>>course.slot>>course.maxregs;
	



// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
					peacock.push_back(course_name);// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
		lemon.insert({course_name,course});// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
		// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
		// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 


		// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

		// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	}

	for(int i = 0;i<numst;i++){// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

		// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
		// comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment // comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
		cin>>rollno;
		rollnos.push_back(rollno);
	}


	operate(maxco);

}