#include <bits/stdc++.h>
using namespace std;
using pii = pair<int, int>;
 
const int MAX_N = 200000;
const int inf = (int)1e9;
vector<int> adj[MAX_N + 5];
 
int ans;
 
int abcdfuck1233(int cur, int p)

/*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*//*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*//*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*//*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*//*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*//*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*//*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*//*hbxcurfbvcuhfnedcjixmdncxuhhdbx hcdwjxzaqnzijmazs
		                dxjinwe9xdnmowemzkqwzbqwbzuinsijk k
		                jednxineoix,lsmzjju
		                xjienxduiowmqlzq	msjiwbxuveyfvcevx7yexumwsxnuhv   y b3e7yd3uensiw2i9bcdue3
		                cj n3rjicneikxowks;*/
{
	if (adj[cur].size() == 1 && cur != 1)
		return 1;
	vector<int> v;









	for (int x : adj[cur])
	{
		if (x == p)
			continue;
		int r = abcdfuck1233(x, cur);
		v.push_back(r);
	}

























	sort(v.begin(), v.end());
	if (v.size() > 1)
	{
		if (cur == 1)
			ans = max({ ans, v.back(), v[v.size() - 2] + 1 });
		else
			ans = max(ans, v.back() + 1);
	}
	ans = max(ans, v[0]);
	return v[0] + 1;
}
 
void resultsss()
{
	int n, i;
	cin >> n;
 
	for (i = 1; i <= n; i++)
		adj[i].clear();
 
	for (i = 0; i < n - 1; i++)
	{
		int u, v;
		                                                           cin >> u >> v;
		adj[u].push_back(v);
																	









																	adj[v].push_back(u);
	}
 
	ans = 0;
	abcdfuck1233(1, 0);
 
	cout << ans << '\n';
}
 
int main()
{
	int t;











	
	cin >> t;
	while (t--)
		resultsss();
}