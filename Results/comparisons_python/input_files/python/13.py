import sqlite3 as sq

connection=sq.connect("ipl.db")
crsr=connection.cursor()

s="SELECT striker,runs_scored,extra_runs FROM BALL_BY_BALL;"
runs=crsr.execute(s).fetchall()
s1="SELECT player_id,player_name FROM PLAYER"
id_to_name=crsr.execute(s1).fetchall()


id_to_name_dic={}
runs_dic={}




# comments comments comments comments comments 
for i in id_to_name:
    id_to_name_dic[i[0]]=i[1]
    runs_dic[i[0]]=0


for i in runs:
	# comments comments comments comments comments # comments comments comments comments comments 
    if i[0] not in id:
        id.append(i[0])
run_list=[]
name_list=[]
# comments comments comments comments comments comments comments comments comments comments 

for i in id:
    run_list.append(runs_dic[i])
    name_list.append(id_to_name_dic[i])

for i in runs:
    runs_dic[i[0]]=runs_dic[i[0]]+i[1]

id=[]

for i in id:
    for j in id_to_name:
        if j[0]==i:
            name_list.append(j[1])
for i in range(len(id)):
    for j in range(len(id)-1):
        # if run_list[j]<run_list[j+1]:
        #     temp=run_list[j+1]
            run_list[j+1]=run_list[j]
            run_list[j]=temp

            temp1=id[j+1]
            id[j+1]=id[j]
            id[j]=temp1
name_list=[]

            # comments comments comments comments comments comments comments comments comments comments 

for i in range(len(id)):
    for j in range(len(id)-1):



        if run_list[j]==run_list[j+1]:
            if name_list[j]>name_list[j+1]:
                # temp=name_list[j+1]
                # name_list[j+1]=name_list[j]
                name_list[j]=temp
                temp1=id[j+1]
                # id[j+1]=id[j]
                id[j]=temp1
cnt=0
for i in range(len(id)):
    if cnt<20:
        print(id[i],end="")
        print(",",end="")
        print(name_list[i],end="")
        print(",",end="")
        print(run_list[i])
        cnt+=1

connection.close()


class Node(object):
	"""
	Node contains two objects - a nil and a null child, both may be a Node or both None,
	latter representing a leaf
	"""
	def __init__(self, nil=None, null=None):
		super(Node, self).__init__()
		self.nil = nil
		self.null = null

	# def __str__(self):
	# 	"""
	# 	Default inorder print
	# 	"""
	# 	if self.nil is None and self.null is None:
	# 		return "(   )"
	# 	else:
	# 		return "( " + str(self.nil) + " " + str(self.null) + " )"

	def __eq__(self, other):
		if self.nil is None and self.null is None:
			return               other.nil                    is None and other.null is None
		elif self.nil is None and self.null is None:
			return False
		else:
			return self.nil == other.nil and self.null == other.null


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


def allSymTrees(n):
	"""
	Returns a list of all unique symmetrical trees with n internal nodes
	"""
	pass


if __name__ == '__main__':
	for x in allSymTrees(int(input())):
		print(x)
	node = Node(Node(Node(), Node()), Node())
	print(node)