import pandas
import requests
import csv
import matplotlib.pyplot as plt
from scipy.cluster.vq import kmeans2
import numpy as np
import argparse
from PIL import Image
import pdb

ap = argparse.ArgumentParser()
ap.add_argument("-k","--k_val", type=int, help="path to input image")
ap.add_argument("-in","--input", type=str, help="path to input image")
args = vars(ap.parse_args())

def estimate_coef(x, y): 
    n = np.size(x) 
    m_x, m_y = np.mean(x), np.mean(y) 
    SS_xy = np.sum(y*x) - n*m_y*m_x 
    b_0 = m_y - b_1*m_x 
    SS_xx = np.sum(x*x) - n*m_x*m_x 
    b_1 = SS_xy / SS_xx 
    return(b_0, b_1) 
  

url = "https://api.covid19india.org/csv/latest/case_time_series.csv"

''' left is downloading and reading'''  

req = requests.get(url)
url_content = req.content
csv_file = open('case_time_series.csv', 'wb')

csv_file.write(url_content)
csv_file.close()

img = Image.open(args['input'])
data = asarray(img)
#shape = data.shape
data = data.astype('float').reshape(-1,3)

#centroid,label = kmeans2(data,k = args['k_val'],minit = '++')

out = centroid[label].reshape(shape)

out_img = Image.fromarray(out.astype('uint8'), 'RGB')
out_img.save('Lenna_{}.png'.format(args['k_val']))


i = 0
while rows[i][0] != '15 April ': 
   i=i+1 

H_t=[]
date=1
xval=[]

for j in range(i-1,len(rows)):
    yval=int(rows[j][6])/int(rows[j-1][6])
    xval.append(date)
    date=date+1
    H_t.append(yval)
    
def plot_regression_line(x, y, b):  
    plt.scatter(x, y, color = "m",marker = "o", s = 30)
    y_pred = b[0] + b[1]*x 
    plt.plot(x, y_pred, color = "g") 
    plt.legend(["best fit line","data points"])
    plt.title("Levitt's Metric of Covid19 in India ")
    plt.xlabel('day count') 
    plt.ylabel('metric values') 
    plt.savefig("covid.png")



H_arr=np.array(H_t)
x_arr=np.array(xval)

b= estimate_coef(x_arr,H_arr)

print(int((1-b[0])/b[1]))
plot_regression_line(x_arr,H_arr,b)


fields=[]
rows=[]
with open('case_time_series.csv','r') as f:
    csvreader=csv.reader(f)
    fields= next(csvreader)
    for row in csvreader:
        rows.append(row)
