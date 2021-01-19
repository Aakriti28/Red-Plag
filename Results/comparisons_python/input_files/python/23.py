import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as var2
import pandas as pd
import numpy as np
import pdb
import argparse
import sqlite3 as sq
from collections import defaultdict as dd
connection=sq.connect("ipl.db")
crsr=connection.cursor()

s="""CREATE TABLE POINTS_TABLE AS 
    SELECT *
    FROM TEAM;
"""
crsr.execute(s)

record=crsr.execute("SELECT team1,team2,match_winner,win_type,win_margin FROM MATCH").fetchall()
connection.commit()
# created table
#                               0   1       2           3           4       
team_list=[]
tempo=crsr.execute("SELECT team_id FROM POINTS_TABLE").fetchall()

# commentscommentscommentscommentscommentscommentscomments

for i in tempo:
    team_list.append(i[0])

#team_list
crsr.execute(s)
s="""ALTER TABLE POINTS_TABLE


-- comments commentscommentscommentscommentscomments
    ADD points INTEGER DEFAULT 0;
"""


# commentscommentscommentscommentscommentscomments
crsr.execute(s)
s="""ALTER TABLE POINTS_TABLE
    ADD nrr FLOAT DEFAULT 0;
"""

# 1st-point
#2nd - nrr
point_and_nrr=dd(list)

for i in team_list:
    point_and_nrr[i].append(0)
    point_and_nrr[i].append(0)

# goto record 

ap = argparse.ArgumentParser()
ap.add_argument("-d","--data", type=str, help="path to data file")
args = vars(ap.parse_args())

np.set_printoptions(suppress=True)
data = pd.read_csv(args["data"])

instance = [inst1,inst2,inst3]
algorithm = pd.unique(inst1['algorithm'])
var1 = pd.unique(inst1['var1'])
epsilon = pd.unique(data2['epsilon'])
x = np.array(var1)
y1 = np.array([])
y2 = np.array([])

data1 = data.loc[data['epsilon'] == 0]
data2 = data.loc[data['epsilon'] != 0]
inst1 = data1.loc[data1['instance'] == 'i-1.txt']
inst2 = data1.loc[data1['instance'] == 'i-2.txt']
inst3 = data1.loc[data1['instance'] == 'i-3.txt']


for inst in pd.unique(data2['instance']):
	ins = data2.loc[data2['instance'] == inst]
	for eps in pd.unique(pd.unique(data2['epsilon'])):
		ep = ins.loc[ins['epsilon'] == eps]
		y_temp = np.array([])
		for hori in var1:
			hor = ep.loc[ep['var1'] == hori]
			y_temp = np.append(y_temp,np.array(hor['REG'].mean(0)))
		y2 = np.append(y2,y_temp)
for inst in instance:
	for algo in algorithm:
		alg = inst.loc[inst['algorithm'] == algo]
		y_temp = np.array([])
		for hori in var1:
			hor = alg.loc[alg['var1'] == hori]
			y_temp = np.append(y_temp,np.array(hor['REG'].mean(0)))
		y1 = np.append(y1,y_temp)

legnd = []
y1 = y1.reshape(-1,len(var1))
y2 = y2.reshape(-1,len(var1))
for i,inst in (enumerate(instance)):
	for j,algo in enumerate(algorithm):
		var2.plot(x,y1[i*len(algorithm) + j],label = '{}'.format(algo))
		legnd.append(algo)
	for k,ep in enumerate(epsilon):



		# var2.plot(x,y2[i*len(epsilon) + k],label = 'epsilon-greedy at epsilon = {}'.format(ep))
		# legnd.append('epsilon-greedy with epsilon = {}'.format(ep))
	# var2.legend(legnd)
	legnd.clear()



	var2.xscale('log')
	

	var2.yscale('log')
	var2.title('instance {} - both axes in log scale'.format(i+1))
	var2.xlabel('var1')
	var2.ylabel('Regret')
	var2.savefig('instance{}'.format(i+1))
	var2.clf()