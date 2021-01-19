import numpy 
import math
import matplotlib.pyplot as plt
from scipy.misc import derivative
from mpl_toolkits import mplot3d 

def b(x):
    k=-x
    if(x>=0): 
        return g(x)
    return g(k) 
def h(x):
    if (x<=0): return 0
    k1=x**2
    k2=(-1)/k1
    return math.exp(k2)

def g(x):
    
    y1=h(2-x)
    y2=h(x-1)

    return y1/(y1+y2)



# def input_val(y,x):
#     return numpy.arange(y,x,(x-y)/200)


def fn_plot1d(fn,y,x,filename,number):
    
    xval=input_val(y,x)
    y_list=list(map(fn,xval))
    yval=numpy.array(y_list)
    plt.figure(1) 
    plt.plot(xval,yval)
    plt.xlabel("x in domain")
    plt.ylabel("value of function")
    plt.title('graph of y=f(x) in the given domain')
    plt.savefig(filename)
    plt.close()

fn_plot1d(b,-2,2,"fn1plot.png")





def sinc(x,y):
    return numpy.sin(numpy.sqrt(x**2 + y**2))/numpy.sqrt(x**2 + y**2)

# def fn_plot2d(fn,y,x,y_min,y_max,filename):
#     x= numpy.linspace(y,x,200)   
#     y= numpy.linspace(y_min,y_max,200)

#     X, Y = numpy.meshgrid(x,y)
#     Z=fn(X,Y)

#     fig=plt.figure()
#     ax=plt.axes(projection='3d')
#     ax.plot_surface(X,Y,Z,rstride=1,cstride=1,cmap='viridis',edgecolor='none')
#     ax.set_title("suface plot of sinc function")
#     ax.set_xlabel("x values")
#     ax.set_ylabel("y values")
#     ax.set_zlabel("z=sinc(x,y)")
#     plt.savefig(filename)
#     plt.close()

# fn_plot2d(sinc,-4.71,4.71,-4.71,4.71,"fn2plot.png")

def nth_derivative_plotter(fn,n,xmin,xmax,filename):
    xval=input_val(xmin,xmax)

    k=n  

    yval=list(map((lambda x: derivative(fn,x,dx=1e-6,n=k)),xval ))
    
    y_arr=numpy.array(yval)
    plt.plot(xval,y_arr)
    plt.xlabel("x in domain")
    plt.ylabel("value of derivative")
    plt.title("graph of derivative of y=f(x) in given domain")
    plt.savefig(filename)
    plt.close()

nth_derivative_plotter(b,1,-2,2,"bd_1.png")
nth_derivative_plotter(b,2,-2,2,"bd_2.png")
