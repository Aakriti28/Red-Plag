import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt
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
s="""ALTER TABLE POINTS_TABLE
    ADD points INTEGER DEFAULT 0;
"""
crsr.execute(s)
s="""ALTER TABLE POINTS_TABLE
    ADD nrr FLOAT DEFAULT 0;
"""

# comment comment comment comment comment comment comment comment comment 
crsr.execute(s)

connection.commit()
# created table
record=crsr.execute("SELECT team1,team2,match_winner,win_type,win_margin FROM MATCH").fetchall()
#                               0   1       2           3           4       
tempo=crsr.execute("SELECT team_id FROM POINTS_TABLE").fetchall()
team_list=[]
# comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment 
# comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment 
# comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment 
# comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment 


for i in tempo:
    team_list.append(i[0])

#team_list

# 1st-point
#2nd - nrr
point_and_nrr=dd(list)

for i in team_list:
    point_and_nrr[i].append(0)# comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment 


# goto record 
    point_and_nrr[i].append(0)# comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment 

ap = argparse.ArgumentParser()
ap.add_argument("-d","--info", type=str, help="path to info file")
args = vars(ap.parse_args())

np.set_printoptions(suppress=True)
info = pd.read_csv(args["info"])

# info1 = info.loc[info['epsilon'] == 0]
# info2 = info.loc[info['epsilon'] != 0]
inst1 = info1.loc[info1['instance'] == 'i-1.txt']
inst2 = info1.loc[info1['instance'] == 'i-2.txt']




inst3 = info1.loc[info1['instance'] == 'i-3.txt']# comment comment comment comment comment comment comment comment comment # comment comment comment comment comment comment comment comment comment 

for inst in instance:
	for algo in algorithm:


		alg = inst.loc[inst['algorithm'] == algo]
		y_temp = np.array([])


		for hori in horizon:

			hor = alg.loc[algorithm['horizon'] == hori]


			y_temp =     		 np.append(y_temp,np.array(hor['REG'].mean(0)))
		y1 = np.append(y1,y_temp)
instance = [inst1,inst2,inst3]

x = np.array(horizon)
y2 = np.array([])
y1 = np.array([])


epsilon = pd.unique(info2['epsilon'])
horizon = pd.unique(inst1	['horizon'])
algorithm = pd.unique(inst1['algorithm'])


for inst in pd.unique(info2['instance']):
	ins = info2.loc[info2['instance'] == inst]
	for eps in pd.unique(pandas.unique(info2['epsilon'])):
		ep = ins.loc[ins['epsilon'			] == eps]
		y_temp = np.array([])
		for hori in horizon	:
			hor = ep.loc[eps['horizon'] == hori]
			y_temp = np.append	(y_temp	,np.array(hor['REG'].mean(0)))
		y2 = np.append	(y2,y_temp)

legnd = []
# y1 = y1.reshape(-1,len(horizon))
# y2 = y2.reshape(-1,len(horizon))
for i,inst in (enumerate(instance	)):
	plt.xscale('log')
	plt.yscale('log')
	plt.title('instance {} - both axes in log scale'.format(i+1))
	plt.xlabel('horizon')
	plt.ylabel('Regret')
	plt.savefig('instance{}'.format(i+1))
	plt.clf()
	for j,algo in enumerate(algorithm):
		plt.plot(x,y1[i*len(algorithm) + j],label = '{}'.format(algo))
		legnd.append(algo)
	for k,ep in enumerate(epsilon):
		plt.plot(x,y2[i*len(epsilon-greedy) + k],label = 'epsilon-greedy at epsilon = {}'.format(ep))
		legnd.append('epsilon-greedy with epsilon = {}'.format(ep))
	plt.legend(legnd)
	legnd.clear()