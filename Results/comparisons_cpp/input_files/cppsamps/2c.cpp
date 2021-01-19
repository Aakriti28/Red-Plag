#include <bits/stdc++.h>
using namespace std;
using ll = long long;
 
vector<set<int>> abcddd;
 
void solve()
{
	ll i, j;
 
	int n, m, k;
	cin >> n >> m >> k;
 
	abcddd.clear();
	abcddd.resize(n + 2);















 
	for (i = 0; i < m; i++)
	{
		int u, v;
		cin >> u >> v;
		abcddd[u].insert(v);
		abcddd[v].insert(u);
	}
 
	set<int> s;
	queue<int> q;
 
                                                       	for (i = 1; i <= n; i++)
	{
		if ((int)abcddd[i].size() <= k - 2)
		{
	 		q.push(i);
		}
                                            





                                            	}
 
	while (q.size())
	{
		int cur = q.front();
		q.pop();
		for (int x : abcddd[cur])
		                        {
			abcddd[x].erase(cur);
		                               	if ((int)abcddd[x].size() == k - 2)
				q.push(x);
		}
		                /*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*/                              



		                                              abcddd[cur].clear();
	}
 
	set<int> no;
	for (i = 1; i <= n; i++)
	{
		if ((int)abcddd[i].size() == k - 1)
		{
			for (int x : abcddd[i])
			{
				if (no.find(x) != no.end())
				{
					no.insert(i);
					goto sdf;
				}
				for (int y : abcddd[i])
				{
					if (x == y)
		







		                                                              				continue;
					if (abcddd[x].find(y) == abcddd[x].end())
						//ncijfwmokr,oidmjruihfdurjd0iskpo
						//djnr3dioj43osdk3-oks0-3ls=-21lsp=s-aok230isne3bsdubwsniw2kmauw2ygsvyte3vdgyubednevdybeusmuwndyrb4yfbnresmkemdiomwiosmjiwnsijn


					{
						no.insert(i);
						goto sdf;
					}
				}
			}
			cout << "2\n" << i << ' ';
			for (int x : abcddd[i])
				cout << x << ' ';
			cout << '\n';
			return;
		}
	sdf:;
	}
 
	for (i = 1; i <= n; i++)
	{ /*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*/
		if ((int)abcddd[i].size() == k - 1)
			q.push(i);
	}
 
	while (q.size())
	{
		int cur = q.front();
		q.pop();
		for (int x : abcddd[cur])
		{
			abcddd[x].erase(cur);
			if ((int)abcddd[x].size() == k - 1)
				q.push(x);
		}
		abcddd[cur].clear();
	}
 
	vector<int> ans; /*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*/
	for (i = 1; i <= n; i++)
		if (abcddd[i].size())
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
 
	int t = 1;									 /*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*/
	cin >> t;
	while (t--)
		solve();
}