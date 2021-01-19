"""
This code takes in a path to compressed file containing source code files, invokes tokenizers 
	depending  the langauge of the input file, and passes the tokenized code to the winnow() 
	function of the 'winnowing' module, to generate document fingerprints, which are matched 
	to produce a percentage similarity for every pair of source codes.
It saves the results in the form of a csv file and a pictorial representation of 
	the similarity matrix
"""

import os
import pandas as pd
import tarfile
import zipfile
import shutil
import matplotlib.pyplot as plt
import numpy as np

from winnowing import winnow
import sys

from .checker_cpp import tokenize_cpp
from .checker_py import tokenize_py
from .checker_java import tokenize_jav
from .backup_checker import backup_tokenize


def plagCheck(fp1,fp2, boilfp=None):

	""" 
	fp1 and fp2 are the fingerprints of the two files to be compared. These fingerprints 
		have been generated from winnowing, the method is explained below.
	boilfp is the fingerprint of boilerplate code.
	This function finds the common fingerprints of the two files and returns the ratio of
		 matched fingerprints and total fingerprints.
	If boilerplate is given by the user, it removes all the common fingerprints for the 
		two files with boilerplate
		"""

	if boilfp != None:
		tempfp1=set(fp1).difference(boilfp)
		tempfp2 = set(fp2).difference(boilfp)
	else:
		tempfp1 = set(fp1)
		tempfp2 = set(fp2)
	"""A list of common fingerprints"""
	comfpr=list(tempfp1 & tempfp2)
	

	deno = min(len(tempfp1),len(tempfp2))

	if deno ==0:
		ratio =0.0
	else:
		ratio= len(comfpr)/deno

	"""returns the ratio of matches and toal fingerprints, we have used minimum of the number of fingerprints in the denominator i.e., for comparisons,
	this is a fair assumption, based on tested results(makes it more sensitive to even small chunks of plagiarized snippets of codes"""
	return ratio

def folder_compare(dir_path, boil_path=None):

	"""dir_path is the path of the directory containing all the code files to be compared, 
		and boil_path is the path to boilerplate code file
	This function invokes tokenizers on various code files and generates the tokenized code
		 which it passes to the wiinow() function, along with the 'kval'
	which is actually the size of the kgram used to genrate hash values of the tokenized code. 
	Now, these fingerprints are compared pair wise, along with the boilerplate 
		fingerprint(if exists), by passing to plagCheck() fucntion
	It returns a simialrity matrix alongwith a list of filenames as an output.

	"""
	kval=10
	file_formats=(".cc",".cxx",".c++",".ii",".ixx",".ipp",".i++",".inl",".idl",".ddl",".odl",".hh",".hxx",".hpp",".h++",".cs",".d",".php",".php4",".php5", 
	".phtml",".inc",".m",".markdown",".md",".mm",".dox",".pyw",".f90",".f95",".f03",".f08",".f18",".f",".for",".vhd",".vhdl",".ucf",".qsf",".ice")
	cppfiles=[]
	filenames=[]
	sim_mat=[]
	files_fpr=[]
	boil_fpr=[]
	for path, subdirs, files in os.walk(dir_path):
		for file in files:
			if file.endswith((".cpp", ".py", ".c", ".h" , ".java")) and not file.startswith('.'):
				cppfiles.append(os.path.join(path, file))
				filenames.append(file)
			elif file.endswith(file_formats) and not file.startswith('.'):
				cppfiles.append(os.path.join(path, file))
				filenames.append(file)


	for file in cppfiles:
		try:
			if file.endswith((".cpp", ".h", ".c")):
				kval = 15
				data1 = tokenize_cpp(file)		
				
			if file.endswith(".py"):
				kval = 10
				data1 = tokenize_py(file)
			if file.endswith(".java"):
				kval = 15
				data1= tokenize_jav(file)
			if file.endswith(file_formats):
				kval = 10
				data1 = backup_tokenize(file)

		except:
			data1 = backup_tokenize(file)
			

		fpr_wpos=[]
		for fprs in winnow(data1, kval):
			fpr_wpos.append(fprs[1])
		files_fpr.append(fpr_wpos)

	if boil_path != None:
		try:
			if boil_path.endswith(".cpp"):
				data_b = tokenize_cpp(boil_path)
			if boil_path.endswith(".py"):
				data_b = tokenize_py(boil_path)
			if boil_path.endswith(".java"):
				data_b = tokenize_py(boil_path)
		except:
			data_b = backup_tokenize(boil_path)

		for fprs in winnow(data1, kval):
			boil_fpr.append(fprs[1])
	if boil_fpr:
		for fpr1 in files_fpr:
			temp=[]
			for fpr2 in files_fpr:
				temp.append(plagCheck(fpr1,fpr2, boil_fpr))
			sim_mat.append(temp)
	else:
		for fpr1 in files_fpr:
			temp=[]
			for fpr2 in files_fpr:
				temp.append(plagCheck(fpr1,fpr2))
			sim_mat.append(temp)

	res_mat = np.array(sim_mat)
	return res_mat, filenames




def saveres(inpath, outpath, boilpath=None):
	"""inpath is path to directory containing code and boilpath is path to boilerplate code file.
	This function basically calls folder_compare() function on the input directory and 
		saves the result in the form of csv to the output path(outpath),
	It also generates a graphical respresentation of the result and savs it in the 
		outpath folder.
	"""

	if boilpath==None:
		matres, filenames=folder_compare(inpath)
	else:
		matres, filenames=folder_compare(inpath, boilpath)

	extentt=np.arange(len(filenames)) + 0.5

	"""using pandas to generate dataframe and save it as csv from the similarity matrix"""

	df = pd.DataFrame(matres, index= filenames, columns=filenames)

	df.to_csv(os.path.join(outpath, 'results.csv'))

	
	"""using matplotlib to generate an image shwoing degree of plagiarism in a pair of file"""

	fig, ax = plt.subplots(1,1)

	img = ax.imshow(matres,cmap='Reds', vmin=0, vmax=1, extent=[0, len(filenames), 0, len(filenames)] )

	ax.set_xticks(extentt)
	ax.set_yticks(extentt)	

	ax.set_xticklabels(filenames, rotation= 60)
	ax.set_yticklabels(filenames[::-1])

	fig.colorbar(img)
	plt.tight_layout()
	plt.savefig(os.path.join(outpath, 'results.png'))



def extract_files(infile):
	"""infile is path to compressed file, this function extract files to 'comparisons/input_files' 
		folder, into the same base directory as input file"""
	if infile.endswith(".zip"):
		filename= os.path.splitext(os.path.basename(infile))[0]
	if infile.endswith(".tar"):
		filename= os.path.splitext(os.path.basename(infile))[0]
	if infile.endswith(".tar.gz"):
		filename= os.path.splitext(os.path.splitext(os.path.basename(infile))[0])[0]

	dirname1= os.path.dirname(infile)
	out_dir= os.path.join(dirname1, 'comparisons')

	if os.path.exists(out_dir) and os.path.isdir(out_dir):
		shutil.rmtree(out_dir, ignore_errors = False)
	
	if infile.endswith(".zip"):
		with zipfile.ZipFile(infile, 'r') as zip_ref:
			zip_ref.extractall(os.path.join(out_dir, 'input_files'))

	if tarfile.is_tarfile(infile):
		tf=tarfile.open(infile)
		tf.extractall(os.path.join(out_dir, 'input_files'))
	temp=os.listdir(out_dir)
	temp_dir= temp[0]
	return out_dir, os.path.join(out_dir,temp_dir)



def RunCheck(infile, boilfile=None):

	"""This function takes in the path to input compressed files, and boilerplate code and invokes 
		extract_files() function to extract the files and then savres() 
	function to generate and save the results in the 'comparisons/results' folder """

	formats=(".tar", ".tar.gz", ".zip")
	if infile.endswith(formats):
		try:
			out_dir , files_dir = extract_files(infile)
			res_dir= os.path.join(out_dir, 'results')
			os.mkdir(res_dir)
			if boilfile==None:
				saveres(files_dir, res_dir)
			else:
				saveres(files_dir, res_dir, boilfile)
			"""returns 'success' and path to directory having generated results"""
			return 'success' , res_dir
			""" returns 'fail' in all other scenarios"""
		except:
			return 'fail' , ''
		
	return 'fail', ''



