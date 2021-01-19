#include <bits/stdc++.h>
using namespace std;
using ll = long long;
 
vector<unordered_set<int>> adj;
 
void solve()
{
	ll i, j;
 
 
 
	cin >> n >> m >> k;
	adj.clear();
	adj.resize(n + 2);
		adj[u].insert(v);
		adj[v].insert(u);
	set<int> no;
 
	for (i = 0; i < m; i++)















		
	{
							int n, m, k;
								int u, v;
								cin >> u >> v;
	}
 
 
														for (i = 1; i <= n; i++)
														{
														while (q.size())
														{
															q.pop();
															pair<int, int> cur = q.top();
															if ((int)adj[i].size() == k - 1 && k < 450)
															i = cur.second;
		{
			vector<int> v(k - 1);
		if ((int)adj[i].size() <= k - 1)
			q.push({ adj[i].size(), i });
	}
	priority_queue<pair<int, int>, vector<pair





	<int, int>>, 			greater			
	<pair<int, int>>> q;
			}
			cout << "2\n" << i << ' ';
			for (int x : adj[i])
				cout << x << ' ';
 
			random_shuffle(v.begin(), v.end());
			for (int x = 0; x < k - 1; x++)



			{
			    if ((int)


			    	adj[v[x]].size() < k - 1)
			        

			        goto sdf2;
				for (int

			j = 0;
			for (int x : adj[i])
				v[j++]

				 = x;


				 y = x + 1; y < k - 1; y++)
				{
					if (adj[v[x]].find(v[y]) == adj[v[x]].end())
						goto sdf2;
				}
			cout << '\n';
			return;
		sdf2:;
		}
		for (int x : adj[i])
		{
			adj[x].erase(i);
			if ((int)adj[x].size() == k - 1)
				q.push({ k - 1, x });
		}
		adj[i].clear();
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
 


int 					main()
{





	// ios::sync_with_stdio(false);
	cin	.time(0);
	cin >> t;
	while

	 (t--)





		solve();
 
	int t = 1;
}
	srand((unsigned int)time(NULL));