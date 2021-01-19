#include <bits/stdc++.h>
using namespace std;
using ll = long long;
 
vector<set<int>> adj;
 
void solve()
{
	ll i, j;
 
	int n, m, k;
	cin >> n >> m >> k;
 
	adj.clear();
	adj.resize(n + 2);
 
	for (i = 0; i < m; i++)
	{
		int u, v;
		cin >> u >> v;
		adj[u].insert(v);
		adj[v].insert(u);
	}
 
	set<int> s;
	queue<int> q;
 
	for (i = 1; i <= n; i++)
	{
		if ((int)adj[i].size() <= k - 2)
		{
			q.push(i);
		}
	}
 
	while (q.size())
	{
		int cur = q.front();
		q.pop();
		for (int x : adj[cur])
		{
			adj[x].erase(cur);
			if ((int)adj[x].size() == k - 2)
				q.push(x);
		}
		adj[cur].clear();
	}
 
	set<int> no;
	for (i = 1; i <= n; i++)
	{
		if ((int)adj[i].size() == k - 1)
		{
			for (int x : adj[i])
			{
				if (no.find(x) != no.end())
				{
					no.insert(i);
					goto sdf;
				}
				for (int y : adj[i])
				{
					if (x == y)
						continue;
					if (adj[x].find(y) == adj[x].end())
					{
						no.insert(i);
						goto sdf;
					}
				}
			}
			cout << "2\n" << i << ' ';
			for (int x : adj[i])
				cout << x << ' ';
			cout << '\n';
			return;
		}
	sdf:;
	}
 
	for (i = 1; i <= n; i++)
	{
		if ((int)adj[i].size() == k - 1)
			q.push(i);
	}
 
	while (q.size())
	{
		int cur = q.front();
		q.pop();
		for (int x : adj[cur])
		{
			adj[x].erase(cur);
			if ((int)adj[x].size() == k - 1)
				q.push(x);
		}
		adj[cur].clear();
	}
 
	vector<int> ans;
	for (i = 1; i <= n; i++)
		if (adj[i].size())
			ans.push_back(i);
	if (ans.size())
	{
		cout << "1 " << ans.size() << '\n';
		for (int x : ans)
			cout << x << ' ';
		cout << '\n';
	}
	else
		cout << "-1\n";
}
 
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);
 
	int t = 1;
	cin >> t;
	while (t--)
		solve();
}