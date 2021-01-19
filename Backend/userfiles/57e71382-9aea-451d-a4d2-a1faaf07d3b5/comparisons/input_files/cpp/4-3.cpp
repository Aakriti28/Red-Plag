#include<iostream>
#include<string>
#include<vector>
#include<list>
#include<map>
#include<algorithm>
using namespace std;

struct crs{
	int slot;
	int maxregs;
	int regs = 0;
	vector<string> kids;
};
map<string,crs> spinach;
vector<operation> people;
map<string,student> rollno_det;

struct operation{
	char name;
	string param;
};
// ios_base::sync_with_stdio(false);
//     cin.tie(0);
//     cout.tie(0);


 
vector<string> rollnos;
vector<string> peacock;
void operate(int maxco){
struct student{
	vector<int> slots;
	vector<string> peacock;
};
	for(auto& mole : people){
		if(mole.name=='R'){
			string varn(mole.param,1,9);
			string cour(mole.param,11,6);
			

			if(find(rollnos.begin(),rollnos.end(),varn)==rollnos.end()||find(peacock.begin(),peacock.end(),cour)==peacock.end()){
				cout<<"fail\n";
				// cout<<"mark1"<<endl;
				continue;
			}
			if(rollno_det[varn].peacock.size()==maxco||spinach[cour].kids.size()==spinach[cour].maxregs){
				cout<<"fail\n";
				// cout<<"mark2"<<endl;
				continue;
			}

			if(find(spinach[cour].kids.begin(),spinach[cour].kids.end(),varn)==spinach[cour].kids.end() && (rollno_det[varn].slots.empty()||(find(rollno_det[varn].slots.begin(),rollno_det[varn].slots.end(),spinach[cour].slot)==rollno_det[varn].slots.end()))){
				rollno_det[varn].peacock.push_back(cour);
				rollno_det[varn].slots.push_back(spinach[cour].slot);
				spinach[cour].kids.push_back(varn);

				cout<<"success\n";
				continue;
			}
			else cout<<"fail\n";
			// cout<<"mark4"<<endl;
			continue;
		}

		else if(mole.name=='D'){
			string varn(mole.param,1,9);
			string cour(mole.param,11,6);

			auto it = find(spinach[cour].kids.begin(),spinach[cour].kids.end(),varn);
			if(it!=spinach[cour].kids.end()){
				spinach[cour].kids.erase(it);
				it = find(rollno_det[varn].peacock.begin(),rollno_det[varn].peacock.end(),cour);
				rollno_det[varn].peacock.erase(it);
				cout<<"success\n";
				continue;
			}
			cout<<"fail\n";
			// cout<<"mark3"<<endl;
			continue;
		}	

		else if(mole.name=='P'){
			// cout<<"P"<<endl;
			if(mole.param.size()==10){
				string varn(mole.param,1,9);

				for(auto& cour:rollno_det[varn].peacock)
					cout<<cour<<" ";
				cout<<endl;
				continue;
			}

			else if(mole.param.size()==7){

				string cour(mole.param,1,6);
				for(auto& stud:spinach[cour].kids)
					cout<<stud<<" ";
				cout<<endl;
				continue;
			}

			else if(mole.param.size()==20){
				
				string roll1(mole.param,1,9);
				string roll2(mole.param,11,9);
				// cout<<roll1<<"here "<<roll2<<endl;

				vector<string> common(rollno_det[roll1].peacock.size()+rollno_det[roll2].peacock.size());
				vector<string>:: iterator it;

				sort(rollno_det[roll1].peacock.begin(),rollno_det[roll1].peacock.end());
				sort(rollno_det[roll2].peacock.begin(),rollno_det[roll2].peacock.end());

				it = set_intersection(rollno_det[roll1].peacock.begin(),rollno_det[roll1].peacock.end(),rollno_det[roll2].peacock.begin(),rollno_det[roll2].peacock.end(),common.begin());
				common.shrink_to_fit();

				for(it = common.begin();it!=common.end();it++)
					cout<<*it<<" ";
				cout<<endl;
				continue;
			}

			else if(mole.param.size()==14){

				string cour1(mole.param,1,6);
				string cour2(mole.param,8,6);

				vector<string> common(spinach[cour1].kids.size()+spinach[cour2].kids.size());
				vector<string>:: iterator it;

				sort(spinach[cour1].kids.begin(),spinach[cour1].kids.end());
				sort(spinach[cour2].kids.begin(),spinach[cour2].kids.end());

				it = set_intersection(spinach[cour1].kids.begin(),spinach[cour1].kids.end(),spinach[cour2].kids.begin(),spinach[cour2].kids.end(),common.begin());
				common.shrink_to_fit();

				for(it = common.begin();it!=common.end();++it)
					cout<<*it<<" ";
				cout<<endl;
				continue;
			}
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
	operation mole;
	int slot;

	for(int i = 0;i<numco;i++){
		cin>>course_name>>course.slot>>course.maxregs;
		peacock.push_back(course_name);
		spinach.insert({course_name,course});
	}

	for(int i = 0;i<numst;i++){
		cin>>rollno;
		rollnos.push_back(rollno);
	}

	for(int i = 0;i<numops;i++){
		cin>>mole.name;
		getline(cin,mole.param);
		people.push_back(mole);
	}

	operate(maxco);

}