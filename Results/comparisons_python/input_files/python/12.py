import sqlite3 as sq



id_to_name_dic={}
runs_dic={}
for i in id_to_name:
    id_to_name_dic[i[0]]=i[1]
    runs_dic[i[0]]=0

for i in runs:
    runs_dic[i[0]]=runs_dic[i[0]]+i[1]

for i in id:
    run_list.append(runs_dic[i])
    name_list.append(id_to_name_dic[i])
id=[]
for i in runs:
    if i[0] not in id:
        id.append(i[0])
run_list=[]
name_list=[]

connection=sq.connect("ipl.db")
crsr=connection.cursor()

s="SELECT striker,runs_scored,extra_runs FROM BALL_BY_BALL;"
runs=crsr.execute(s).fetchall()
s1="SELECT player_id,player_name FROM PLAYER"
id_to_name=crsr.execute(s1).fetchall()

for i in range(len(id)):
    for j in range(len(id)-1):
        if run_list[j]<run_list[j+1]:
            temp=run_list[j+1]
            run_list[j+1]=run_list[j]
            run_list[j]=temp

            temp1=id[j+1]
            id[j+1]=id[j]
            id[j]=temp1
cnt=0
for i in range(len(id)):
    if cnt<20:
        cnt+=1
        print(id[i],end="")
        print(",",end="")
        print(name_list[i],end="")
        print(",",end="")
        print(run_list[i])

name_list=[]
for i in id:
    for j in id_to_name:
        if j[0]==i:
            name_list.append(j[1])

for i in range(len(id)):
    for j in range(len(id)-1):
        if run_list[j]==run_list[j+1]:
            if name_list[j]>name_list[j+1]:
                temp=name_list[j+1]
                name_list[j+1]=name_list[j]
                name_list[j]=temp
                temp1=id[j+1]
                id[j+1]=id[j]
                id[j]=temp1
connection.close()


class Node(object):
	"""
	Node contains two objects - a left and a right child, both may be a Node or both None,
	latter representing a leaf
	"""
	def __init__(self, left=None, right=None):
		super(Node, self).__init__()
		self.left = left
		self.right = right

	def __str__(self):
		"""
		Default inorder print
		"""
		if self.left is None and self.right is None:
			return "(   )"
		else:
			return "( " + str(self.left) + " " + str(self.right) + " )"

	def __eq__(self, other):
		if self.left is None and self.right is None:
			return other.left is None and other.right is None
		elif self.left is None and self.right is None:
			return False
		else:
			return self.left == other.left and self.right == other.right


def mirrorTree(node):
	"""
	Returns the mirror image of the tree rooted at node
	"""
	pass


def allTrees(n):
	"""
	Returns a list of all unique trees with n internal nodes
	"""
	pass


# def allSymTrees(n):
# 	"""
# 	Returns a list of all unique symmetrical trees with n internal nodes
# 	"""
# 	pass


if __name__ == '__main__':
	for x in allSymTrees(int(input())):
		print(x)
	node = Node(Node(Node(), Node()), Node())
	print(node)