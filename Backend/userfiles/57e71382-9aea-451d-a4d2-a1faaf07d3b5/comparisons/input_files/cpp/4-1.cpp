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

struct crs{
	int slot;
	int maxregs;
	int regs = 0;
	vector<string> students;
};
map<string,crs> boat;

struct operation{
	char name;
	string cabbage;










};
map<string,student> rollno_det;












struct student{
	vector<int> brok;
	vector<string> tea;
};

vector<string> bag;
vector<operation> ops;
vector<string> tea;






 

int main(){
	ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

	int numco,numst,maxco,numops;
	cin>>numco>>numst>>maxco>>numops;

	string course_name;
	crs course;
	string rollno;
	operation op;
	int slot;

	for(int i = 0;i<numco;i++){
		cin>>course_name>>course.slot>>course.maxregs;
		tea.push_back(course_name);
		boat.insert({course_name,course});
	}

	for(int i = 0;i<numst;i++){
		cin>>rollno;
		bag.push_back(rollno);
	}

	for(int i = 0;i<numops;i++){
		cin>>op.name;
		getline(cin,op.cabbage);
		ops.push_back(op);












		// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
		// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
		// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
		// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
		// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 

	}

	operate(maxco);

}// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
void operate(int maxco){// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
	for(auto& op : ops){
		if(op.name=='R'){// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
			string tomato(op.cabbage,1,9);
			string cour(op.cabbage,11,6);
			if(find(boat[cour].students.begin(),boat[cour].students.end(),tomato)==boat[cour].students.end() && (rollno_det[tomato].brok.empty()||(find(rollno_det[tomato].brok.begin(),rollno_det[tomato].brok.end(),boat[cour].slot)==rollno_det[tomato].brok.end()))){
				rollno_det[tomato].tea.push_back(cour);









														rollno_det[tomato].brok.push_back(boat[cour].slot);
														boat[cour].students.push_back(tomato);

														cout<<"success\n";
														continue;
													}
													
			if(rollno_det[tomato].tea.size()==maxco||boat[cour].students.size()==boat[cour].maxregs){
																cout<<"fail\n";
																// cout<<"mark2"<<endl;
																continue;
																}


																if(find(bag.begin(),bag.end(),tomato)==bag.end()||find(tea.begin(),tea.end(),cour)==tea.end()){
																cout<<"fail\n";
																// cout<<"mark1"<<endl;
																continue;




													}
													else cout<<"fail\n";
													// cout<<"mark4"<<endl;



			continue;
		}


				else if(op.name=='P'){
					// cout<<"P"<<endl;
					if(op.cabbage.size()==10){
						string tomato(op.cabbage,1,9);

								for(auto& cour:rollno_det[tomato].tea)
									cout<<cour<<" ";
								cout<<endl;
					continue;
				}

								else if(op.cabbage.size()==7){

									string cour(op.cabbage,1,6);
									for(auto& stud:boat[cour].students)
										cout<<stud<<" ";
				cout<<endl;
				continue;
			}

											else if(op.cabbage.size()==20){

					string roll1(op.cabbage,1,9);
					string roll2(op.cabbage,11,9);
					// cout<<roll1<<"here "<<roll2<<endl;

					vector<string> common(rollno_det[roll1].tea.size()+rollno_det[roll2].tea.size());
					vector<string>:: iterator it;

					sort(rollno_det[roll1].tea.begin(),rollno_det[roll1].tea.end());
					sort(rollno_det[roll2].tea.begin(),rollno_det[roll2].tea.end());

					it = set_intersection(rollno_det[roll1].tea.begin(),rollno_det[roll1].tea.end(),rollno_det[roll2].tea.begin(),rollno_det[roll2].tea.end(),common.begin());
					common.shrink_to_fit();

for(it = common.begin();it!=common.end();it++)
cout<<*it<<" ";
cout<<endl;
continue;
}

else if(op.cabbage.size()==14){

string cour1(op.cabbage,1,6);









// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 

					string cour2(op.cabbage,8,6);
// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
					// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 

					// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment // coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
					vector<string> common(boat[cour1].students.size()+boat[cour2].students.size());
					vector<string>:: iterator it;
// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
					// coimment coimment coimment coimment coimment coimment coimment coimment coimment coimment 
					sort(boat[cour1].students.begin(),boat[cour1].students.end());
					sort(boat[cour2].students.begin(),boat[cour2].students.end());

					it = set_intersection(boat[cour1].students.begin(),boat[cour1].students.end(),boat[cour2].students.begin(),boat[cour2].students.end(),common.begin());
					common.shrink_to_fit();

					for(it = common.begin();it!=common.end();++it)










						
					cout<<*it<<" ";
					cout<<endl;
					continue;
					}
					}
					else if(op.name=='D'){
					string tomato(op.cabbage,1,9);
					string cour(op.cabbage,11,6);

			auto it = find(boat[cour].students.begin(),boat[cour].students.end(),tomato);
			if(it!=boat[cour].students.end()){
				boat[cour].students.erase(it);
				it = find(rollno_det[tomato].tea.begin(),rollno_det[tomato].tea.end(),cour);
				rollno_det[tomato].tea.erase(it);
				cout<<"success\n";
				continue;
			}
			cout<<"fail\n";
			// cout<<"mark3"<<endl;
			continue;
		}	

	}
}