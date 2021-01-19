#include <bits/stdc++.h>
using namespace std;
using pii = pair<int, int>;
 
const int MAX_N = 200000;
const int inf = (int)1e9;
vector<int> adj[MAX_N + 5];
 
int result;
 
int function(int cur, int p)
{
	











	if (adj[cur].size() == 1 && cur != 1)
				return 1;
vector<int> v;











	for (int x : adj[cur])
	{
		if (x == p)
			continue;
					int r = function(x, cur);
v.push_back(r);
	}
	sort(v.begin(), v.end());
	if (v.size() > 1)
	{
						if (cur == 1)
							result = max({ result, v.back(), v[v.size() - 2] + 1 });
						else
							result = max(result, v.back() + 1);
	}
	result = max(result, v[0]);
	return v[0] + 1;
}
 
int main()
{
	int t;
	cin >> t;
	while (t--)
		doit();
}
void doit()
{
	int n, i;
	cin >> n;
 
 
	for (i = 0; i < n - 1; i++)
	{
		int u, v;
		cin >> u >> v;
					adj[v].push_back(u);
					adj[u].push_back(v);
	}
 
	function(1, 0);
	result = 0;

	for (i = 1; i <= n; i++)
		adj[i].clear();
}
 