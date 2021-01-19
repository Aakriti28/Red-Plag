import sqlite3 as sq

connection=sq.connect("ipl.db")
crsr=connection.cursor()

s="SELECT striker,runs_scored,extra_runs FROM BALL_BY_BALL;"
runs=crsr.execute(s).fetchall()
s1="SELECT player_id,player_name FROM PLAYER"
id_to_name=crsr.execute(s1).fetchall()


dic={}
runs_dic={}
for i in id_to_name:
    dic[i[0]]=i[1]
    runs_dic[i[0]]=0

for i in runs:
    runs_dic[i[0]]=runs_dic[i[0]]+i[1]

id=[]
for i in runs:
    if i[0] not in id:
        id.append(i[0])
run_list=[]
lst=[]

for i in range(len(id)):
    for j in range(len(id)-1):
        if run_list[j]<run_list[j+1]:
            temp=run_list[j+1]
            run_list[j+1]=run_list[j]
            run_list[j]=temp

            temp1=id[j+1]
            id[j+1]=id[j]
            id[j]=temp1

for i in id:
    run_list.append(runs_dic[i])
    lst.append(dic[i])

lst=[]
cnt=0
for i in range(len(id)):
    if cnt<20:
        print(id[i],end="")
        print(",",end="")
        print(lst[i],end="")
        print(",",end="")
        print(run_list[i])
        cnt+=1

for i in range(len(id)):
    for j in range(len(id)-1):
        if run_list[j]==run_list[j+1]:
            if lst[j]>lst[j+1]:
                temp=lst[j+1]
                lst[j+1]=lst[j]
                lst[j]=temp
                temp1=id[j+1]
                id[j+1]=id[j]
                id[j]=temp1

for i in id:
    for j in id_to_name:
        if j[0]==i:
            lst.append(j[1])
# connection.close()


class Node(object):
	"""
	Node contains two objects - a right and a lef child, both may be a Node or both None,
	latter representing a leaf
	"""

	def __str__(self):
		"""
		Default inorder print
		"""
		if self.right is None               and             self.lef is None:
			return             "(   )"
		else:
			return "( " + str(          self.right) + " " + str(self.lef) + " )"

	def __init__(self, right=None,
		lef=None):
		



		super(Node, self).__init__()
		self.right = right
		




		self.     lef = lef

	def __eq__(self, other):
		if self.right is None and self.lef is None:
			return other.right is None and other.lef is None
		elif self.right is None and self.lef is None:
			return False
		else:
			return self.right == other.right and self.lef == other.lef


	Returns a list of all unique trees with n internal nodes

def allSymTrees(n):
	"""
	Returns a list of all unique symmetrical trees with n internal nodes
	"""
	pass
def func(  node  ):
	"""
	Returns the mirror image of the tree rooted at node
	"""
	pass


def newfunc(n):
	"""
	"""
	pass


if __name__ == '__main__':
	for x in allSymTrees(int(input())):
		print(x)
	node = Node(Node(Node(), Node()), Node())
	print(node)