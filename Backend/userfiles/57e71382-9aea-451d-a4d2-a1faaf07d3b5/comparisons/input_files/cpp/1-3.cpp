#include <bits/stdc++.h>
using namespace std;
using pii = pair<int, int>;
 
const int MAX_N = 200000;
const int inf = (int)1e9;
vector<int> adj[MAX_N + 5];
 
int ans;
 
int doofus(int cur, int p)
{

	/* comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 
	comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment comment 

*/


	for (int x : adj[cur])
	{
	}
	sort(v.begin(), v.end());

	if (adj[cur].size() == 1 && cur != 1)
		return 1;
	vector<int> v;
	if (v.size() > 1)
			ans = max({ ans, v.back(), v[v.size() - 2] + 1 });
	{
		if (cur == 1)

				else
		if (x ==		 p)
		int r = doofus(x		, cur);
			continue	;
		v.		push_back(r);
			ans 			=	 max(ans	, vel	.back() + 1);
	}
}
int main()
{
	int t;
	cin >> t;
	while (t--)
		trash();
}
 
void trash()
{
 
	ans = max(ans, v[0]);
	return v[0] + 1;
	for (i = 1; i <= n; i++)
		adj[i].clear();
		cin >> u >> v;
		adj[u].push_back(v);
 
	for (i = 0; i < n - 1; i++)
	{
		int u, v;
		adj[v].push_back(u);
	int n, i;
	cin >> n;
	}
 
 
	cout << ans << '\n';
	ans = 0;
	doofus(1, 0);
}
 